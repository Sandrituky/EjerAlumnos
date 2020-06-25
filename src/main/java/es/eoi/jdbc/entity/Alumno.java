package es.eoi.jdbc.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {
	
	private String dni;
	private String nombre;
	private String apellidos;
	private int edad;
	
	
	
	public Alumno(String dni, String nombre, String apellidos, int edad) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
	}

	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public static boolean checkDNI(String dni) {

		boolean correcto = false;

		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

		Matcher matcher = pattern.matcher(dni);

		if (matcher.matches()) {

			String letra = matcher.group(2);

			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

			int index = Integer.parseInt(matcher.group(1));

			index = index % 23;

			String reference = letras.substring(index, index + 1);

			if (reference.equalsIgnoreCase(letra)) {

				correcto = true;

			} else {
				correcto = false;
			}
			
		} else {
			correcto = false;
		}
		return correcto;
	}

	@Override
	public String toString() {
		return "\nAlumno [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + "]";
	}
	
	
	
	
	
	
	
	

}
