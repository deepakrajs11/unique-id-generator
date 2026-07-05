package com.deepakraj.unique_id_generator.generator;

/**
 * Contract for generating unique identifiers.
 *
 * Different algorithms (Twitter Snowflake, UUID v7, ULID, etc.)
 * can implement this interface.
 */
public interface UniqueIdGenerator {

    /**
     * Generates the next unique identifier.
     *
     * @return unique identifier
     */
    long generate();

}