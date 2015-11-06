package com.lovelife.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.javlib.util.Util;
import com.lovelife.model.UserDTO;
import com.lovelife.service.LoginService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) {
		UserDTO attemptedUser = new UserDTO();
		UserDTO authenticatedUser = new UserDTO();
		attemptedUser.setUsername(request.getParameter("username"));
		attemptedUser.setPassword(request.getParameter("password"));
		LoginService loginService = new LoginService();
		String path = "";
		authenticatedUser = loginService.loginUser(attemptedUser);
		if (!Util.isEmptyOrNull(authenticatedUser.getFullName())) {
			HttpSession session = request.getSession();
			session.setAttribute("Authenticated_User", authenticatedUser);
			response.setHeader("Cache-Control", "no-cache, no-store,must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			path = "/home.html";
		} else {
			path = "/error.html";
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
