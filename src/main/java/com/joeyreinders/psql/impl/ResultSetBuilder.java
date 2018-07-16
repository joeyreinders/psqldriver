package com.joeyreinders.psql.impl;

import com.joeyreinders.psql.ResultSet;
import com.joeyreinders.psql.ResultSetMetadata;
import com.joeyreinders.psql.Tuple;

import java.util.ArrayList;
import java.util.List;

final class ResultSetBuilder implements Builder<ResultSet> {
    private final List<String[]> elements = new ArrayList<>();

    private ResultSetMetadata resultSetMetadata;

    private ResultSetBuilder() {
        //NOOP
    }

    public ResultSetBuilder add(final String[] row) {
        this.elements.add(row);
        return this;
    }

    public ResultSetBuilder add(final ResultSetMetadata resultSetMetadata) {
        this.resultSetMetadata = resultSetMetadata;
        return this;
    }

    public static ResultSetBuilder newInstance() {
        return new ResultSetBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet build() {
        final Tuple[] tuples = new Tuple[elements.size()];
        int idx = -1;
        for(String[] element: elements) {
            idx ++;
            tuples[idx] = DefaultTuple.newInstance(element, resultSetMetadata);
        }

        return new DefaultResultSet(tuples, resultSetMetadata);
    }
}
