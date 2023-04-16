// Name: William Zhen
// StudentID: 20792351
// Email: w2zhen@uwaterloo.ca

// Note: All acknowledgment for code snippets are noted by their respective numbers in Homework5.pdf

package search_engine.bm25;

import java.util.*;
import java.util.stream.Collectors;

public class BM25 {
    Map<String, Map> metadata;
    Map<String, Integer> lexicon;
    Map<Integer, List<Integer>> inverted_index;

    public BM25(Map<String, Map> metadata, Map<String, Integer> lexicon, Map<Integer, List<Integer>> inverted_index) {
        this.metadata = metadata;
        this.lexicon = lexicon;
        this.inverted_index = inverted_index;
    }

    // Query token frequency
    public Map<Integer,Integer> QueryCount(List<String> query_tokens) {
        // Initialize map for unique query token to count
        Map<Integer, Integer> query_count = new HashMap<>();
        // Count unique query tokens
        for (String query_token : query_tokens) {
            // Count query token if only exist in lexicon
            if (lexicon.containsKey(query_token)) {
                int query_token_id = lexicon.get(query_token);
                query_count.merge(query_token_id,1,Integer::sum);
            }
        }
        // Return map of query token count
        return query_count;
    }

    // Calculate the average document length of the collection
    public Double AverageDocumentLength() {
        // Get collection of internal id
        Map<Integer, Map<String, String>> metadata_id = metadata.get("id");
        // Accumulate the sum of document lengths
        double sum = 0;
        for (Integer id : metadata_id.keySet()) {
            int length = Integer.parseInt(metadata_id.get(id).get("length"));
            sum += length;
        }
        // return average
        return sum/metadata_id.size();
    }

    public Map<Integer, Double> getResults(List<String> query_tokens) {
        // Get collection of internal id
        Map<Integer, Map<String, String>> metadata_id = metadata.get("id");
        // Initialize parameters and constants
        double average_doc_length = AverageDocumentLength();
        double k1 = 1.2;
        double b = 0.75;
        double k2 = 7;
        double N_doc_colletion = metadata_id.size();
        // Get frequency of query tokens
        Map <Integer, Integer> query_count = QueryCount(query_tokens);
        // Initialize accumulator of docid to score
        Map <Integer, Double> accumulator = new HashMap<>();
        // Calculate BM25 for each query token
        for (Integer query_token_id : query_count.keySet()) {
            // Get query frequency of query token
            int query_frequency = query_count.get(query_token_id);
            // Get list of postings for query token
            List<Integer> postings = inverted_index.get(query_token_id);
            // Number of documents for query token
            int n_doc_postings = postings.size()/2;
            // Accumulate score for each unique docid
            for (int i = 0; i <= postings.size()-2; i+=2) {
                // Internal ID
                int docid = postings.get(i);
                // Frequency of query token in document
                int doc_frequency = postings.get(i+1);
                // Get document length
                double doc_length = Double.parseDouble(metadata_id.get(docid).get("length"));
                // Calculate BM25 Score
                double K = k1 * ((1 - b) + b * (doc_length / average_doc_length));
                double tf_doc = ((k1 + 1) * doc_frequency) / (K + doc_frequency);
                double tf_query = ((k2 + 1) * query_frequency) / (k2 + query_frequency);
                double idf = Math.log((N_doc_colletion - n_doc_postings + 0.5)/(n_doc_postings + 0.5));
                double score = tf_doc * tf_query * idf;
                // Merge docid and the BM25 score
                accumulator.merge(docid, score, Double::sum);
            }
        }
        // [2] Return sorted accumulator by score
        return accumulator.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (entry1, entry2) -> entry2, LinkedHashMap::new));
    }
}
