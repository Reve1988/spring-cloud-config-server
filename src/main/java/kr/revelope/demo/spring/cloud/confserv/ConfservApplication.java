package kr.revelope.demo.spring.cloud.confserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * http://cloud.spring.io/spring-cloud-config/2.0.x/multi/multi__spring_cloud_config_server.html
 */
@SpringBootApplication
@EnableConfigServer
public class ConfservApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfservApplication.class, args);
	}
}
