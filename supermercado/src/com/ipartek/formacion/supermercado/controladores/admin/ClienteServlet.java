package com.ipartek.formacion.supermercado.controladores.admin;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.supermercado.accesodatos.Dao;
import com.ipartek.formacion.supermercado.controladores.Configuracion;
import com.ipartek.formacion.supermercado.modelos.Cliente;

@WebServlet("/admin/cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger LOGGER = Logger.getLogger(ClienteServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Recoger información de la petición

		String id = request.getParameter("id");

		// 2. Poner información dentro de un modelo
		// 3. Tomar decisiones según lo recibido

		if (id != null) {
			// Dao<Cliente> dao = ClienteDaoTreeMap.getInstancia();
			Dao<Cliente> dao = Configuracion.daoClientes;

			Cliente cliente = dao.obtenerPorId(Long.parseLong(id));

			// 4. Generar modelo para la vista

			request.setAttribute("cliente", cliente);
		}

		// 5. Redirigir a otra vista
		request.getRequestDispatcher("/WEB-INF/vistas/admin/cliente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 1. Recoger información de la petición
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String cif = request.getParameter("cif");
		String fechaNacimiento = request.getParameter("fecha-nacimiento");

		// 2. Poner información dentro de un modelo
		Cliente cliente = new Cliente(id, nombre, apellidos, cif, fechaNacimiento);

		LOGGER.info(cliente.toString());

		// 3. Tomar decisiones según lo recibido
		if (!cliente.isCorrecto()) {
			// 4. Generar modelo para la vista
			request.setAttribute("cliente", cliente);
			// 5. Redirigir a otra vista
			request.getRequestDispatcher("/WEB-INF/vistas/admin/cliente.jsp").forward(request, response);
			return;
		}

		Dao<Cliente> dao = Configuracion.daoClientes;

		String mensaje;

		if (cliente.getId() == null) {
			// Si no está rellenado el id, es que queremos añadir
			dao.crear(cliente);

			mensaje = "Se ha creado el cliente correctamente";
		} else {
			// Si está rellenado el id, es que queremos modificar
			dao.modificar(cliente);

			mensaje = "Se ha modificado el cliente correctamente";
		}

		// 4. Generar modelo para la vista

		request.setAttribute("alertaTexto", mensaje);
		request.setAttribute("alertaNivel", "success");

		// 5. Redirigir a otra vista

		request.getRequestDispatcher("/admin/maestro").forward(request, response);
	}
	/*
	 * if (cliente.isCorrecto()) { LOGGER.info("Cliente correcto");
	 * response.sendRedirect(request.getContextPath() + "/admin/maestro"); } else {
	 * LOGGER.warning("Cliente INcorrecto"); request.setAttribute("cliente",
	 * cliente);
	 * request.getRequestDispatcher("/WEB-INF/vistas/admin/cliente.jsp").forward(
	 * request, response); }
	 */
}