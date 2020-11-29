package com.ipartek.formacion.bases.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sesion-read")
public class SesionRead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("email");
		String password = (String) session.getAttribute("password");

		/*
		 * response.setContentType("text/plain"); response.getWriter().println(email);
		 * response.getWriter().println(password);
		 */
		request.setAttribute("email", email);
		request.setAttribute("password", password);
		request.getRequestDispatcher("sesion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
