package cn.liangjieheng.learning.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {

    private static final Logger LOGGER = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        LOGGER.error("test error");
    }
}
