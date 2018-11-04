package kr.revelope.demo.spring.cloud.confserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfservApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfservApplication.class, args);
	}
}
