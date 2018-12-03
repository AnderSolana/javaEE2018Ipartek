package com.ipartek.formacion.ejemplocapas.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.ejemplocapas.pojos.PojoException;
import com.ipartek.formacion.ejemplocapas.pojos.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Usuario usuario; 
		try {
			usuario = new Usuario(null, email, password);
		} catch(PojoException e) {
			//response.sendRedirect("login.jsp");
			request.setAttribute("error", "Error en el formato de email o contraseña");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		if ("javier@lete.com".equals(usuario.getEmail()) && "Pa$$w0rd".equals(usuario.getPassword())) {
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			
			usuarios.add(new Usuario(1L, "ander@email.com", "Pa$$w0rd"));
			usuarios.add(new Usuario(2L, "pedro@email.com", "Pa$$w0rd"));
			
			request.setAttribute("usuarios", usuarios);
			
			request.getSession().setAttribute("usuario", usuario);
			// Ir a la página indicada y llevarse todos los datos
			request.getRequestDispatcher("principal.jsp").forward(request, response);
		}else {
			request.setAttribute("error", "No tienes permiso para login");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			//response.sendRedirect("login.jsp");
		}
		// response.getWriter().println("Hola " + usuario.getEmail());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
