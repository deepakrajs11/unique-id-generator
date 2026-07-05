package com.deepakraj.unique_id_generator.properties;

import com.deepakraj.unique_id_generator.enums.IdGenerationAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "id-generator")
public class IdGeneratorProperties {

    /**
     * Algorithm to use for ID generation.
     */
    private IdGenerationAlgorithm algorithm;

    /**
     * Unique worker identifier.
     */
    private long workerId;

    /**
     * Unique datacenter identifier.
     */
    private long datacenterId;

    /**
     * Custom epoch in milliseconds.
     */
    private long customEpoch;

    public IdGenerationAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(IdGenerationAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

    public long getCustomEpoch() {
        return customEpoch;
    }

    public void setCustomEpoch(long customEpoch) {
        this.customEpoch = customEpoch;
    }
}
