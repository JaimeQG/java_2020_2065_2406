package com.ipartek.formacion.ifcd0112.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.ifcd0112.accesodatos.Dao;
import com.ipartek.formacion.ifcd0112.accesodatos.LibroDaoTreeMap;
import com.ipartek.formacion.ifcd0112.modelos.Libro;

@WebServlet("/libro")
public class LibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Recoger información de la petición

		String id = request.getParameter("id");

		// 2. Poner información dentro de un modelo
		// 3. Tomar decisiones según lo recibido

		if (id != null) {
			Dao<Libro> dao = LibroDaoTreeMap.getInstancia();

			Libro libro = dao.obtenerPorId(Long.parseLong(id));

			// 4. Generar modelo para la vista

			request.setAttribute("libro", libro);
		}

		// 5. Redirigir a otra vista
		request.getRequestDispatcher("/WEB-INF/vistas/libro.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Cambiar codificación de entrada de datos de formulario de Windows-1252 a UTF8
		request.setCharacterEncoding("utf-8");

		// 1. Recoger información de la petición

		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String precio = request.getParameter("precio");
		String descuento = request.getParameter("descuento");

		// 2. Poner información dentro de un modelo

		Libro libro = new Libro(id, nombre, precio, descuento);

		// 3. Tomar decisiones según lo recibido

		if (!libro.isCorrecto()) {
			// 4. Generar modelo para la vista
			request.setAttribute("libro", libro);
			// 5. Redirigir a otra vista
			request.getRequestDispatcher("/WEB-INF/vistas/libro.jsp").forward(request, response);
			return;
		}

		Dao<Libro> dao = LibroDaoTreeMap.getInstancia();

		String mensaje;

		if (libro.getId() == null) {
			// Si no está rellenado el id, es que queremos añadir
			dao.crear(libro);

			mensaje = "Se ha creado el libro " + libro.getNombre() + " correctamente";
		} else {
			// Si está rellenado el id, es que queremos modificar
			dao.modificar(libro);

			mensaje = "Se ha modificado el libro " + libro.getNombre() + " correctamente";
		}

		// 4. Generar modelo para la vista

		request.setAttribute("alertaTexto", mensaje);
		request.setAttribute("alertaNivel", "success");

		// 5. Redirigir a otra vista

		request.getRequestDispatcher("/principal").forward(request, response);
	}

}