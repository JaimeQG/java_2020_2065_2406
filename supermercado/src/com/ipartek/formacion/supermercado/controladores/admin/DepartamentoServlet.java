package com.ipartek.formacion.supermercado.controladores.admin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.supermercado.accesodatos.Dao;
import com.ipartek.formacion.supermercado.controladores.Configuracion;
import com.ipartek.formacion.supermercado.modelos.Departamento;

@WebServlet("/admin/departamento")
public class DepartamentoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(DepartamentoServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Recoger información de la petición

		String id = request.getParameter("id");

		// 2. Poner información dentro de un modelo
		// 3. Tomar decisiones según lo recibido

		if (id != null) {
			Dao<Departamento> dao = Configuracion.daoDepartamentos;

			Departamento departamento = dao.obtenerPorId(Long.parseLong(id));

			// 4. Generar modelo para la vista

			request.setAttribute("departamento", departamento);
		}

		// 5. Redirigir a otra vista
		request.getRequestDispatcher("/WEB-INF/vistas/admin/departamento.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Cambiar codificación de entrada de datos de formulario de Windows-1252 a UTF8

		request.setCharacterEncoding("utf-8");

		// 1. Recoger información de la petición

		String id = request.getParameter("id");
		String nombre = request.getParameter("departamento-nombre");
		String descripcion = request.getParameter("departamento-descripcion");

		// 2. Poner información dentro de un modelo

		Departamento departamento = new Departamento(id, nombre, descripcion);

		LOGGER.log(Level.INFO, departamento.toString());

		// 3. Tomar decisiones según lo recibido
		/*
		 * 
		 * if (!departamento.isCorrecto()) { // 4. Generar modelo para la vista
		 * request.setAttribute("producto", producto);
		 * 
		 * Dao<Departamento> dao = Configuracion.daoDepartamentos;
		 * Iterable<Departamento> departamentos = dao.obtenerTodos();
		 * 
		 * // Iterable<Departamento> departamentos = //
		 * Configuracion.daoDepartamentos.obtenerTodos();
		 * 
		 * request.setAttribute("departamentos", departamentos);
		 * 
		 * // 5. Redirigir a otra vista
		 * request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(
		 * request, response); return; }
		 */

		Dao<Departamento> dao = Configuracion.daoDepartamentos;

		String mensaje;

		if (departamento.getId() == null) {
			// Si no está rellenado el id, es que queremos añadir
			dao.crear(departamento);

			mensaje = "Se ha creado el departamento correctamente";
		} else {
			// Si está rellenado el id, es que queremos modificar
			dao.modificar(departamento);

			mensaje = "Se ha modificado el departamento correctamente";
		}

		// 4. Generar modelo para la vista

		request.setAttribute("alertaTexto", mensaje);
		request.setAttribute("alertaNivel", "success");

		// 5. Redirigir a otra vista

		request.getRequestDispatcher("/admin/indexDpto").forward(request, response);
	}

}