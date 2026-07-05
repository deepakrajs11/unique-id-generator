package com.deepakraj.unique_id_generator.generator;

/**
 * Abstraction for retrieving the current time.
 */
public interface TimeProvider {

    /**
     * Returns the current timestamp in milliseconds.
     *
     * @return current time in milliseconds
     */
    long currentTimeMillis();

}