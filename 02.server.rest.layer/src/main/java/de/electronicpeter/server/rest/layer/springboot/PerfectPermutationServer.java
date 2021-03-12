package de.electronicpeter.server.rest.layer.springboot;

import de.electronicpeter.server.rest.layer.config.PerfectPermutationAnnotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@PerfectPermutationAnnotation
public class PerfectPermutationServer {
    public static void main(String[] args) { SpringApplication.run(PerfectPermutationServer.class, args); }
}
