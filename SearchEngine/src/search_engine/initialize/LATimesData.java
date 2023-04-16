// Name: William Zhen
// StudentID: 20792351
// Email: w2zhen@uwaterloo.ca

// Note: All acknowledgment for code snippets are noted by their respective numbers in Homework5.pdf

package search_engine.initialize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

public class LATimesData {
    // la-times data
    Map<String, Map> metadata;
    Map<String, Integer> lexicon;
    Map<Integer, List<Integer>> inverted_index;

    public LATimesData(String latimes_index_path) throws IOException {
//        String latimes_index_path = "/Users/williamzhen/Documents/MSCI541/hwtest/latimes-index";
        ObjectInputStream meta_input = new ObjectInputStream(new FileInputStream(latimes_index_path + "/metadata.txt"));
        ObjectInputStream lexi_input = new ObjectInputStream(new FileInputStream(latimes_index_path + "/lexicon.txt"));
        ObjectInputStream inv_input = new ObjectInputStream(new FileInputStream(latimes_index_path + "/inverted_index.txt"));

        // Read metadata
        System.out.println("Reading metadata...");

        try {
            this.metadata = (Map<String, Map>) meta_input.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        meta_input.close();

        System.out.println("Reading metadata complete");

        // Read lexicon
        System.out.println("Reading lexicon...");

        try {
            this.lexicon = (Map<String, Integer>) lexi_input.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        lexi_input.close();

        System.out.println("Reading lexicon complete");

        // Read inverted index
        System.out.println("Reading inverted index...");
        try {
            this.inverted_index = (Map<Integer, List<Integer>>) inv_input.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        inv_input.close();

        System.out.println("Reading inverted index complete");
    }

    public Map<String, Map> getMetadata() {
        return this.metadata;
    }

    public Map<String, Integer> getLexicon() {
        return this.lexicon;
    }

    public Map<Integer, List<Integer>> getInverted_index() {
        return this.inverted_index;
    }
}
