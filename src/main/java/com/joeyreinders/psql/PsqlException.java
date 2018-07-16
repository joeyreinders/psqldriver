package com.joeyreinders.psql;

public final class PsqlException extends RuntimeException {
    public PsqlException(final String message) {
        super(message);
    }
}
