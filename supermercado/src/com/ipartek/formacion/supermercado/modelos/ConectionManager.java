package com.ipartek.formacion.supermercado.modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionManager {

	private static final String PATH = "C:\\Users\\usuario\\Desktop\\ddbb/perrera.db";

	static public Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:" + PATH);

	}
}
