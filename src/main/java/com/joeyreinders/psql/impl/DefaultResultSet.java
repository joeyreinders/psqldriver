package com.joeyreinders.psql.impl;

import com.joeyreinders.psql.ResultSet;
import com.joeyreinders.psql.ResultSetMetadata;
import com.joeyreinders.psql.Tuple;

import java.util.Arrays;
import java.util.Iterator;

/**
 * The default implementation of the ResultSet
 */
final class DefaultResultSet implements ResultSet {
    /**
     * Container
     */
    private final Tuple[] tuples;

    /**
     * Close
     */
    private boolean closed = false;

    /**
     * Result set meta data
     */
    private final ResultSetMetadata metadata;

    /**
     * Default constructor
     */
    DefaultResultSet(final Tuple[] tuples, final ResultSetMetadata metadata) {
        this.tuples = tuples;
        this.metadata = metadata;
    }

    /**
     * {@inheritDoc}
     */
    public ResultSetMetadata getMetadata() {
        return metadata;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple getByIndex(final int index) {
        checkClosedResultSet();
        return tuples[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        checkClosedResultSet();
        return tuples.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple first() {
        return getByIndex(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<Tuple> iterator() {
        checkClosedResultSet();
        return Arrays.asList(tuples).iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        closed = true;
        for(int i = 0; i < tuples.length; i++) {
            tuples[i] = null;
        }
    }

    /**
     * check if the resultset is already closed
     */
    private void checkClosedResultSet() {
        if(closed) {
            throw new IllegalStateException("Resultset is already closed");
        }
    }

    @Override
    public String toString() {
        return Util.join("\n", Arrays.asList(tuples));
    }
}
