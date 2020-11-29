package com.ipartek.formacion.bases.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie-escribir")
public class CookieEscribir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cookie cookie = new Cookie("demo", "decookie");
		cookie.setMaxAge(60 * 60 * 24 * 7); // Integer.MAX_VALUE
		response.addCookie(cookie);

		response.setContentType("text/plain");
		response.getWriter().println("Cookie escrita");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
