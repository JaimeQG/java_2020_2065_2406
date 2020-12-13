package com.ipartek.formacion.libreria.controladores.admin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.libreria.accesodatos.Dao;
import com.ipartek.formacion.libreria.accesodatos.LibroDaoTreeMap;
import com.ipartek.formacion.libreria.modelos.Libro;

@WebServlet("/admin/libro")
public class LibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(LibroServlet.class.getName());

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
		request.getRequestDispatcher("/WEB-INF/vistas/admin/libro.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Cambiar codificación de entrada de datos de formulario de Windows-1252 a UTF8
		request.setCharacterEncoding("utf-8");

		// 1. Recoger información de la petición

		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String urlImagen = request.getParameter("urlImagen");
		String precio = request.getParameter("precio");
		String descuento = request.getParameter("descuento");
		String autor = request.getParameter("autor");

		// 2. Poner información dentro de un modelo

		Libro libro = new Libro(id, nombre, urlImagen, precio, descuento, autor);

		LOGGER.log(Level.INFO, libro.toString());

		// 3. Tomar decisiones según lo recibido

		if (!libro.isCorrecto()) {
			// 4. Generar modelo para la vista
			request.setAttribute("libro", libro);
			// 5. Redirigir a otra vista
			request.getRequestDispatcher("/WEB-INF/vistas/admin/libro.jsp").forward(request, response);
			return;
		}

		Dao<Libro> dao = LibroDaoTreeMap.getInstancia();

		String mensaje;

		if (libro.getId() == null) {
			// Si no está rellenado el id, es que queremos añadir
			dao.crear(libro);

			mensaje = "Se ha creado el producto correctamente";
		} else {
			// Si está rellenado el id, es que queremos modificar
			dao.modificar(libro);

			mensaje = "Se ha modificado el producto correctamente";
		}

		// 4. Generar modelo para la vista

		request.setAttribute("alertaTexto", mensaje);
		request.setAttribute("alertaNivel", "success");

		// 5. Redirigir a otra vista

		request.getRequestDispatcher("/admin/index").forward(request, response);
	}

}