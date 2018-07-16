package com.joeyreinders.psql.impl;

import com.joeyreinders.psql.FileExecutionResult;

import java.util.List;

final class DefaultFileExecutionResult implements FileExecutionResult {
    /**
     * Errors
     */
    private final String[] errors;

    /**
     * Normal
     */
    private final String[] output;

    /**
     * Default constructor
     */
    private DefaultFileExecutionResult(final String[] output, final String[] errors) {
        this.output = output;
        this.errors = errors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getErrorLines() {
        return errors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getOutput() {
        return output;
    }

    /**
     * Factory method
     */
    public static FileExecutionResult newInstance(final List<String> output, final List<String> errors) {
        return new DefaultFileExecutionResult(
            output.toArray(new String[0]),
            errors.toArray(new String[0])
        );
    }
}
