package com.joeyreinders.psql;

/**
 * Management tools
 */
public interface ManagementTools {
    /**
     * Get the current activity.
     * @see pg_stat_activity
     */
    ResultSet getStatisticsActivity();

    /**
     * Show the current settings
     * @See https://www.postgresql.org/docs/9.1/static/sql-show.html
     */
    ResultSet showSettings();

    /**
     * Show the value for a setting
     */
    String showSetting(final String settingName);

    /**
     * Create a new database
     */
    void createDatabase(final String newName);

    /**
     * Drop a database
     */
    void dropDatabase(final String database);
}
