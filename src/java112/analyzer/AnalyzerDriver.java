package java112.analyzer;

public class AnalyzerDriver {

    public static void main(String[] args) {
        //TODO Add code to write out the time it takes to run
/* before threads
Start time: 1447686262235
End time: 1447686266370
Total runtime: 4135
Process finished with exit code 0
*/


        long startTime =  System.currentTimeMillis();
        System.out.println("Start time: " + startTime);

        AnalyzeFile analyzer = new AnalyzeFile();
        analyzer.runAnalysis(args);

        long endTime =  System.currentTimeMillis();
        System.out.println("End time: " + endTime);
        System.out.println("Total runtime: " + Long.toString(endTime-startTime) );
    }

}
