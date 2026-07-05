package com.deepakraj.unique_id_generator.config;

import com.deepakraj.unique_id_generator.generator.SystemTimeProvider;
import com.deepakraj.unique_id_generator.generator.TimeProvider;
import com.deepakraj.unique_id_generator.generator.TwitterSnowflakeGenerator;
import com.deepakraj.unique_id_generator.generator.UniqueIdGenerator;
import com.deepakraj.unique_id_generator.properties.IdGeneratorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdGeneratorConfiguration {

    @Bean
    public TimeProvider timeProvider() {
        return new SystemTimeProvider();
    }

    @Bean
    public UniqueIdGenerator uniqueIdGenerator(TimeProvider timeProvider,
                                               IdGeneratorProperties properties) {

        return new TwitterSnowflakeGenerator(timeProvider, properties);
    }
}