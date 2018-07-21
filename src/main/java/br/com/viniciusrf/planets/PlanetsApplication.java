package br.com.viniciusrf.planets;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableAsync
public class PlanetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanetsApplication.class, args);
	}
	
	 @Bean
	  public Executor asyncExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(2);
	    executor.setMaxPoolSize(5);
	    executor.setQueueCapacity(500);
	    executor.initialize();
	    return executor;
	  }
	  
}
