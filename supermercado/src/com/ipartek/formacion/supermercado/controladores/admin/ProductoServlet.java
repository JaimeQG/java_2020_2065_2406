package com.ipartek.formacion.supermercado.controladores.admin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ipartek.formacion.supermercado.accesodatos.Dao;
import com.ipartek.formacion.supermercado.controladores.Configuracion;
import com.ipartek.formacion.supermercado.modelos.Departamento;
import com.ipartek.formacion.supermercado.modelos.Producto;

@WebServlet("/admin/producto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProductoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProductoServlet.class.getName());

	private static final String UPLOAD_DIRECTORY = "product_imgs";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Recoger información de la petición

		String id = request.getParameter("id");

		// 2. Poner información dentro de un modelo
		// 3. Tomar decisiones según lo recibido

		if (id != null) {
			Dao<Producto> dao = Configuracion.daoProductos;

			Producto producto = dao.obtenerPorId(Long.parseLong(id));

			// 4. Generar modelo para la vista

			request.setAttribute("producto", producto);
		}

		Iterable<Departamento> departamentos = Configuracion.daoDepartamentos.obtenerTodos();

		request.setAttribute("departamentos", departamentos);

		// 5. Redirigir a otra vista
		request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Cambiar codificación de entrada de datos de formulario de Windows-1252 a UTF8

		request.setCharacterEncoding("utf-8");

		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();

		// 1. Recoger información de la petición

		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		// Departamento
		String departamentoId = request.getParameter("departamento");

		String urlImagen = null; // = request.getParameter("imagen");
		String urlImagenAnterior = request.getParameter("imagenAnterior");
		String descripcion = request.getParameter("descripcion");
		String precio = request.getParameter("precio");
		String cantidad = request.getParameter("cantidad");
		String unidadMedida = request.getParameter("unidad-medida");
		String precioUnidadMedida = request.getParameter("precio-unidad-medida");
		String descuento = request.getParameter("descuento");

		String nombreFichero = null;

		for (Part part : request.getParts()) {
			nombreFichero = part.getSubmittedFileName();

			LOGGER.info("Nombre de fichero: [" + nombreFichero + "]");

			if (nombreFichero != null && nombreFichero.trim().length() > 0) {
				LOGGER.info("Nombre de fichero ACEPTADO: [" + nombreFichero + "]");
				LOGGER.info(uploadPath + File.separator + nombreFichero);
				part.write(uploadPath + File.separator + nombreFichero);

				urlImagen = nombreFichero;
			}
		}

		if (urlImagen == null) {
			urlImagen = urlImagenAnterior;
		}

		// 2. Poner información dentro de un modelo

		Producto producto = new Producto(id, nombre, descripcion, urlImagen, precio, descuento, unidadMedida,
				precioUnidadMedida, cantidad);

		Long departamentoIdLong = Long.parseLong(departamentoId);

		if (departamentoIdLong == -1) {
			String nombreDepartamento = request.getParameter("departamento-nombre");
			String descripcionDepartamento = request.getParameter("departamento-descripcion");

			Departamento departamento = Configuracion.daoDepartamentos
					// .crearYObtener(new Departamento(null, nombreDepartamento,
					// descripcionDepartamento));
					.crearYObtener(new Departamento(0L, nombreDepartamento, descripcionDepartamento));

			departamentoIdLong = departamento.getId();
		}

		producto.setDepartamento(new Departamento(departamentoIdLong, null, null));

		LOGGER.log(Level.INFO, producto.toString());

		// 3. Tomar decisiones según lo recibido

		if (!producto.isCorrecto()) {
			// 4. Generar modelo para la vista
			request.setAttribute("producto", producto);

			Dao<Departamento> dao = Configuracion.daoDepartamentos;
			Iterable<Departamento> departamentos = dao.obtenerTodos();

			// Iterable<Departamento> departamentos =
			// Configuracion.daoDepartamentos.obtenerTodos();

			request.setAttribute("departamentos", departamentos);

			// 5. Redirigir a otra vista
			request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
			return;
		}

		Dao<Producto> dao = Configuracion.daoProductos;

		String mensaje;

		if (producto.getId() == null) {
			// Si no está rellenado el id, es que queremos añadir
			dao.crear(producto);

			mensaje = "Se ha creado el producto correctamente";
		} else {
			// Si está rellenado el id, es que queremos modificar
			dao.modificar(producto);

			mensaje = "Se ha modificado el producto correctamente";
		}

		// 4. Generar modelo para la vista

		request.setAttribute("alertaTexto", mensaje);
		request.setAttribute("alertaNivel", "success");

		// 5. Redirigir a otra vista

		request.getRequestDispatcher("/admin/index").forward(request, response);
	}

}