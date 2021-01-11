package com.ipartek.formacion.supermercado.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.supermercado.modelos.Departamento;

public class DepartamentoDaoMySql implements Dao<Departamento> {
	private static final String URL = "jdbc:mysql://localhost:3306/supermercado?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "admin";

	private static final String SQL_SELECT = "SELECT * FROM departamentos";

	private DepartamentoDaoMySql() {
	}

	private final static DepartamentoDaoMySql INSTANCIA = new DepartamentoDaoMySql();

	public static DepartamentoDaoMySql getInstancia() {
		return INSTANCIA;
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC para MySQL", e);
		}
	}

	@Override
	public Iterable<Departamento> obtenerTodos() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT)) {

			ArrayList<Departamento> departamentos = new ArrayList<>();
			Departamento departamento;

			while (rs.next()) {
				departamento = new Departamento(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));

				departamentos.add(departamento);
			}

			return departamentos;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido consultar la lista de departamentos", e);
		}
	}

}
