package java112.analyzer;

import java.util.*;
import java.io.*;


public class TokenCountAnalyzer implements Analyzer {

    private Properties properties;
    private Map<String, Integer> tokenCounts;


    /**
     * Basic constructor for TokenCountAnalyzer
     */
    public TokenCountAnalyzer() {
        tokenCounts = new TreeMap<String, Integer>();
    }


    /**
     * Constructor for TokenCountAnalyzer with Properties parameter
     */
    public TokenCountAnalyzer(Properties properties) {

        this();

        this.properties = properties;
    }

    /**
     * Getter function for the field tokenCounts
     */
    public Map getTokenCounts() {
        return tokenCounts;
    }


    /**
     *  Process each token in input file.
     *
     *@param  token  Description of the Parameter
     */
    public void processToken(String token) {

        int count = 1;
        if(tokenCounts.containsKey(token)) {
            count = tokenCounts.get(token);
            tokenCounts.put(token, ++count);
        } else {
            tokenCounts.put(token, count);
        }
    }


    /**
     *  Write report file for TokenCountAnalyzer
     *
     *@param  inputFilePath   Input file that was analyzed
     */
    public void writeOutputFile(String inputFilePath) {

        PrintWriter writer = null;

        String outputFilePath = properties.getProperty("output.dir")
                + properties.getProperty("output.file.token.count");

        try {
            writer = new PrintWriter(new BufferedWriter(
                        new FileWriter(outputFilePath)));

            for (Map.Entry<String, Integer> entry : tokenCounts.entrySet()) {
                writer.print(entry.getKey());
                writer.print("\t");
                writer.println(entry.getValue());
            }

        } catch (IOException ioe) {
            System.out.println("Error creating TokenCountAnalyzer output file");
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
                System.out.println("Something extremely bad happened!");
                closeException.printStackTrace();
            }
        }

    }



}
