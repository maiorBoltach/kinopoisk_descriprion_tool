package ru.kinopoisk.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerClass {
    private static Logger logger;
    private static Logger loggerID;
    private static final String PATH = "src/main/resources/configs/log4j2-config.xml";
    private static final String CONFIG_LOG4J = "log4j.configurationFile";
    public static boolean stepSuccessful =true;


    public static synchronized Logger getInstanceSummaryLogger() {
        if (logger == null) {
            System.setProperty(CONFIG_LOG4J, PATH);
            logger = LogManager.getLogger("Summary");
        }
        return logger;
    }

    public static synchronized Logger getInstanceSummaryLoggerID() {
        if (loggerID == null) {
            System.setProperty(CONFIG_LOG4J, PATH);
            loggerID = LogManager.getLogger("IDonly");
        }
        return loggerID;
    }
}