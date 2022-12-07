package br.com.dpaulla.mail.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import br.com.dpaulla.mail.server.services.TasksService;

@SpringBootApplication
@EnableConfigurationProperties(TasksProperties.class)
@ComponentScan("br.com.dpaulla")
public class DPaullaMailServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DPaullaMailServerApplication.class, args);
	}

    @Bean
    CommandLineRunner init(TasksService tasksService) {
        return (args) -> {
        	tasksService.init();
        };
    }
	
}