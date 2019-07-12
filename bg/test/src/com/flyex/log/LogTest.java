package com.flyex.log;


import org.apache.log4j.Logger;

public class LogTest {
    public static void main(String[] args) throws Exception {
        while (true) {
            Logger logger = Logger.getLogger("logRollingFile");
            logger.info("111111111111111111111111111110----"+ System.currentTimeMillis());
            Thread.sleep(50);
        }
    }
}
