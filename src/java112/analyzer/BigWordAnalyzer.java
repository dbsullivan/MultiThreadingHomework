package java112.analyzer;

import java.io.*;
import java.util.*;


public class BigWordAnalyzer implements Analyzer {

    private Properties properties;
    private Set<String> bigWords;
    private int minimumWordLength;

    /*
      * Constructor for BigWordAnalyzer
    */
    public BigWordAnalyzer() {
        bigWords = new TreeSet<String>();
    }

    /*
      * Constructor for BigWordAnalyzer with Properties parameter
    */
    public BigWordAnalyzer(Properties properties){

        this();

        this.properties = properties;

        String minimum = properties.getProperty("bigwords.minimum.length");

        minimumWordLength = Integer.parseInt(minimum);

    }

    /**
     * Returns the value of bigWordsSet.
     */
    public Set<String> getBigWords() {
        return bigWords;
    }



    public void processToken(String token) {

        if (token.length() >= minimumWordLength) {
            bigWords.add(token);
        }
    }

    public void writeOutputFile(String inputFilePath) {

        PrintWriter output = null;

        String outputFilePath = properties.getProperty("output.dir") 
        + properties.getProperty("output.file.bigwords");

        try {
            output = new PrintWriter(new BufferedWriter(
                    new FileWriter(outputFilePath)));

            for (String token : bigWords) {
                output.println(token);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            output.close();
        }
    }


}
