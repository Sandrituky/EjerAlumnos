package src.main.java;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.service.AlumnoService;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class GestionInstituto {
	
	static AlumnoService alumService = new AlumnoService();
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		menuManager();
		
		
	}
	
	
	public static void menuManager() {
		

		System.out.println("\nSelecciona una opción");
		System.out.println("1. Listado alumnos");
		System.out.println("2. Buscar alumno (DNI)");
		System.out.println("3. Crear alumno");
		System.out.println("4. Modificar alumno");
		System.out.println("5. Eliminar alumno");
		System.out.println("0. Salir");
		
		int opcion = scan.nextInt();
		
		switch(opcion) {
		
		case 1:
			listarAlumnos();
			menuManager();
			
			break;
			
		case 2:
			findAlumno();
			menuManager();
			
			
			break;
			
		case 3:
			addAlumno();
			menuManager();
			
			break;
			
		case 4:
			modAlumno();
			menuManager();
			
			
			break;
			
		case 5:
			deleteAlumno();
			menuManager();
			
			break;
		case 0:
			System.exit(0);
			break;
		
		
		}
		
		
	}
	
	public static void listarAlumnos() {
		System.out.println("Listado de alumnos:");
		System.out.println(alumService.listAlumnos().toString());
	}
	
	public static void findAlumno() {
		System.out.println("Introduzca el DNI del alumno que quiera buscar");
		String dni = scan.next();
		Alumno alumno = alumService.findAlumnoByDni(dni);
		if(alumno!=null) {
			System.out.println(alumno.toString());
		}else {
			System.out.println("No existe ningún alumno con el DNI "+dni);
		}
	}
	
	public static void addAlumno() {
		System.out.println("Bienvenido al gestor de creación de alumnos. Introduzca sus datos:");
		
		System.out.print("DNI: ");
		String dni = scan.next();
		if((Alumno.checkDNI(dni)==false)) {
			System.out.println("El DNI introducido es incorrecto");
			menuManager();
		}
		
		System.out.print("Nombre: ");
		scan.nextLine();
		String nombre = scan.next();
		
		System.out.print("Apellidos: ");
		scan.nextLine();
		String apellidos = scan.nextLine();
		
		System.out.print("Edad: ");
		int edad = scan.nextInt();
		
		Alumno alumnoCreate = new Alumno(dni, nombre, apellidos, edad);
		alumService.addAlumno(alumnoCreate);
	}
	
	public static void deleteAlumno() {
		System.out.println("Introduzca el DNI del alumno que quiera eliminar del sistema");
		String dni = scan.next();
		Alumno alumno = alumService.findAlumnoByDni(dni);
		if(alumno!=null) {
			System.out.println(alumno.toString());
			alumService.deleteAlumno(alumno);
		}else {
			System.out.println("No existe ningún alumno con el DNI "+dni);
		}
		
	}
	
	public static void modAlumno() {
		System.out.println("Introduzca el DNI del alumno que quiera modificar");
		String dni = scan.next();
		Alumno alumno = alumService.findAlumnoByDni(dni);
		if(alumno!=null) {	
			System.out.println(alumno.toString());
			System.out.println("¿Que dato deseas modificar?");
			System.out.println("{Nombre, Apellidos, Edad}");
			String opcion = "";
			opcion = scan.next();
			switch(opcion.toUpperCase()) {
			case "NOMBRE":
				System.out.println("Nombre: ");
				scan.nextLine();
				String nombre = scan.nextLine();
				alumno.setNombre(nombre);
				break;
				
			case "APELLIDOS": case "APELLIDO":
				System.out.print("Apellido: ");
				scan.nextLine();
				String apellidos = scan.nextLine();
				alumno.setApellidos(apellidos);
				
				break;
				
			case "EDAD":
				System.out.print("Edad: ");
				int edad = scan.nextInt();
				alumno.setEdad(edad);
				break;
			default:
				System.out.println("No se introdujo una opción correcta");
				menuManager();
				break;
				
			}
			
			alumService.updateAlumno(alumno);
			
			
		}else {
			System.out.println("No existe ningún alumno con el DNI "+dni);
		}
		
	}
	
	

}
