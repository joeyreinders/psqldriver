package com.joeyreinders.psql.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionDetailsTest {
    private ConnectionDetails details;

    @Before
    public void setupDetails() {
        details = ConnectionDetails.newBuilder()
                .host("localhost")
                .port(2018)
                .database("test-db")
                .userName("john")
                .password("tpwd")
                .build();
    }

    @Test
    public void getConnectionString() {
        assertEquals("export PGPASS=tpwd;psql -h localhost -p 2018 -d test-db -U john",
                details.getConnectionString());
    }
}