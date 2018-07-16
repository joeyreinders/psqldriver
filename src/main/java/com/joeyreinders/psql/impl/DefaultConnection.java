package com.joeyreinders.psql.impl;

import com.joeyreinders.psql.*;

import java.io.File;

public final class DefaultConnection implements Connection {
    private final ConnectionDetails connectionDetails;
    private final QueryExecutor queryExecutor;

    /**
     * Default constructor
     */
    private DefaultConnection(final ConnectionDetails connectionDetails) {
        this.connectionDetails = connectionDetails;
        this.queryExecutor = QueryExecutor.newInstance(connectionDetails);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean testConnection() {
        return ! executeQuery("SELECT 1=1").isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet executeQuery(final String queryStatement) {
        return queryExecutor.executeQuery(queryStatement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeQueryToCsv(final String queryStatement, final File location) {
        queryExecutor.executeToCsv(queryStatement, Util.canonicalize(location));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConnectionDetails getConnectionDetails() {
        return connectionDetails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseMetadata getDatabaseMetadata() {
        return DefaultDatabaseMetadata.newInstance(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ManagementTools getManagementTools() {
        return DefaultManagementTools.newInstance(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileExecutionResult executeFile(final File theFile) {
        return FileExecutor.newInstance(connectionDetails).execute(theFile);
    }

    /**
     * Factory method
     */
    public static DefaultConnection newInstance(final ConnectionDetails cDetails) {
        return new DefaultConnection(cDetails);
    }
}
