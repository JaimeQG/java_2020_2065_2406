package com.ipartek.formacion.libreria.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.libreria.accesodatos.UsuarioDaoTreeMap;
import com.ipartek.formacion.libreria.modelos.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");

		UsuarioDaoTreeMap dao = UsuarioDaoTreeMap.getInstancia();
		Usuario usuario = dao.obtenerPorNombre(nombre);

		if (usuario != null && usuario.getPassword().equals(password)) {
			request.getSession().setAttribute("usuario", usuario);
			// request.getRequestDispatcher("/admin/index").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/admin/index");
		} else {
			request.setAttribute("alertaTexto",
					"El nombre de usuario o la contraseña son incorrectos. Inténtelo de nuevo");
			request.setAttribute("alertaNivel", "danger");
			request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
		}
	}

}
