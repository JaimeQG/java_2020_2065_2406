package com.ipartek.formacion.supermercado.mysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ipartek.formacion.supermercado.accesodatos.AccesoDatosException;

public class EjemploProcAlmacenado {

	private static final String URL = "jdbc:mysql://localhost:3306/supermercado?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "";

	private static final String SQL_PROCEDIMIENTO = "{call ObtenerDatosProducto(?)}";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC para MySQL", e);
		}
	}

	public static void main(String[] args) {

		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		long id = -1;
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASS);

			// Carga el driver de MySQL
			// Conecta con la base de datos
			// Llamada al procedimiento almacenado
			CallableStatement cst = con.prepareCall(SQL_PROCEDIMIENTO);

			do {
				System.out.println("\nIntroduce el ID del producto:");
				try {
					id = Long.parseLong(entrada.readLine());
				} catch (IOException ex) {
					System.out.println("Error...");
				}

				// Parametro 1 del procedimiento almacenado
				cst.setLong(1, id);

				// Definimos los tipos de los parametros de salida del procedimiento almacenado
				// cst.registerOutParameter(2, java.sql.Types.VARCHAR);

				// Ejecuta el procedimiento almacenado
				// Se obtienen la salida del procedimineto almacenado
				try (ResultSet rs = cst.executeQuery()) {

					if (rs.next()) {
						String nombre = rs.getString("nombre");
						System.out.println("Nombre: " + nombre);
					}
				}

			} while (id != 0);

		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		System.out.println("Adios ...0");
	}
}
