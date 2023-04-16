// Name: William Zhen
// StudentID: 20792351
// Email: w2zhen@uwaterloo.ca

// Note: All acknowledgment for code snippets are noted by their respective numbers in Homework5.pdf

package search_engine.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Check and convert DOCNO to date format
public class DatePathFinder {
    String Year;
    String Month;
    String Day;
    String docno;

    public DatePathFinder(String docno) {
        this.docno = docno;
        Pattern pattern = Pattern.compile("LA(\\d{2})(\\d{2})(\\d{2})-(\\d{4})");
        Matcher matcher = pattern.matcher(docno);
        matcher.find();

        this.Year = "19" + matcher.group(3);
        this.Month = matcher.group(1);
        this.Day = matcher.group(2);
    }

    public String getDocumentPath() {
        return "/Y-" + this.Year + "/M-" + this.Month + "/D-" + this.Day + "/" + this.docno + ".txt";
    }

    public String getDate() {
        return this.Month + "/" + this.Day + "/" + "19" + this.Year;
    }
}
