package com.joeyreinders.psql;

/**
 * Database meta data
 */
public interface DatabaseMetadata {
    /**
     * Get the current database name
     */
    String getName();

    /**
     * Get all the schema on the database
     */
    ResultSet getSchema();

    /**
     * Get all the tables of the database.
     * If no schema is defined, public is assumed
     */
    ResultSet getTables(final String schemaName);

    /**
     * Get the database version
     */
    String getVersion();
}
