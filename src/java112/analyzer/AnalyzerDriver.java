package java112.analyzer;

public class AnalyzerDriver {

    public static void main(String[] args) {
        //TODO Add code to write out the time it takes to run
        System.out.println("Start time: " + System.currentTimeMillis());

        AnalyzeFile analyzer = new AnalyzeFile();
        analyzer.runAnalysis(args);

        System.out.println("End time: " + System.currentTimeMillis());
    }

}
