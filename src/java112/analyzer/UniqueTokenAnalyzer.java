package java112.analyzer;

import java.util.*;
import java.io.*;

public class UniqueTokenAnalyzer implements Analyzer {

    private Set<String> uniqueTokensList;
    private Properties properties;


    /**
     * Basic constructor for UniqueTokenAnalyzer
     */
    public UniqueTokenAnalyzer() {

        uniqueTokensList = new TreeSet<String>();

    }

    public UniqueTokenAnalyzer(Properties properties) {

        this();

        this.properties = properties;

    }

    public Set getUniqueTokensList() {
        return uniqueTokensList;
    }

    public void processToken(String token) {

        uniqueTokensList.add(token);

    }

    public void writeOutputFile(String inputFilePath) {

        PrintWriter output = null;

        String outputFilePath = properties.getProperty("output.dir")
                + properties.getProperty("output.file.unique");

        try {
            output = new PrintWriter(new BufferedWriter(
                    new FileWriter(outputFilePath)));

            for (String token : uniqueTokensList) {
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
