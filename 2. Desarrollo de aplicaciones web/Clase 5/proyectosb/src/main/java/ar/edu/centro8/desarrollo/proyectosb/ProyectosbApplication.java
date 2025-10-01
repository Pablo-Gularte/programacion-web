package ar.edu.centro8.desarrollo.proyectosb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProyectosbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectosbApplication.class, args);
	}

}
