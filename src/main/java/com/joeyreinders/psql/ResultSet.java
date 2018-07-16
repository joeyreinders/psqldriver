package com.joeyreinders.psql;

/**
 * This class represents the result of a query.
 */
public interface ResultSet extends Iterable<Tuple>, AutoCloseable {
    /**
     * Get the resultset meta data
     */
    ResultSetMetadata getMetadata();

    /**
     * Get a tuple by index number
     */
    Tuple getByIndex(final int index);

    /**
     * Get the resultset size
     */
    int getSize();

    /**
     * Get the first tuple of the result set
     */
    Tuple first();

    /**
     * Check if the resultset is empty
     */
    boolean isEmpty();
}
