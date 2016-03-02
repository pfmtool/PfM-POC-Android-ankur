package cs.dartmouth.edu.gcmdemo.backend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs.dartmouth.edu.gcmdemo.backend.data.Contact;
import cs.dartmouth.edu.gcmdemo.backend.data.ContactDatastore;

public class QueryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String name = req.getParameter("name");
		ArrayList<Contact> result = ContactDatastore.query(name);
		req.setAttribute("result", result);
		getServletContext().getRequestDispatcher("/query_result.jsp").forward(
				req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
