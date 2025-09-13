package org.example;

import org.example.appenders.LogAppender;
import org.example.entities.LogMessage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncLogProcessor {

    private final ExecutorService executor;

    private AsyncLogProcessor() {
        this.executor = Executors.newSingleThreadExecutor(runnable -> {
            Thread thread = new Thread(runnable, "AsyncLogProcessor");
            thread.setDaemon(true); // not a user thread, jvm doesn't wait for it to shut down
            return thread;
        });
    }

    private static final AsyncLogProcessor asyncLogProcessor = new AsyncLogProcessor();

    public static AsyncLogProcessor getAsyncLogProcessorInstance() {
        return AsyncLogProcessor.asyncLogProcessor;
    }

    public void process(LogMessage logMessage, List<LogAppender> logAppenders) {
        if (executor.isShutdown()) {
            throw new RuntimeException("executor is shut down! can't process logs");
        }

        executor.submit(() -> {
            for (LogAppender logAppender: logAppenders) {
                logAppender.append(logMessage);
            }
        });
    }

    public void shutDown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                System.err.println("Logger executor did not terminate in the specified time.");
                // Forcibly shut down any still-running tasks.
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
