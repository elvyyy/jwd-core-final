package com.epam.jwd.core_final.exception;

public class EntityExistsInStorage extends RuntimeException {
    public EntityExistsInStorage() {
    }

    public EntityExistsInStorage(String message) {
        super(message);
    }

    public EntityExistsInStorage(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityExistsInStorage(Throwable cause) {
        super(cause);
    }

    public EntityExistsInStorage(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
