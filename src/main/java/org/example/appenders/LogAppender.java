package org.example.appenders;

import org.example.entities.LogMessage;
import org.example.formatters.LogFormatter;

public interface LogAppender {
    void append(LogMessage logMessage);
    void close();
    void setFormatter(LogFormatter logFormatter);
    LogFormatter getLogFormatter();
}
