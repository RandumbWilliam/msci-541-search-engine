// Name: William Zhen
// StudentID: 20792351
// Email: w2zhen@uwaterloo.ca

// Note: All acknowledgment for code snippets are noted by their respective numbers in Homework5.pdf

package search_engine.snippet;

// Snippet Object that holds the score and the sentence
public class SnippetObject {
    Integer score;
    String sentence;

    public SnippetObject(Integer score, String sentence_tokens) {
        this.score = score;
        this.sentence = sentence_tokens;
    }

    public String getSentence() {
        return this.sentence;
    }
}
