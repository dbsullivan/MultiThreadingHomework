package java112.analyzer;

import java.util.*;
import java.io.*;

public class SummaryReport implements Analyzer {

    private int totalTokensCount;
    private Properties properties;


    /**
     * Basic constructor for SummaryReport
     */
    public SummaryReport() {
        
    }

    /**
     * Constructor for SummaryReport with Properties
     */
    public SummaryReport(Properties properties) {
        this();
        this.properties = properties;
    }


    /**
     * Returns the value of totalTokensCount.
     */
    public int getTotalTokensCount() {
        return totalTokensCount;
    }


    public void processToken(String token) {

        totalTokensCount++;

        //System.out.println("token: " + token + ", count: " + totalTokensCount);
    }

    public void writeOutputFile(String inputFilePath) {

        PrintWriter output = null;

        String outputFilePath = properties.getProperty("output.dir")
                + properties.getProperty("output.file.summary");

        try {
            output = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath)));

            output.println("Application: FileCruncher");
            output.println("Author: Eric Knapp");
            output.println("Email: eknapp@gmail.com");
            output.println("Input File: " + inputFilePath);
            output.println("Analysis time stamp: " + new Date());
            output.println("Total Token Count: " + totalTokensCount);


        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            output.close();
        }


    }


}
