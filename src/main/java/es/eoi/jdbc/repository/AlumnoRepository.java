package es.eoi.jdbc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.eoi.jdbc.entity.Alumno;

public class AlumnoRepository {

	private Connection openConnection() {
		String url = "jdbc:mysql://localhost:3306/db_alumnos?serverTimezone=UTC";
		String user = "root";
		String pass = "1234";
		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conexion;
	}

	public Alumno findByDni(String dni) {

		Alumno alumno = null;
		try {
			Connection conexion = openConnection();

			PreparedStatement pst = conexion.prepareStatement("SELECT * FROM ALUMNO WHERE DNI=?");
			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();
			if (rs.next() == true) {
				dni = rs.getString("DNI");
				String nombre = rs.getString("NOMBRE");
				String apellidos = rs.getString("APELLIDOS");
				int edad = rs.getInt("EDAD");
				alumno = new Alumno(dni, nombre, apellidos, edad);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return alumno;
	}

	public List<Alumno> findAllAlumnos() {

		List<Alumno> listaAlumnos = new ArrayList<Alumno>();

		try {
			Connection conexion = openConnection();

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM ALUMNO");

			while (rs.next()) {
				String dni = rs.getString("DNI");
				String nombre = rs.getString("NOMBRE");
				String apellidos = rs.getString("APELLIDOS");
				int edad = rs.getInt("EDAD");

				Alumno alum = new Alumno(dni, nombre, apellidos, edad);
				listaAlumnos.add(alum);
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaAlumnos;

	}

	public boolean createAlumno(Alumno alumno) {

		boolean resul = false;

		try {
			Connection conexion = openConnection();
			String query = "INSERT INTO ALUMNO (DNI,NOMBRE,APELLIDOS,EDAD) VALUES (?,?,?,?)";

			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setString(1, alumno.getDni());
			pst.setString(2, alumno.getNombre());
			pst.setString(3, alumno.getApellidos());
			pst.setInt(4, alumno.getEdad());

			// ResultSet rs = pst.executeQuery();
			int rs = pst.executeUpdate();

			if (rs > 0) {
				System.out.println("Se ha insertado el alumno");
			} else {
				System.out.println("Se produjo un error al insertar el alumno");
			}

			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resul;

	}

	public boolean updateAlumno(Alumno alumno) {
		boolean resul = false;

		try {
			Connection conexion = openConnection();
			String query = "UPDATE alumno SET NOMBRE=?, APELLIDOS=?, EDAD=? WHERE DNI=?";

			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setString(1, alumno.getNombre());
			pst.setString(2, alumno.getApellidos());
			pst.setInt(3, alumno.getEdad());
			pst.setString(4, alumno.getDni());

			int rs = pst.executeUpdate();

			if (rs > 0) {
				System.out.println("Se ha modificado el alumno");
			} else {
				System.out.println("Se produjo un error al modificar el alumno");
			}

			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resul;

	}


	public boolean deleteAlumno(Alumno alumno) {
		
		boolean resul = false;

		try {
			Connection conexion = openConnection();
			String query = "DELETE FROM alumno WHERE DNI=?";

			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setString(1, alumno.getDni());

			int rs = pst.executeUpdate();

			if (rs > 0) {
				System.out.println("Se ha eliminado el alumno "+alumno.getNombre()+" "+ alumno.getApellidos());
			} else {
				System.out.println("Se produjo un error al eliminar el alumno");
			}

			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resul;

	}
	
	public boolean existsByDni(Alumno alumno) {
		boolean resul = true;
		
		try {
		Connection conexion = openConnection();
		String query = "SELECT * FROM alumno WHERE dni=?";
		
		PreparedStatement pst = conexion.prepareStatement(query);
		pst.setString(1, alumno.getDni());
			
		ResultSet rs = pst.executeQuery();
		if (rs.next() == true) {
			resul = true;
		}else {
			resul = false;
		}

		
		} catch (SQLException e) {
			e.printStackTrace();
			resul = true;
		}
		return resul;
		
		
	}



}
