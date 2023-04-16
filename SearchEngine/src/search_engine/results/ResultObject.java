// Name: William Zhen
// StudentID: 20792351
// Email: w2zhen@uwaterloo.ca

// Note: All acknowledgment for code snippets are noted by their respective numbers in Homework5.pdf
package search_engine.results;

// Snippet object for each result
public class ResultObject {
    Integer rank;
    String headline;
    String date;
    String snippet;
    String docno;

    public ResultObject(Integer rank, String headline, String date, String snippet, String docno) {
        this.rank = rank;
        this.headline = headline;
        this.date = date;
        this.snippet = snippet;
        this.docno = docno;
    }

    public String getResultObject() {
        String header = this.headline;
        int string_index = this.snippet.length();
        if (this.headline.equals("NO HEADLINE")) {
            if ( this.snippet.length() > 50) {
                string_index = 50;
            }
            if (this.snippet.trim().length() > 0) {
                header = this.snippet.substring(0,string_index) + " ...";
            } else {
                header = "No text or header ...";
            }
        }
        String head = this.rank + ". " + header + "(" + this.date + ")";
        String body = this.snippet + "(" + this.docno + ")";
        return head + "\n" + body + "\n";
    }
}
