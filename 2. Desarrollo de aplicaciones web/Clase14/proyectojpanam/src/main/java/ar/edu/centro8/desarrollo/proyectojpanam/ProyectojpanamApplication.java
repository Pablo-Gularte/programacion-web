package ar.edu.centro8.desarrollo.proyectojpanam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectojpanamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectojpanamApplication.class, args);
	}

}

//IMPLEMENTACION
// @SpringBootApplication
// public class ProyectojpanamApplication implements CommandLineRunner {

// 	@Autowired
// 	EstudianteRepository estudianteRepository;

// 	@Autowired
// 	CursoRepository cursoRepository;

// 	public static void main(String[] args) {
// 		SpringApplication.run(ProyectojpanamApplication.class, args);
// 	}

// 	@Override
// 	public void run(String... args) throws Exception {
// 		Curso c1 = new Curso("Java");
// 		Curso c2 = new Curso("Python");
		
// 		Estudiante e1 = new Estudiante("Juan");
// 		Estudiante e2 = new Estudiante("Maria");

	
// 		cursoRepository.save(c1);
// 		cursoRepository.save(c2);

// 		//El estudiante 1 estara en ambos cursos(Java y Phyton)
// 		List<Curso> cursosEstudiante1 = new ArrayList<>();
// 		cursosEstudiante1.add(c1);
// 		cursosEstudiante1.add(c2);
// 		e1.setCursos(cursosEstudiante1);

// 		//El estudiante 2 estara solo en el curso de Java
// 		List<Curso> cursosEstudiante2 = new ArrayList<>();
// 		cursosEstudiante2.add(c1);
// 		e2.setCursos(cursosEstudiante2);

// 		estudianteRepository.save(e1);
// 		estudianteRepository.save(e2);

// 		//compilar y ejecutar las queries para testear
// 		// select * from estudiantes;
// 		// select * from cursos;
// 		// select * from estudiante_curso;
		
// 	}


// }




