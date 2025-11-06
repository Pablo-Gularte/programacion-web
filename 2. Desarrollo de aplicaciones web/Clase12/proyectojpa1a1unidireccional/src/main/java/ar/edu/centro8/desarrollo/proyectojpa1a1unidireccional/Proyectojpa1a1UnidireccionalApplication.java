package ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Proyectojpa1a1UnidireccionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(Proyectojpa1a1UnidireccionalApplication.class, args);
		System.out.println("🚗 Aplicación OneToOne UNIDIRECCIONAL iniciada correctamente!");
		System.out.println("📖 Solo Auto conoce a Motor - Motor NO conoce a Auto");
	}

}