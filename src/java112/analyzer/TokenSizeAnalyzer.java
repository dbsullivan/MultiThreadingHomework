package java112.analyzer;


import java.io.*;
import java.util.*;

public class TokenSizeAnalyzer implements Analyzer {


    private Map<Integer, Counter> tokenSizes;
    private Properties properties;
    private int maximumSize;

    /*
      * Constructor for TokenSizeAnalyzer
    */
    public TokenSizeAnalyzer(){
        tokenSizes = new TreeMap<Integer, Counter>();
    }

    /*
      * Constructor for TokenSizeAnalyzer
    */
    public TokenSizeAnalyzer(Properties properties){

        this();

        this.properties = properties;
    }

    public Map<Integer, Counter> getTokenSizes() {
        return tokenSizes;
    }

    public int getMaximumSize() {
        return maximumSize;
    }


    public void processToken(String token) {

        int tokenSize = token.length();
        int currentCount = 1;

        if (tokenSizes.containsKey(tokenSize)) {
            tokenSizes.get(tokenSize).increment();
        } else {
            tokenSizes.put(tokenSize, new Counter());
        }

        if (currentCount > maximumSize) {
            maximumSize = currentCount;
        }

    }

    public void writeOutputFile(String inputFilePath) {


        PrintWriter writer = null;

        String outputFilePath = properties.getProperty("output.dir")
                + properties.getProperty("output.file.token.size");

        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath)));

            for (Map.Entry<Integer, Counter> entry : tokenSizes.entrySet()) {
                writer.print(entry.getKey());
                writer.print("\t");
                writer.println(entry.getValue());
            }

        } catch (IOException ioe) {
            System.out.println("Error creating TokenSizeAnalyzer output file.");
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


}
