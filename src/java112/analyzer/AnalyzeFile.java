package java112.analyzer;

import java.util.*;
import java.io.*;

public class AnalyzeFile {

    private static final int ARGUMENTS_COUNT = 2;

    private String inputFilePath;

    private List<Analyzer> analyzers;

    private Properties properties;


    public void runAnalysis(String[] arguments) {

        if (arguments.length != ARGUMENTS_COUNT) {
            System.out.println("Please enter an input file path");
            return ;
        }

        inputFilePath = arguments[0];

        loadProperties(arguments[1]);

        createAnalyzers();

        readAndAnalyzeInputFile();

        writeAllOutputFiles();

    }

    
    private void createAnalyzers() {

        analyzers = new ArrayList<Analyzer>();

        analyzers.add(new SummaryReport(properties));
        analyzers.add(new UniqueTokenAnalyzer(properties));
        analyzers.add(new BigWordAnalyzer(properties));
        analyzers.add(new TokenCountAnalyzer(properties));
        analyzers.add(new KeywordAnalyzer(properties));
        analyzers.add(new TokenSizeAnalyzer(properties));
        
    }

    private void readAndAnalyzeInputFile() {

        BufferedReader input = null;
        String line = null;
        try {
            input = new BufferedReader(new FileReader(inputFilePath));

            while (input.ready()) {
                line = input.readLine();
                tokenizeLine(line);
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tokenizeLine(String line) {

        String[] lineTokenArray = line.split("\\W");

        for (int index = 0; index < lineTokenArray.length; index++) {
            processTokenWithAnalyzers(lineTokenArray[index]);
        }

    }

    private void processTokenWithAnalyzers(String token) {

        if (token.length() > 0) {

            for (Analyzer analyzer : analyzers) {
                analyzer.processToken(token);
            }
        }

    }

    private void writeAllOutputFiles() {

        for (Analyzer analyzer : analyzers) {
            analyzer.writeOutputFile(inputFilePath);
        }

    }

    private void loadProperties(String propertiesFilePath) {
        properties = new Properties();
        //System.out.println("Getting ready to load" + propertiesFilePath);
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));

        } catch (IOException ioe) {
            System.out.println("Can't load the properties file" + propertiesFilePath);
            ioe.printStackTrace();

        } catch (Exception e) {
            System.out.println("Problem: " + e);
            e.printStackTrace();

        }
    }

}
