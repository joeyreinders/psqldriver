package com.joeyreinders.psql.impl;

import com.joeyreinders.psql.PsqlException;
import com.joeyreinders.psql.ResultSet;
import com.joeyreinders.psql.ResultSetMetadata;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

final class QueryExecutor {
    private final ConnectionDetails connectionDetails;

    private QueryExecutor(final ConnectionDetails connectionDetails) {
        this.connectionDetails = connectionDetails;
    }

    void executeToCsv(final String statement, final String location) {
        final String stmt = String.format(SqlStatements.COPY_TO_CSV, statement, location);
        executeQuery(stmt);
    }

    ResultSet executeQuery(final String statement) {
        final StringBuilder cmd = new StringBuilder(this.connectionDetails.getConnectionString());
        //Add correct format
        cmd.append(" -t -x ");
        //Add the statement
        cmd.append(" -c \"").append(statement).append(" \"");

        //Execute the thing
        final Result result = exec(cmd.toString());
        if(result.errorResult != null && ! result.errorResult.isEmpty()) {
            throw new PsqlException(result.errorResult.replaceFirst("ERROR:", "").trim());
        }

        return parse(result.normalResult);
    }

    private ResultSet parse(final String resultString) {
        final ResultSetBuilder resultSetBuilder = ResultSetBuilder.newInstance();

        final String lines[] = resultString.split("\\r?\\n");
        final ResultSetMetadata metadata = createMetadata(lines);

        List<String> values = new ArrayList<>();
        for(String line: lines) {
            if(isLineSeparator(line)) {
                resultSetBuilder.add(values.toArray(new String[0]));
            } else {
                final String[] keyValue = line.split("\\|");
                if(keyValue.length == 2) {
                    values.add(keyValue[1].trim());
                } else {
                    values.add(line);
                }
            }
        }

        resultSetBuilder.add(values.toArray(new String[0]));
        resultSetBuilder.add(metadata);
        return resultSetBuilder.build();
    }

    private ResultSetMetadata createMetadata(final String[] lines) {
        final Set<String> columns = new HashSet<>();
        for(String line: lines) {
            if(isLineSeparator(line)) {
                break;
            }

            columns.add(line.split("\\|")[0].trim());
        }

        return DefaultResultSetMetadata.newInstance(columns.toArray(new String[0]));
    }

    private boolean isLineSeparator(final String line) {
        return line.startsWith("-") && line.endsWith("-") && line.contains("-+-");
    }

    private Result exec(final String cmd) {
        final Result result = new Result();
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmd});
            result.normalResult = Util.read(proc.getInputStream());
            result.errorResult = Util.read(proc.getErrorStream());
            return result;
        } catch (final Exception ex) {
            throw new PsqlException("Error while executing statement.");
        } finally {
            if(proc != null) {
                proc.destroy();
            }
        }
    }

    static QueryExecutor newInstance(final ConnectionDetails connectionDetails) {
        return new QueryExecutor(connectionDetails);
    }

    private static class Result {
        String normalResult;
        String errorResult;
    }
}
