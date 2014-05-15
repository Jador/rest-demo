package demo.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * [Class Description Here]
 *
 * @author Tyler Graham
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"demo.patient.service", "demo.patient.controller"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
