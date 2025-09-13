package org.example.formatters;

import org.example.entities.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
