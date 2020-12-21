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
import com.ipartek.formacion.supermercado.modelos.Producto;

@WebServlet("/admin/producto")
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProductoServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Recoger información de la petición

		String id = request.getParameter("id");

		// 2. Poner información dentro de un modelo
		// 3. Tomar decisiones según lo recibido

		if (id != null) {
			// Dao<Producto> dao = ProductoDaoTreeMap.getInstancia();
			Dao<Producto> dao = Configuracion.daoProductos;

			Producto producto = dao.obtenerPorId(Long.parseLong(id));

			// 4. Generar modelo para la vista

			request.setAttribute("producto", producto);
		}

		// 5. Redirigir a otra vista
		request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Cambiar codificación de entrada de datos de formulario de Windows-1252 a UTF8

		request.setCharacterEncoding("utf-8");

		// 1. Recoger información de la petición

		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String urlImagen = request.getParameter("imagen");
		String descripcion = request.getParameter("descripcion");
		String precio = request.getParameter("precio");
		String cantidad = request.getParameter("cantidad");
		String unidadMedida = request.getParameter("unidad-medida");
		String precioUnidadMedida = request.getParameter("precio-unidad-medida");
		String descuento = request.getParameter("descuento");

		// 2. Poner información dentro de un modelo

		Producto producto = new Producto(id, nombre, descripcion, urlImagen, precio, descuento, unidadMedida,
				precioUnidadMedida, cantidad);

		LOGGER.log(Level.INFO, producto.toString());

		// 3. Tomar decisiones según lo recibido

		if (!producto.isCorrecto()) {
			// 4. Generar modelo para la vista
			request.setAttribute("producto", producto);
			// 5. Redirigir a otra vista
			request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
			return;
		}

		// Dao<Producto> dao = ProductoDaoTreeMap.getInstancia();
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