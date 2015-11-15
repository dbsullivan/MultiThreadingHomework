package edu.matc;

import org.apache.log4j.Logger;

/**
 * A class to demonstrate creating threads that implement runnable
 * @author paulawaite
 * @version 1.0 10/31/15.
 */
public class MyRunnableLauncher  {
    private final Logger logger = Logger.getLogger(this.getClass());

    public void run() {
        logger.info("Just before creating a new thread");
        MyRunnable myRunnable = new MyRunnable();
        Thread runnableThread = new Thread(myRunnable);
        runnableThread.start();
    }


    public static void main(String[] args) {
        MyRunnableLauncher launcher = new MyRunnableLauncher();
        launcher.run();
    }
}
