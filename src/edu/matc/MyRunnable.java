package edu.matc;

import org.apache.log4j.Logger;

/**
 * A class to demonstrate using the Runnable interface
 * @author paulawaite
 * @version 1.0 10/31/15.
 */
public class MyRunnable implements Runnable {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Override
    public void run() {
        logger.info("In MyRunnable");
    }
}
