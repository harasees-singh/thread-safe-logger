package org.example.appenders;

import org.example.entities.LogMessage;
import org.example.formatters.LogFormatter;
import org.example.formatters.SimpleTextFormatter;

public class ConsoleAppender implements LogAppender {
    private LogFormatter logFormatter;

    public ConsoleAppender() {
        this.logFormatter = new SimpleTextFormatter();
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(this.logFormatter.format(logMessage));
    }

    @Override
    public void close() {
        // Do Nothing
    }

    @Override
    public void setFormatter(LogFormatter logFormatter) {
        this.logFormatter = logFormatter;
    }

    @Override
    public LogFormatter getLogFormatter() {
        return this.logFormatter;
    }
}
