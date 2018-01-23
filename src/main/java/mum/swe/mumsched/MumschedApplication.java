package mum.swe.mumsched;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("mum.swe.mumsched")
public class MumschedApplication {

	public static void main(String[] args) {
		SpringApplication.run(MumschedApplication.class, args);
	}	
}
