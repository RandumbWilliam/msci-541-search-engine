// Name: William Zhen
// StudentID: 20792351
// Email: w2zhen@uwaterloo.ca

// Note: All acknowledgment for code snippets are noted by their respective numbers in Homework5.pdf

package search_engine.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Read raw document into a string
public class DocumentReader {
    public static String document_read(String document_path) {
        File file = new File(document_path);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String raw_document = "";
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            raw_document = raw_document.concat(line + "\n");
        }

        return raw_document;
    }
}
