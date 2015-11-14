package edu.matc;

import org.apache.log4j.Logger;

/**
 * A class to demonstrate extending Thread
 * @author paulawaite
 * @version 1.0 10/31/15.
 */
public class MyThread extends Thread {
    private final Logger logger = Logger.getLogger(this.getClass());

    public void run() {
        logger.info("In MyThread");
    }
}
