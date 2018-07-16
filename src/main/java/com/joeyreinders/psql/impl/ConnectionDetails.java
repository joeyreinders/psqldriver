package com.joeyreinders.psql.impl;

public final class ConnectionDetails {
    private static final String FORMAT_STRING = "export PGPASS=%s;psql -h %s -p %s -d %s -U %s";
    private static final String FORMAT_STRING_NO_DB = "export PGPASS=%s;psql -h %s -p %s -U %s";

    private final String host;
    private final int port;
    private final String database;
    private final String userName;
    private final String password;

    private ConnectionDetails(final ConnectionDetailsBuilder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.database = builder.database;
        this.userName = builder.userName;
        this.password = builder.password;
    }

    String getConnectionString() {
        if(this.database == null || this.database.isEmpty()) {
            return String.format(FORMAT_STRING_NO_DB, this.password, this.host, this.port, this.userName);
        }
        return String.format(FORMAT_STRING, this.password, this.host, this.port, this.database, this.userName);
    }

    public static ConnectionDetailsBuilder newBuilder() {
        return new ConnectionDetailsBuilder();
    }

    public static class ConnectionDetailsBuilder implements Builder<ConnectionDetails> {
        private String host;
        private int port;
        private String database;
        private String userName;
        private String password;

        private ConnectionDetailsBuilder() {
            //NOOP
        }

        public ConnectionDetailsBuilder host(final String host) {
            this.host = host;
            return this;
        }

        public ConnectionDetailsBuilder port(final int port) {
            this.port = port;
            return this;
        }

        public ConnectionDetailsBuilder database(final String database) {
            this.database = database;
            return this;
        }

        public ConnectionDetailsBuilder userName(final String userName) {
            this.userName = userName;
            return this;
        }

        public ConnectionDetailsBuilder password(final String password) {
            this.password = password;
            return this;
        }

        @Override
        public ConnectionDetails build() {
            return new ConnectionDetails(this);
        }
    }
}
