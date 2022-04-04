package com.heydari.transactions.exception;

public class TransactionBadRequestException extends RuntimeException{
    public TransactionBadRequestException() {
        super();
    }
    public TransactionBadRequestException(String message) {
        super(message);
    }
    public TransactionBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
