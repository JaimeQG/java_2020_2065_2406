package com.ipartek.formacion.bases.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie-leer")
public class CookieLeer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = leerCookie(request, "demo");

		response.setContentType("text/plain");
		response.getWriter().println(name);
	}

	private String leerCookie(HttpServletRequest request, String nombre) {
		Cookie[] cookies = request.getCookies();

		String name = null;

		for (Cookie c : cookies) {
			if (nombre.equals(c.getName())) { // c.getName().equals("demo")

				name = c.getName();
				// String value = c.getValue();
				break;
			}
		}
		return name;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
