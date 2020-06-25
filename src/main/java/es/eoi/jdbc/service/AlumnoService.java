package es.eoi.jdbc.service;

import java.util.Collections;
import java.util.List;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.repository.AlumnoRepository;

public class AlumnoService {
	
	private AlumnoRepository alumnosRepo = new AlumnoRepository();
	
	
	public Alumno findAlumnoByDni(String dni) {
		
		Alumno alumno = alumnosRepo.findByDni(dni);
		

		
		return alumno;
		
	}
	
	
	public List<Alumno> listAlumnos() {

		List <Alumno> listaAlumnos = alumnosRepo.findAllAlumnos();
		
		if (!listaAlumnos.isEmpty()){
			return listaAlumnos;
		}else {
			return Collections.emptyList(); 
		}
		
	}
	
	public boolean addAlumno(Alumno alumno) {
		boolean resul = false;

		if(alumnosRepo.existsByDni(alumno)==true){
			resul=false;
		}else {
			List <Alumno> listaAlumnos = alumnosRepo.findAllAlumnos();
			alumnosRepo.createAlumno(alumno);
			listaAlumnos.add(alumno);
			resul=true;
		}
		return resul;
		
	}
	
	public boolean updateAlumno(Alumno alumno) {
		alumnosRepo.updateAlumno(alumno);
		return true;
	}
	
	public boolean deleteAlumno(Alumno alumno) {
		alumnosRepo.deleteAlumno(alumno);
		return true;
	}

}
