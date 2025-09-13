package org.example.entities;

import lombok.Getter;
import org.example.enums.DebugLevel;

import java.time.LocalDateTime;

@Getter
public class LogMessage {
    String thread;
    String message;
    DebugLevel level;
    LocalDateTime timestamp;

    public LogMessage(String message, DebugLevel level) {
        this.thread = Thread.currentThread().getName();
        this.message = message;
        this.level = level;
        this.timestamp = LocalDateTime.now();
    }
}
