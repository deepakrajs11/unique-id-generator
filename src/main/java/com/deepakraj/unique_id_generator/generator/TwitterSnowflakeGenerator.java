package com.deepakraj.unique_id_generator.generator;

import com.deepakraj.unique_id_generator.properties.IdGeneratorProperties;

public class TwitterSnowflakeGenerator implements UniqueIdGenerator {

    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATACENTER_ID_BITS = 5L;

    private static final long MAX_WORKER_ID = (1L << WORKER_ID_BITS) - 1;
    private static final long MAX_DATACENTER_ID = (1L << DATACENTER_ID_BITS) - 1;
    private static final long SEQUENCE_MASK = (1L << SEQUENCE_BITS) - 1;

    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_SHIFT =
            SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    private final TimeProvider timeProvider;
    private final long workerId;
    private final long datacenterId;
    private final long customEpoch;

    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public TwitterSnowflakeGenerator(TimeProvider timeProvider,
                                     IdGeneratorProperties properties) {

        this.timeProvider = timeProvider;
        this.workerId = properties.getWorkerId();
        this.datacenterId = properties.getDatacenterId();
        this.customEpoch = properties.getCustomEpoch();

        validateConfiguration();
    }

    @Override
    public synchronized long generate() {

        long currentTimestamp = currentTimestamp();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException(
                    "System clock moved backwards. Refusing to generate id.");
        }

        if (currentTimestamp == lastTimestamp) {

            sequence = (sequence + 1) & SEQUENCE_MASK;

            if (sequence == 0) {
                currentTimestamp = waitUntilNextMillis(lastTimestamp);
            }

        } else {
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - customEpoch) << TIMESTAMP_SHIFT)
                | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    private long currentTimestamp() {
        return timeProvider.currentTimeMillis();
    }

    private long waitUntilNextMillis(long lastTimestamp) {

        long timestamp = currentTimestamp();

        while (timestamp <= lastTimestamp) {
            timestamp = currentTimestamp();
        }

        return timestamp;
    }

    private void validateConfiguration() {

        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException(
                    "Worker ID must be between 0 and " + MAX_WORKER_ID);
        }

        if (datacenterId < 0 || datacenterId > MAX_DATACENTER_ID) {
            throw new IllegalArgumentException(
                    "Datacenter ID must be between 0 and " + MAX_DATACENTER_ID);
        }

        if (customEpoch < 0) {
            throw new IllegalArgumentException(
                    "Custom epoch cannot be negative.");
        }
    }
}