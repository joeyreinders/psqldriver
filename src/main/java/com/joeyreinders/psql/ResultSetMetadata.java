package com.joeyreinders.psql;

/**
 * The meta data that can be retrieved from the result
 */
public interface ResultSetMetadata {
    /**
     * Get all the columns that were retrieved
     * @return an array of column names
     */
    String[] getColumns();

    /**
     * Get the column index of a certain column by name
     * @param columnName the column name
     * @return the index number of the column, IllegalArgument when not found
     */
    int getColumnIndex(final String columnName);

    /**
     * Get the column count
     */
    int getColumnCount();
}
