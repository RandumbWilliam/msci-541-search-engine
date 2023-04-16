// Name: William Zhen
// StudentID: 20792351
// Email: w2zhen@uwaterloo.ca

// Note: All acknowledgment for code snippets are noted by their respective numbers in Homework5.pdf

package search_engine.main;
import search_engine.bm25.BM25;
import search_engine.common.*;
import search_engine.initialize.LATimesData;
import search_engine.results.ResultObject;
import search_engine.snippet.Snippet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@SuppressWarnings("unchecked")

public class SearchEngine {
    // Gloabal variable to store a map of rank to raw document
    public Map<Integer, String> ranked_results;

    public String latimes_index_path;

    public SearchEngine(String latimes_index_path) {
        this.latimes_index_path = latimes_index_path;
    }

    // New query for results
    public void new_query(Map<String, Map> metadata, Map<String, Integer> lexicon, Map<Integer, List<Integer>> inverted_index) {
        // [1] Start command line input
        Scanner query_scan = new Scanner(new InputStreamReader(System.in));
        System.out.print("Enter your query: ");
        String query_input = query_scan.nextLine();
        // [2] Execution timer
        long start = System.nanoTime();
        System.out.println("Retrieving...");
        // Tokenize query
        List<String> query_tokens = Tokenizer.tokenize(query_input);
        // BM25 Results
        BM25 bm25_results = new BM25(metadata, lexicon, inverted_index);
        // Get Results
        Map<Integer, Double> results = bm25_results.getResults(query_tokens);
        // If results is 0, prompt user again for new query
        if (results.size() == 0) {
            re_query(metadata, lexicon, inverted_index);
        } else {
            // LATIMES INDEX PATH
//            String latimes_index_path = "/Users/williamzhen/Documents/MSCI541/hwtest/latimes-index";
            // Get ID metadata
            Map<Integer, Map<String, String>> metadata_id = metadata.get("id");
            // Initialize top ranked results
            ranked_results = new HashMap<>();
            // Initialize rank
            int rank = 1;
            // Go through the first top 10 results
            for (Integer docid : results.keySet()) {
                if (rank <= 10) {
                    // Meta information and Raw Document
                    Map<String, String> meta = metadata_id.get(docid);
                    String docno = meta.get("docno");
                    DatePathFinder date_path_finder = new DatePathFinder(docno);
                    String document_path = latimes_index_path + date_path_finder.getDocumentPath();
                    String raw_document = DocumentReader.document_read(document_path);
                    // Result Object
                    String headline = meta.get("headline");
                    String date = meta.get("date");
                    String snippet = Snippet.snip(raw_document, query_tokens);
                    ResultObject result_object = new ResultObject(rank, headline, date, snippet, docno);
                    String result = result_object.getResultObject();
                    System.out.println(result);
                    ranked_results.put(rank, raw_document);
                    rank++;
                }
            }
            // End retrieval time
            long end = System.nanoTime();
            long elapsedTime = end - start;
            double elapsedTimeInSecond = (double) elapsedTime / 1000000000;
            System.out.println("Retrieval took: " + elapsedTimeInSecond);
        }
    }

    // Re-prompt
    public void re_query(Map<String, Map> metadata, Map<String, Integer> lexicon, Map<Integer, List<Integer>> inverted_index) {
        System.out.println("There are no results!");
        System.out.println("Try another query.");
        new_query(metadata, lexicon, inverted_index);
    }

    // Rank document input query
    public void rank_query(Map<String, Map> metadata, Map<String, Integer> lexicon, Map<Integer, List<Integer>> inverted_index) {
        boolean quit = false;
        while (!quit) {
            // [1] Command line query input
            Scanner view_scan = new Scanner(new InputStreamReader(System.in));
            System.out.print("Enter the document you wish to view by entering the rank [\"N\" new query | \"Q\" quit]: ");
            String view_input = view_scan.nextLine();
            view_input = view_input.toLowerCase();
            // If q or Q quit the program
            if (view_input.equals("q")) {
                quit = true;
            // If n or N, call new_query and initialize new query
            } else if (view_input.equals("n")) {
                new_query(metadata, lexicon, inverted_index);
            // If integer ranked 1-10, get raw document
            } else if (CheckInteger.isInteger(view_input) && Integer.parseInt(view_input) > 0 && Integer.parseInt(view_input) <= 10) {
                int rank = Integer.parseInt(view_input);
                System.out.println(ranked_results.get(rank));
            // Invalid input
            } else {
                System.out.println("Please enter a valid integer from 1-10 or the option for a new query or to quit!");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        // /Users/williamzhen/Documents/MSCI541/hwtest/latimes-index
        Scanner latimes_scan = new Scanner(new InputStreamReader(System.in));
        System.out.print("Enter your path to the latimes-index: ");
        String latimes_query = latimes_scan.nextLine();
        // Load metadata, lexicon, and inverted index
        LATimesData latimes_data = new LATimesData(latimes_query);
        Map<String, Map> metadata = latimes_data.getMetadata();
        Map<String, Integer> lexicon = latimes_data.getLexicon();
        Map<Integer, List<Integer>> inverted_index = latimes_data.getInverted_index();
        // UI
        SearchEngine search_engine = new SearchEngine(latimes_query);
        search_engine.new_query(metadata, lexicon, inverted_index);
        search_engine.rank_query(metadata, lexicon, inverted_index);
    }
}
