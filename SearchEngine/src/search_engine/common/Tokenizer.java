// Name: William Zhen
// StudentID: 20792351
// Email: w2zhen@uwaterloo.ca

// Note: All acknowledgment for code snippets are noted by their respective numbers in Homework5.pdf

package search_engine.common;

import java.util.ArrayList;
import java.util.List;

// Tokenize string
public class Tokenizer {
    public static List<String> tokenize(String text) {
        // Lowercase all characters
        String lowercase_text = text.toLowerCase();
        // List of tokens
        List<String> tokens = new ArrayList<>();
        int start = 0;
        int i;
        for (i = 0; i < lowercase_text.length(); i++) {
            // check if each character is alphanumeric
            char l = lowercase_text.charAt(i);
            // If non-alphanumeric, slice the string between indexes start and i and save that substring as token in the list
            if (!Character.isLetterOrDigit(l)) {
                if (start != i) {
                    String token = lowercase_text.substring(start, i);
                    tokens.add(token);
                }
                start = i + 1;
            }
        }
        // Substring the last term of the string
        if (start != i) {
            tokens.add(lowercase_text.substring(start));
        }
        // Return list of tokens
        return tokens;
    }
}
