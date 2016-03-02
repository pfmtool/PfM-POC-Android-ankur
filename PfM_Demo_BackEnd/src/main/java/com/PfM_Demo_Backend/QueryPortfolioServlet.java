package com.PfM_Demo_Backend;

import com.PfM_Demo_Backend.data.Portfolio;
import com.PfM_Demo_Backend.data.PortfolioDatastore;
import com.PfM_Demo_Backend.data.User;
import com.PfM_Demo_Backend.data.UserDatastore;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class QueryPortfolioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String name = req.getParameter("portfolioName");
        ArrayList<Portfolio> result = PortfolioDatastore.query(name);
        req.setAttribute("result", result);
        getServletContext().getRequestDispatcher("/query_result_portfolio.jsp").forward(
                req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }
}
