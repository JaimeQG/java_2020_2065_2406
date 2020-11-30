package com.ipartek.formacion.bases.uf2406;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie-ejercicio")
public class CookieEjercicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Cookie[] cookies = request.getCookies();

		if (cookies.length > 0) {
			for (Cookie c : cookies) {
				out.println(c.getName() + " = " + c.getValue());
			}
		} else {
			out.println("*** No hay cookie que mostrar ***");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		String valor = request.getParameter("color");

		if (nombre != null && nombre.length() > 0) {
			Cookie cookie = new Cookie(nombre, valor);
			cookie.setMaxAge(Integer.MAX_VALUE); // Integer.MAX_VALUE
			response.addCookie(cookie);
		}

		response.sendRedirect("cookie-ejercicio");
	}

}
