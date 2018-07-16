package com.joeyreinders.psql;

import com.joeyreinders.psql.impl.ConnectionDetails;
import com.joeyreinders.psql.impl.DefaultConnection;

public final class PsqlManager {
    public static Connection createConnection(final String host,
                                              final int port,
                                              final String database,
                                              final String userName,
                                              final String password) {
        final ConnectionDetails details = ConnectionDetails.newBuilder()
                .host(host)
                .port(port)
                .database(database)
                .userName(userName)
                .password(password)
                .build();
        return DefaultConnection.newInstance(details);
    }

    public static Connection createLocalConnection(final int port,
                                                   final String database,
                                                   final String userName,
                                                   final String password) {
        return createConnection("localhost", port, database, userName, password);
    }

    public static Connection createLocalConnection(final String database,
                                                   final String userName,
                                                   final String password) {
        return createLocalConnection(5432, database, userName, password);
    }
}
