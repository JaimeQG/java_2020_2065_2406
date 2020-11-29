package com.ipartek.formacion.bases.servlets.ejercicios;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie-idioma")
public class CookieIdiomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idioma = null;

		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("idioma")) {
				idioma = cookie.getValue();
				break;
			}
		}

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println(idioma);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idioma = request.getParameter("idioma");

		Cookie cookie = new Cookie("idioma", idioma);

		response.addCookie(cookie);

		response.sendRedirect("cookie-idioma");
	}

}
