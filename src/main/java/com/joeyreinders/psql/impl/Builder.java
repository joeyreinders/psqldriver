package com.joeyreinders.psql.impl;

/**
 * Builder interface, constructs an object of type T
 * @param <T> type to build
 */
interface Builder<T> {
    /**
     * Returns a new object of type T
     */
    T build();
}
