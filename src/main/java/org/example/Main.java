package org.example;

import org.example.appenders.ConsoleAppender;
import org.example.appenders.FileAppender;

public class Main {
    public static void main(String[] args) {
        LogManager logManager = LogManager.getInstance();

        Logger fileLogger = logManager.getLogger("file");
        fileLogger.addAppender(new FileAppender("/Users/haraseessingh/Desktop/thread-safe-logger/src/main/java/org/example/logs/log.log"));

        fileLogger.debug("first log");

        Logger consoleLogger = logManager.getLogger("console");
        consoleLogger.addAppender(new ConsoleAppender());

        consoleLogger.debug("hella");

        logManager.shutDown();
    }
}