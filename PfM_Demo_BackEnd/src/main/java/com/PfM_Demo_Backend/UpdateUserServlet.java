package com.PfM_Demo_Backend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PfM_Demo_Backend.data.User;
import com.PfM_Demo_Backend.data.UserDatastore;

public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");


		if (name != null && !name.equals("")) {
			User user = new User(name, pass);
			UserDatastore.update(user);
		}

		resp.sendRedirect("/queryUser.do");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}

}
