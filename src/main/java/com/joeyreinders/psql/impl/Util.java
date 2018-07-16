package com.joeyreinders.psql.impl;

import com.joeyreinders.psql.PsqlException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * All the utility methods gathered together.
 */
final class Util {
    /**
     * Concatenate an iterable with a certain separator.
     * @param separator separator to use
     * @param iterable the items to concatenate
     * @return a concatenated string
     */
    static String join(final String separator, final Iterable<?> iterable) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<?> iter = iterable.iterator();
        while(iter.hasNext()) {
            sb.append(iter.next());
            if(iter.hasNext()) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    /**
     * Canonicalize a File path
     */
    static String canonicalize(final File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException ex) {
            throw new PsqlException(ex.getMessage());
        }
    }

    /**
     * Read an inputstream to a string
     */
    static String read(final InputStream inputStream) throws IOException {
        final InputStreamReader reader = new InputStreamReader(inputStream);
        final StringBuilder sb = new StringBuilder();
        int data = reader.read();
        while(data != -1) {
            sb.append((char) data);
            data = reader.read();
        }

        return sb.toString();
    }
}
