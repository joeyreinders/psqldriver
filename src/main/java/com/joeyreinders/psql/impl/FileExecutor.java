package com.joeyreinders.psql.impl;

import com.joeyreinders.psql.FileExecutionResult;
import com.joeyreinders.psql.PsqlException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Execute a file against the database
 */
final class FileExecutor {
    /**
     * Connection details
     */
    private final ConnectionDetails connectionDetails;

    /**
     * Default constructor
     */
    private FileExecutor(final ConnectionDetails connectionDetails) {
        this.connectionDetails = connectionDetails;
    }

    /**
     * Run the file against the database
     */
    FileExecutionResult execute(final File theFile) {
        final List<String> output = new ArrayList<>();
        final List<String> errors = new ArrayList<>();

        Process proc = null;
        try {
            final String cmd = connectionDetails.getConnectionString() + " -f " + theFile.getCanonicalPath();
            proc = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmd});
            read(proc.getInputStream(), output);
            read(proc.getErrorStream(), errors);
            return DefaultFileExecutionResult.newInstance(output, errors);
        } catch (final Exception ex) {
            throw new PsqlException("Error while executing the SQL file.");
        } finally {
            if(proc != null) {
                proc.destroy();
            }
        }
    }

    /**
     * Read stream line by line
     */
    private void read(final InputStream is, final List<String> result) throws IOException  {
        final BufferedReader buf = new BufferedReader(new InputStreamReader(is));


        for (String line = buf.readLine(); line != null; line = buf.readLine()) {
            result.add(line);
        }
    }

    /**
     * Factory method
     */
    public static FileExecutor newInstance(final ConnectionDetails connectionDetails) {
        return new FileExecutor(connectionDetails);
    }
}
