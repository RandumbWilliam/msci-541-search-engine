# Homework 5 | Search Engine

Name: William Zhen <br />
Student ID: 20792351 <br />
Email: w2zhen@uwaterloo.ca

## How To Run The Program

Ensure you have Java installed! <br />
This program was wirtten with Java JDK 17. <br />
[Click here to install Java](https://www.oracle.com/java/technologies/downloads/) <br />
Compile the program if needed. In the main directory of the program: <br />
1. $ cd src
2. $ javac search_engine/main/SearchEngine.java
<br />
To run the program: <br />
$ java search_engine.main.SearchEngine <br />

### Parameters
The first prompt will prompt the user to locate it's latimes index folder.<br />
<br />
### Notes
After metadata, lexicon, and inverted index has been loaded, the user can now start using the program.<br />
<br />
In the ranked retrieval query, choose an integer from ranked 1-10 to get the full raw document OR<br />
N - new query (lowercase or uppercase)<br/>
Q - quit (lowercase or uppercase)<br/>
<br />

### Instructions ([Git repo instructions](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository))
#### To use the Search Engine
1. Open Terminal
2. Change the current working directory to the location where you want the cloned directory
3. $ git clone https://github.com/UWaterloo-MSCI-541/msci-541-f21-hw5-RandumbWilliam.git
4. Press Enter to create your local clone
5. $ cd msci-541-f21-hw5-RandumbWilliam/
6. $ cd SearchEngine/src
7. $ javac search_engine/main/SearchEngine.java
8. $ java search_engine.main.SearchEngine
9. Enter your path to the latimes index folder
10. Wait for metadata, lexicon, and inverted index to load in
11. Once it prompts you to "Enter your query: ", you can enter your query and hit Enter!
12. Once your results have been retrieved, it'll prompt you to get one of the ranked documents or new query or quit <br/>
"Enter the document you wish to view by entering the rank ["N" new query | "Q" quit]: "
13. Finish

### Example(MacOS)
1. Open Terminal
2. $ cd Desktop
3. $ mkdir RepoClone
4. $ cd RepoClone
5. $ git clone https://github.com/UWaterloo-MSCI-541/msci-541-f21-hw5-RandumbWilliam.git
6. Enter
7. $ cd msci-541-f21-hw4-RandumbWilliam/
8. $ cd SearchEngine/src
9. $ javac search_engine/main/SearchEngine.java
10. $ java search_engine.main.SearchEngine
11. Enter your path to the latimes-index: /Users/williamzhen/Desktop/latimes-index
12. Wait
13. Enter your query: UV damage eyes
14. Enter the document you wish to view by entering the rank ["N" new query | "Q" quit]:
15. Finish

## Acknowledgments
* [Interactive command line input](https://alvinalexander.com/java/edu/pj/pj010005/)
* [Execution timer](https://stackoverflow.com/questions/3382954/measure-execution-time-for-a-java-method)
* [Split into sentences](https://stackoverflow.com/questions/21430447/how-to-split-paragraphs-into-sentences)
* [Priority queue comparator](https://www.geeksforgeeks.org/implement-priorityqueue-comparator-java/)
* Thank you to Anjali Mistry, 4th year biology (not in this course), helped designed longest contiguous k for query biased snippet
* [Croft, Bruce, et al. Search Engines: Information Retrieval in Practice. Pearson Education, 2010](https://ciir.cs.umass.edu/downloads/SEIRiP.pdf)
