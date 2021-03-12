package de.electronicpeter.server.rest.layer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"de.electronicpeter.server.rest.layer", "de.electronicpeter.server.service.layer"})
public class PerfectPermutationConfig {
}
