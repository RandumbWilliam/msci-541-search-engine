// Name: William Zhen
// StudentID: 20792351
// Email: w2zhen@uwaterloo.ca

// Note: All acknowledgment for code snippets are noted by their respective numbers in Homework5.pdf

package search_engine.snippet;

import java.util.Comparator;

// [4] Snippet comparator for priority queue
public class SnippetComparator implements Comparator<SnippetObject> {
    public int compare(SnippetObject s1, SnippetObject s2) {
        if (s1.score < s2.score)
            return 1;
        else if (s1.score > s2.score)
            return -1;
        return 0;
    }
}
