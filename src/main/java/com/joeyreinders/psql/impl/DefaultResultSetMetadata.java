package com.joeyreinders.psql.impl;

import com.joeyreinders.psql.ResultSetMetadata;

import java.util.Arrays;

final class DefaultResultSetMetadata implements ResultSetMetadata {
    private final String[] columns;

    /**
     * Default constructor
     */
    private DefaultResultSetMetadata(final String[] cols) {
        this.columns = cols;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getColumns() {
        return Arrays.copyOf(this.columns, this.columns.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnIndex(final String columnName) {
        int counter = -1;

        for(String element: columns) {
            counter ++;

            if(element.equals(columnName)) {
                return counter;
            }
        }

        throw new IllegalArgumentException("Could not find column: " + columnName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Factory method
     */
    public static ResultSetMetadata newInstance(final String[] cols) {
        return new DefaultResultSetMetadata(cols);
    }
}
