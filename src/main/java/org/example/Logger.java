package org.example;

import lombok.Getter;
import org.example.appenders.LogAppender;
import org.example.entities.LogMessage;
import org.example.enums.DebugLevel;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Logger {
    private List<LogAppender> appenders;
    private String name;

    public Logger(String name) {
        this.name = name;
        appenders = new ArrayList<>();
    }

    public void addAppender(LogAppender appender) {
        appenders.add(appender);
    }

    public void log(DebugLevel level, String message) {
        AsyncLogProcessor.getAsyncLogProcessorInstance().process(new LogMessage(message, level), appenders);
    }

    public void debug(String message) {
        log(DebugLevel.DEBUG, message);
    }
}
