package com.deepakraj.unique_id_generator;

import com.deepakraj.unique_id_generator.properties.IdGeneratorProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(IdGeneratorProperties.class)
public class UniqueIdGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniqueIdGeneratorApplication.class, args);
	}
}