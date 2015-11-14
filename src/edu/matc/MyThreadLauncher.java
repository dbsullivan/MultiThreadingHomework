package edu.matc;

import org.apache.log4j.Logger;

/**
 * A class to demonstrate creating threads
 * @author paulawaite
 * @version 1.0 10/31/15.
 */
public class MyThreadLauncher {
    private final Logger logger = Logger.getLogger(this.getClass());

    // TODO Add code to MyThread and MyThreadLauncher to demonstrate how each of the following work:
    // sleep(long milliseconds)
    // alive()
    // getPriority() and setPriority(int priority)
    // join()

    public void run() {
        logger.info("Just before creating a new thread");
        MyThread myThread = new MyThread();
        myThread.start();
    }

    public static void main(String[] args) {
        MyThreadLauncher launcher = new MyThreadLauncher();
        launcher.run();
    }
}
