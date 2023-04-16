// Name: William Zhen
// StudentID: 20792351
// Email: w2zhen@uwaterloo.ca

// Note: All acknowledgment for code snippets are noted by their respective numbers in Homework5.pdf

package search_engine.common;

// Check if string is an integer
public class CheckInteger {
    public static Boolean isInteger(String str_num) {
        if (str_num == null) {
            return false;
        }
        try {
            Integer.parseInt(str_num);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
