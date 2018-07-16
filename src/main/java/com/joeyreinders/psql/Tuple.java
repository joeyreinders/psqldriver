package com.joeyreinders.psql;

/**
 * A tuple represents the record
 */
public interface Tuple {
    /**
     * Get the cell value as string
     * @param columnName the name of the column
     * @return the string value of the cell
     */
    String getString(final String columnName);

    /**
     * Get the cell value as string
     * @param idx the index of the column
     * @return the string value of the cell
     */
    String getString(final int idx);

    /**
     * Get the cell value as double
     * @param columnName the name of the column
     * @return the double value of the cell
     */
    Double getDouble(final String columnName);

    /**
     * Get the cell value as double
     * @param idx the index of the column
     * @return the double value of the cell
     */
    Double getDouble(final int idx);

    /**
     * Get the cell value as integer
     * @param columnName the name of the column
     * @return the integer value of the cell
     */
    Integer getInteger(final String columnName);

    /**
     * Get the cell value as integer
     * @param idx the index of the column
     * @return the integer value of the cell
     */
    Integer getInteger(final int idx);

    /**
     * Get the cell value as boolean
     * @param columnName the name of the column
     * @return the boolean value of the cell
     */
    Boolean getBoolean(final String columnName);

    /**
     * Get the cell value as boolean
     * @param idx the index of the column
     * @return the boolean value of the cell
     */
    Boolean getBoolean(final int idx);

    /**
     * Get the cell value as long
     * @param columnName the name of the column
     * @return the long value of the cell
     */
    Long getLong(final String columnName);

    /**
     * Get the cell value as long
     * @param idx the index of the column
     * @return the long value of the cell
     */
    Long getLong(final int idx);

    /**
     * Check if the cell value is null.
     * If the actual value of the cell is NULL, this can be a false positive.
     * @param columnName the name of the column
     * @return true when null
     */
    boolean isNull(final String columnName);

    /**
     * Check if the cell value is null.
     * If the actual value of the cell is NULL, this can be a false positive.
     * @param idx the index of the column
     * @return true when null
     */
    boolean isNull(final int idx);
}
