package org.example;

import org.example.appenders.LogAppender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogManager {

    private static LogManager INSTANCE = new LogManager();

    public static LogManager getInstance() {
        return INSTANCE;
    }

    private Map<String, Logger> map;

    private LogManager() {
        map = new ConcurrentHashMap<>();
    }

    public Logger getLogger(String name) {
        return map.computeIfAbsent(name, this::createLogger);
    }

    private Logger createLogger(String name) {
        return new Logger(name);
    }

    public void shutDown() {
        AsyncLogProcessor.getAsyncLogProcessorInstance().shutDown();
        map.values()
                .forEach(logger -> logger.getAppenders()
                        .forEach(LogAppender::close));
        ;
    }
}
