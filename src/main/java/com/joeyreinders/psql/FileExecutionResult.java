package com.joeyreinders.psql;

/**
 * File execution result
 */
public interface FileExecutionResult {
    /**
     * Get the error content
     */
    String[] getErrorLines();

    /**
     * Get the normal result content
     */
    String[] getOutput();
}
