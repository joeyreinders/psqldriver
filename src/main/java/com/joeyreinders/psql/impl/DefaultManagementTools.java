package com.joeyreinders.psql.impl;

import com.joeyreinders.psql.Connection;
import com.joeyreinders.psql.ManagementTools;
import com.joeyreinders.psql.ResultSet;

final class DefaultManagementTools implements ManagementTools {
    /**
     * Connection
     */
    private final Connection connection;

    /**
     * Default constructor
     */
    private DefaultManagementTools(final Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet getStatisticsActivity() {
        return connection.executeQuery(SqlStatements.PG_STAT_ACTIVITY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSet showSettings() {
        return connection.executeQuery(SqlStatements.SHOW_SETTINGS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String showSetting(final String settingName) {
        return connection.executeQuery(String.format(SqlStatements.SHOW_SETTING, settingName)).first().getString(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createDatabase(final String newName) {
        connection.executeQuery(String.format(SqlStatements.CREATE_DATABASE, newName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dropDatabase(final String database) {
        connection.executeQuery(String.format(SqlStatements.DROP_DATABASE, database));
    }

    /**
     * Factory method
     */
    static ManagementTools newInstance(final Connection connection) {
        return new DefaultManagementTools(connection);
    }
}
