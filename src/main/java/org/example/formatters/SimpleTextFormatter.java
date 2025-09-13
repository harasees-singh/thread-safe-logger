package org.example.formatters;

import org.example.entities.LogMessage;

public class SimpleTextFormatter implements LogFormatter {

    @Override
    public String format(LogMessage logMessage) {
        return logMessage.getTimestamp().toString() + ' ' + logMessage.getThread() + ' ' + logMessage.getLevel().name() + ' ' + logMessage.getMessage();
    }
}
