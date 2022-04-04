package com.heydari.transactions.exception;

public class TransactionInternalException extends  Exception{
    public TransactionInternalException() {
        super();
    }
    public TransactionInternalException(String message) {
        super(message);
    }

    public TransactionInternalException(String message, Throwable cause) {
        super(message, cause);
    }
}
