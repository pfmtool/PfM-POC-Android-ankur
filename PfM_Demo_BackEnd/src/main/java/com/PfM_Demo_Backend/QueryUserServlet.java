package com.PfM_Demo_Backend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PfM_Demo_Backend.data.User;
import com.PfM_Demo_Backend.data.UserDatastore;

public class QueryUserServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String portfolioName = req.getParameter("portfolioName");
		ArrayList<User> result = UserDatastore.query(portfolioName);
		req.setAttribute("result", result);
		getServletContext().getRequestDispatcher("/query_result.jsp").forward(
				req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
