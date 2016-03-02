package com.PfM_Demo_Backend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PfM_Demo_Backend.data.User;
import com.PfM_Demo_Backend.data.UserDatastore;

public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String name = req.getParameter("uName");
		String pass = req.getParameter("pass");


		if (name == null || name.equals("")) {
			req.setAttribute("_retStr", "invalid input");
			getServletContext().getRequestDispatcher("/query_result.jsp")
					.forward(req, resp);
			return;
		}

		User user = new User(name, pass);
		boolean ret = UserDatastore.add(user);
		if (ret) {
			req.setAttribute("_retStr", "Add user " + name + " succ");
			MessagingEndpoint msg = new MessagingEndpoint();
			msg.sendMessage("Added");

			ArrayList<User> result = new ArrayList<User>();
			result.add(user);
			req.setAttribute("result", result);
		} else {
			req.setAttribute("_retStr", name + " exists");
		}

		getServletContext().getRequestDispatcher("/query_result.jsp").forward(
				req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}

}
