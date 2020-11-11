package com.epam.jwd.core_final.exception;

public class EntityAlreadyAssigned extends RuntimeException {
    public EntityAlreadyAssigned() {
    }

    public EntityAlreadyAssigned(String message) {
        super(message);
    }

    public EntityAlreadyAssigned(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyAssigned(Throwable cause) {
        super(cause);
    }

    public EntityAlreadyAssigned(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
