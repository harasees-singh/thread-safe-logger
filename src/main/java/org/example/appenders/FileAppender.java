package org.example.appenders;

import org.example.entities.LogMessage;
import org.example.formatters.LogFormatter;
import org.example.formatters.SimpleTextFormatter;

import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements LogAppender {

    FileWriter writer;
    LogFormatter formatter;

    public FileAppender(String filePath) {
        this.formatter = new SimpleTextFormatter();
        try {
            this.writer = new FileWriter(filePath, true);
        } catch (Exception e) {
            System.out.println("Failed to create writer for file logs, exception: " + e.getMessage());
        }
    }

    @Override
    public synchronized void append(LogMessage logMessage) {
        try {
            writer.write(this.formatter.format(logMessage) + "\n");
        } catch (IOException ex) {
            System.out.println("Failed to write logs to file, exception: " + ex.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            writer.close();;
        } catch (IOException ex) {
            System.out.println("Failed to close FileWriter");
        }
    }

    @Override
    public void setFormatter(LogFormatter logFormatter) {
        this.formatter = logFormatter;
    }

    @Override
    public LogFormatter getLogFormatter() {
        return this.formatter;
    }
}
