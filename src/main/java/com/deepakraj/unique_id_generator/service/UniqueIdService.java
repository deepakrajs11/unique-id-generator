package com.deepakraj.unique_id_generator.service;

import com.deepakraj.unique_id_generator.generator.UniqueIdGenerator;
import org.springframework.stereotype.Service;

@Service
public class UniqueIdService {

    private final UniqueIdGenerator uniqueIdGenerator;

    public UniqueIdService(UniqueIdGenerator uniqueIdGenerator) {
        this.uniqueIdGenerator = uniqueIdGenerator;
    }

    public long generateId() {
        return uniqueIdGenerator.generate();
    }
}