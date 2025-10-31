package ar.edu.centro8.desarrollo.proyectosb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Proyectosb2Application {

	public static void main(String[] args) {
		SpringApplication.run(Proyectosb2Application.class, args);
	}

}
