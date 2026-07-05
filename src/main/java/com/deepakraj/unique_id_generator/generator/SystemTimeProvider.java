package com.deepakraj.unique_id_generator.generator;

import java.time.Instant;

/**
 * Default implementation that retrieves the current UTC time.
 */
public class SystemTimeProvider implements TimeProvider {

    @Override
    public long currentTimeMillis() {
        return Instant.now().toEpochMilli();
    }

}
