package org.gnuhpc.interview.datastructure.queue.basicimpl;

public class QueueEmptyException extends RuntimeException {
    private static final long serialVersionUID = 2L;

    public QueueEmptyException() {
        super();
    }

    public QueueEmptyException(String message) {
        super(message);
    }

    public QueueEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}

