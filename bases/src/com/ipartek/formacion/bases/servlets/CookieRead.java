package com.ipartek.formacion.bases.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie-read")
public class CookieRead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String idioma = null;

		Cookie[] cookies = request.getCookies();

		if (cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals("idioma")) {
					idioma = c.getName();
					String value = c.getValue();
					out.println(idioma + " = " + value);
					break;
				}
			}
		} else {
			out.println("*** No hay cookie que mostrar ***");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
		response.sendRedirect("cookie-read");
	}

}
