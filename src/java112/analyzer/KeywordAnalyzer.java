package java112.analyzer;

import java.util.*;
import java.io.*;


public class KeywordAnalyzer implements Analyzer {

    private Map<String, List<Integer>> keywordMap;
    private Properties properties;
    private int tokenOccurence;

    /**
     * Basic constructor for KeywordAnalyzer
     */
    public KeywordAnalyzer() {

        keywordMap = new TreeMap <String, List <Integer >>();
    }

    /**
     * Basic constructor for KeywordAnalyzer
     */
    public KeywordAnalyzer(Properties properties) {

        this();

        this.properties = properties;

        loadKeywordFile();
    }

    /**
     * Returns the value of keywordMap.
     */
    public Map<String, List<Integer>> getKeywordMap() {
        return keywordMap;
    }

    /**
     *  Process each token in input file.
     *
     *@param  token  Description of the Parameter
     */
    public void processToken(String token) {

        tokenOccurence++;

        List keywordOccuences = null;

        if (keywordMap.containsKey(token)) {
            keywordOccuences = keywordMap.get(token);
            keywordOccuences.add(tokenOccurence);
        }
    }

    /**
     *  Write report file
     *
     *@param  inputFilePath   Input file that was analyzed
     */
    public void writeOutputFile(String inputFilePath) {


        PrintWriter writer = null;

        String outputFilePath = properties.getProperty("output.dir") + properties.getProperty("output.file.keyword");

        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath)));

            for (Map.Entry<String, List<Integer>> entry : keywordMap.entrySet()) {
                outputOccurences(entry, writer);
            }

        } catch (IOException ioe) {
            System.out.println("Error creating UniqueTokenAnalyzer output file.");
            ioe.printStackTrace();
        } catch (Exception exception) {
            System.out.println("Something really bad happened.");
            exception.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception closeException) {
                System.out.println("Something really bad happened.");
                closeException.printStackTrace();
            }
        }

    }

    private void outputOccurences(Map.Entry<String, List<Integer>> entry, PrintWriter out) {

        String keyword = entry.getKey();
        List<Integer> occurences = entry.getValue();

        // System.out.println("entry keyword: " + keyword);

        outputKeywordAndFirstOccurence(keyword, occurences, out);

        outputMiddleOccurences(occurences, out);

        outputLastOccurence(occurences, out);

    }

    private void outputLastOccurence(List<Integer> occurences, PrintWriter out) {

        if (occurences.size() == 1) {
            out.println("]");
            out.println();
        } else {
            out.println(occurences.get(occurences.size() - 1) + "]");
            out.println();
        }

    }

    private void outputMiddleOccurences(List<Integer> occurences, PrintWriter out) {

        int rowCount = 2;

        Integer occurence = null;

        for (int index = 1; index < occurences.size() - 1; index++) {

            occurence = (Integer) occurences.get(index);

            if (occurence.intValue() < 10000 && rowCount > 11) {
                out.println(occurence + ",");
                rowCount = 1;
            } else {
                if (occurence.intValue() < 100000 && occurence.intValue() >= 10000 && rowCount > 10) {
                    out.println(occurence + ",");
                    rowCount = 1;
                } else {
                    if (occurence.intValue() < 1000000 && occurence.intValue() >= 100000 && rowCount > 9) {
                        // System.out.println("In < 1,000,000");
                        out.println(occurence + ",");
                        rowCount = 1;
                    } else {
                        if (occurence.intValue() < 10000000 && occurence.intValue() >= 1000000 && rowCount > 8) {
                            out.println(occurence + ",");
                            rowCount = 1;
                        } else {
                            out.print(occurence + ", ");
                            rowCount++;
                        }
                    }
                }
            }

        }

    }

    private void outputKeywordAndFirstOccurence(String keyword, List<Integer> occurences, PrintWriter out) {

        out.println(keyword + " =");

        if (occurences.size() == 1) {
            Integer occurence = occurences.get(0);
            out.print("[" + occurence);
        } else if (occurences.size() > 1) {
            Integer occurence = occurences.get(0);
            out.print("[" + occurence + ", ");
        }

    }

    private void loadKeywordFile() {

        String keywordFilePath = properties.getProperty("file.path.keywords");
        // System.out.println("keywordFilePath: " + keywordFilePath);

        BufferedReader keywordReader = null;
        String line;

        try {

            keywordReader = new BufferedReader(new FileReader(keywordFilePath));

            while (keywordReader.ready()) {
                line = keywordReader.readLine();
                if (line.length() > 0) {
                    keywordMap.put(line, new ArrayList<Integer>());
                }
            }

            // System.out.println("keywords: " + keywordMap);

        } catch (FileNotFoundException fnfe) {
            System.out.println("Keyword file not found.");
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Something IO happened.");
            ioe.printStackTrace();
        } catch (Exception exception) {
            System.out.println("Something really bad happened.");
            exception.printStackTrace();
        } finally {
            try {
                if (keywordReader != null) {
                    keywordReader.close();
                }
            } catch (Exception closeException) {
                System.out.println("Something really bad happened.");
                closeException.printStackTrace();
            }
        }
    }

}

