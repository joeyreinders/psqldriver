package com.joeyreinders.psql.impl;

import com.joeyreinders.psql.Connection;
import com.joeyreinders.psql.DatabaseMetadata;
import com.joeyreinders.psql.ResultSet;

final class DefaultDatabaseMetadata implements DatabaseMetadata {
    private final Connection connection;

    /**
     * Default constructor
     */
    private DefaultDatabaseMetadata(final Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return connection.executeQuery(SqlStatements.DB_NAME).first().getString("current_database");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet getSchema() {
        return connection.executeQuery(SqlStatements.GET_SCHEMA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet getTables(String schemaName) {
        if(schemaName == null || schemaName.isEmpty()) {
            schemaName = "public";
        }

        return connection.executeQuery(SqlStatements.GET_TABLES.replaceFirst("\\?", schemaName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersion() {
        return connection.executeQuery(SqlStatements.GET_VERSION).first().getString("version");
    }

    /**
     * Factory method
     */
    static DatabaseMetadata newInstance(final Connection connection) {
        return new DefaultDatabaseMetadata(connection);
    }
}
