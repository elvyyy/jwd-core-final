package com.epam.jwd.core_final.exception;

public class NoAvailableMissionExist extends RuntimeException {
    public NoAvailableMissionExist() {
    }

    public NoAvailableMissionExist(String message) {
        super(message);
    }

    public NoAvailableMissionExist(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAvailableMissionExist(Throwable cause) {
        super(cause);
    }

    public NoAvailableMissionExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
