package com.joeyreinders.psql.impl;

/**
 * Utility to convert a string to a type.
 */
final class ValueUtil {
    /**
     * Hide constructor
     */
    private ValueUtil() {
        //NOOP
    }

    /**
     * Create an integer
     */
    static Integer createInteger(final String value) {
        return isNullOrEmpty(value) ? null : Integer.valueOf(value);
    }

    /**
     * Create a double
     */
    static Double createDouble(final String value) {
        return isNullOrEmpty(value) ? null : Double.valueOf(value);
    }

    /**
     * Check if value is null
     */
    static boolean isNull(final String value) {
        return value == null || "NULL".equals(value);
    }

    /**
     * Create a boolean
     */
    static Boolean createBoolean(final String value) {
        return "1".equals(value) || "true".equalsIgnoreCase(value);
    }

    /**
     * Create a long
     */
    static Long createLong(final String value) {
        return isNullOrEmpty(value) ? null : Long.valueOf(value);
    }

    /**
     * Check if a string is null or empty
     */
    private static boolean isNullOrEmpty(final String value) {
        return value == null || value.isEmpty();
    }
}
