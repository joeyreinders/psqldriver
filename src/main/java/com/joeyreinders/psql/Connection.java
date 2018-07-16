package com.joeyreinders.psql;

import com.joeyreinders.psql.impl.ConnectionDetails;

import java.io.File;

/**
 * Connection object to the database.
 */
public interface Connection {
    /**
     * Check if a connection can be made
     */
    boolean testConnection();

    /**
     * Execute a query on the database
     */
    ResultSet executeQuery(final String queryStatement);

    /**
     * Execute a query and save the result
     */
    void executeQueryToCsv(final String queryStatement, final File location);

    /**
     * Get the connection details
     */
    ConnectionDetails getConnectionDetails();

    /**
     * Get the database meta data
     */
    DatabaseMetadata getDatabaseMetadata();

    /**
     * Get management tools
     */
    ManagementTools getManagementTools();

    /**
     * Execute a file
     */
    FileExecutionResult executeFile(final File theFile);
}
