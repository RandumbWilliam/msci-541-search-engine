// Name: William Zhen
// StudentID: 20792351
// Email: w2zhen@uwaterloo.ca

// Note: All acknowledgment for code snippets are noted by their respective numbers in Homework5.pdf

package search_engine.snippet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import search_engine.common.Tokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.*;

public class Snippet {
    // Calculate score for each sentence in the text body
    public String ResultSnippet(String text_body, List<String> query_tokens) {
        // [3] split by punctuation
        String sentences[]=text_body.split("(?<=[.!?])\\s");
        // [4] Priority queue for pairs
        PriorityQueue<SnippetObject> snippet_objects = new PriorityQueue<SnippetObject>(new SnippetComparator());
        for (int i = 0; i < sentences.length; i++) {
            int l = 0;
            int c = 0;
            int d = 0;

            // If first sentence of body, l=2, if second sentence of body, l=1, otherwise l=0
            if (i == 0) {
                l = 2;
            } else if (i == 1) {
                l = 1;
            }
            // tokenize sentence
            List<String> sentence_tokens = Tokenizer.tokenize(sentences[i]);

            // c - query term count, including repetitions
            // d - query term count, distinct
            Set<String> unique_query_tokens = new HashSet<>(query_tokens);
            for (String query_token : unique_query_tokens) {
                c += Collections.frequency(sentence_tokens, query_token);
                if (sentence_tokens.contains(query_token)) {
                    d++;
                }
            }

            // [5] Longest contiguous
            int longest_contiguous = 0;
            int k = 0;
            int query_token_index = 0;
            for (String sentence_token : sentence_tokens) {
                // If the word exist in the query
                if (unique_query_tokens.contains(sentence_token)) {
                    // If this is the first encountered word to start the contigous
                    if (longest_contiguous == 0) {
                        // Assign the next index in for the query tokens
                        query_token_index = query_tokens.indexOf(sentence_token) + 1;
                        // increment contiguous counter
                        longest_contiguous++;
                    // On next iteration, if the query index is within the query tokens size
                    } else if (query_token_index < query_tokens.size()) {
                        // if query tokens at that query index is equals, then it's sentence is so far contiguous with the query
                        query_tokens.get(query_token_index).equals(sentence_token);
                        // increment contiguous counter
                        longest_contiguous++;
                        // increment query index counter
                        query_token_index++;
                    // Otherwise, it's the end the contiguous
                    } else  {
                        // Store max contiguous for that sentence
                        if (longest_contiguous > k) {
                            k = longest_contiguous;
                        }
                        longest_contiguous = 0;
                    }
                // Otherwise, it's the end of the contiguous
                } else  {
                    // Store max contiguous for that sentence
                    if (longest_contiguous > k) {
                        k = longest_contiguous;
                    }
                    longest_contiguous = 0;
                }
            }

            // Calculate the score
            int s = c + d + k + l;
            // Make a new snippet object and add to priority queue
            SnippetObject snippet_object = new SnippetObject(s, sentences[i]);
            snippet_objects.add(snippet_object);
        }
        // Retrieve top 3 sentences
        String result_snippet = "";
        for (int i = 0; i < 3; i++) {
            SnippetObject sentence_object = snippet_objects.poll();
            if (sentence_object != null) {
                result_snippet = result_snippet.concat(sentence_object.getSentence() + " ");
            }
        }
        return result_snippet;
    }

    // Parse raw document
    public String TextParse(String raw_document) {
        // Retrieve TEXT and P elements
        String TEXT = "";
        try {
            DocumentBuilderFactory db_factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder d_Builder = db_factory.newDocumentBuilder();
            Document doc = d_Builder.parse(new InputSource(new StringReader(raw_document)));
            doc.getDocumentElement().normalize();
            NodeList TEXT_list = doc.getElementsByTagName("TEXT");
            for (int i = 0; i < TEXT_list.getLength(); i++) {
                NodeList P_list = ((Element)TEXT_list.item(i)).getElementsByTagName("P");
                for (int j = 0; j < P_list.getLength(); j++) {
                    String paragraph = P_list.item(j).getTextContent();
                    paragraph = paragraph.replace("\n", " ");
                    TEXT = TEXT.concat(paragraph);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return TEXT;
    }

    public static String snip(String raw_document, List<String> query_tokens) {
        Snippet snippet = new Snippet();
        String text_body = snippet.TextParse(raw_document);
        return snippet.ResultSnippet(text_body, query_tokens);
    }
}
