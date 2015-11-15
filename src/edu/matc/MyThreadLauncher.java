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

    /*
    * Dave added thread methods:
    **/
    public void aliveThread(Thread myThread) {
        logger.info("Thread is Alive status for " + myThread.getName() + " == " + myThread.isAlive() + " at priority: " + myThread.getPriority());
    }

//    public void sleepThread(Thread myThread) {
//        for (int i = 0; i < 10; i++) {
//            logger.info("Thread sleeping counter for " + myThread.getName() + " == " + i);
//            try {
//                myThread.sleep(1000);
//                logger.info("Slept time ms for " + myThread.getName() + " == " + System.currentTimeMillis());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    public void run() {
        logger.info("Just before creating a new thread");
        MyThread myThreadA = new MyThread();  // demonstates highest priority thread
        MyThread myThreadB = new MyThread();  // demonstates lower priority thread
        MyThread myThreadC = new MyThread();  // demonstates thread in waiting, C depends on A or B to die, due to join().

        myThreadA.setPriority(5);
        myThreadA.setName("Thread...AAA");
        myThreadB.setPriority(5);
        myThreadB.setName("Thread...BBB");
        myThreadC.setPriority(5);
        myThreadC.setName("Thread...CCC");
        try {
            myThreadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myThreadA.start();  // start late with higher priority test, or increase priority midway through loop
        myThreadB.start();
        myThreadC.start();

        // add methods for threads, is it alive?
        aliveThread(myThreadA);
        aliveThread(myThreadB);
        aliveThread(myThreadC);


        for (int i = 0; i < 10; i++) {
            logger.info("Thread sleeping counter for " + myThreadA.getName() + " == " + i);
            logger.info("Thread sleeping counter for " + myThreadB.getName() + " == " + i);
            logger.info("Thread sleeping counter for " + myThreadC.getName() + " == " + i);
            try {
                if ( i == 5 && myThreadA.isAlive() == true) {
                     myThreadA.setPriority(10);
                    }
                if (myThreadA.isAlive() == true) {
                   myThreadA.sleep(100);
                }
                if (myThreadB.isAlive() == true) {
                   myThreadB.sleep(100);
                }
                if (myThreadC.isAlive() == true) {
                    myThreadC.sleep(100);
                }
                logger.info("Slept time ms for " + myThreadA.getName() + " == " + System.currentTimeMillis());
                logger.info("Slept time ms for " + myThreadB.getName() + " == " + System.currentTimeMillis());
                logger.info("Slept time ms for " + myThreadC.getName() + " == " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // add methods for threads, is it alive?
            aliveThread(myThreadA);
            aliveThread(myThreadB);
            aliveThread(myThreadC);
        }

        // add methods for threads, is it alive?
        aliveThread(myThreadA);
        aliveThread(myThreadB);
        aliveThread(myThreadC);
    }

    public static void main(String[] args) {
        MyThreadLauncher launcher = new MyThreadLauncher();
        launcher.run();
    }
}
