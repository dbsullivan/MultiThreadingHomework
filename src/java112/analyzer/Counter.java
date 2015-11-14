package java112.analyzer;

public class Counter {

    private int counter;


    /*
     *  Constructor for Counter
     */
    /**
     *  Constructor for the Counter object
     */
    public Counter() {
        counter = 1;
    }


    /**
     *  Returns the value of counter.
     *
     *@return    The counter value
     */
    public int getCounter() {
        return counter;
    }


    /**
     *  Description of the Method
     */
    public void increment() {
        counter++;
    }


    /**
     *  Description of the Method
     *
     *@return    Description of the Return Value
     */
    public String toString() {

        return counter + "";
    }

}

