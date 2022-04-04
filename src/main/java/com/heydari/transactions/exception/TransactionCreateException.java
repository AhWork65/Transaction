package com.heydari.transactions.exception;

public class TransactionCreateException extends Exception{
    public TransactionCreateException() {
        super();
    }
    public TransactionCreateException(String message) {
        super(message);
    }

    public TransactionCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}

