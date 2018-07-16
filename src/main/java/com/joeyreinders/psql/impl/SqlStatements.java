package com.joeyreinders.psql.impl;

/**
 * All the sql statements
 */
final class SqlStatements {
    /**
     * Hide constructor
     */
    private SqlStatements() {
        //Noop
    }

    /**
     * Current DB
     */
    static final String DB_NAME = "SELECT current_database()";

    /**
     * Get the available schemas
     */
    static final String GET_SCHEMA = "SELECT * FROM information_schema.schemata";

    /**
     * Get all the tables
     */
    static final String GET_TABLES = "SELECT * FROM information_schema.tables WHERE table_schema = ?";

    /**
     * Get the database version
     */
    static final String GET_VERSION = "SELECT version()";

    /**
     * pg_stat_activity
     */
    static final String PG_STAT_ACTIVITY = "SELECT * FROM pg_stat_activity";

    /**
     * Show settings
     */
    static final String SHOW_SETTINGS = "SHOW all";

    /**
     * Show setting
     */
    static final String SHOW_SETTING = "SHOW %s";

    /**
     * Create a new database
     */
    static final String CREATE_DATABASE = "CREATE DATABASE %s";

    /**
     * Drop a database
     */
    static final String DROP_DATABASE = "DROP DATABASE %s";

    /**
     * Copy to CSV
     */
    static final String COPY_TO_CSV = "COPY ( %s ) TO '%s' DELIMITER ',' CSV HEADER";
}
