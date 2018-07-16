package com.joeyreinders.psql.impl;

import com.joeyreinders.psql.ResultSetMetadata;
import com.joeyreinders.psql.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of a tuple
 */
final class DefaultTuple implements Tuple {
    private String[] values;
    private ResultSetMetadata metadata;

    private DefaultTuple(final String[] aRow, final ResultSetMetadata metadata) {
        this.values = aRow;
        this.metadata = metadata;
    }

    /**
     * {@inheritDoc}
     */
    public String getString(final String columnName) {
        final int idx = metadata.getColumnIndex(columnName);
        return getString(idx);
    }

    /**
     * {@inheritDoc}
     */
    public String getString(final int idx) {
        if (idx > values.length) {
            throw new ArrayIndexOutOfBoundsException(idx);
        }

        return values[idx];
    }

    /**
     * {@inheritDoc}
     */
    public Double getDouble(final String columnName) {
        return ValueUtil.createDouble(getString(columnName));
    }

    /**
     * {@inheritDoc}
     */
    public Double getDouble(final int idx) {
        return ValueUtil.createDouble(getString(idx));
    }

    /**
     * {@inheritDoc}
     */
    public Integer getInteger(final String columnName) {
        return ValueUtil.createInteger(getString(columnName));
    }

    /**
     * {@inheritDoc}
     */
    public Integer getInteger(final int idx) {
        return ValueUtil.createInteger(getString(idx));
    }

    /**
     * {@inheritDoc}
     */
    public Boolean getBoolean(final String columnName) {
        return ValueUtil.createBoolean(getString(columnName));
    }

    /**
     * {@inheritDoc}
     */
    public Boolean getBoolean(final int idx) {
        return ValueUtil.createBoolean(getString(idx));
    }

    /**
     * {@inheritDoc}
     */
    public Long getLong(final String columnName) {
        return ValueUtil.createLong(getString(columnName));
    }

    /**
     * {@inheritDoc}
     */
    public Long getLong(final int idx) {
        return ValueUtil.createLong(getString(idx));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNull(final String columnName) {
        return ValueUtil.isNull(getString(columnName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNull(final int idx) {
        return ValueUtil.isNull(getString(idx));
    }

    /**
     * Factory method
     */
    static Tuple newInstance(final String[] aRow, final ResultSetMetadata metadata) {
        return new DefaultTuple(aRow, metadata);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final List<String> rowString = new ArrayList<>();
        for(String colName: metadata.getColumns()) {
            rowString.add(colName + "=" + getString(colName));
        }

        return Util.join(",", rowString);
    }
}
