package com.PfM_Demo_Backend;

import com.PfM_Demo_Backend.data.PortfolioDatastore;
import com.PfM_Demo_Backend.data.UserDatastore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by CODEMUD on 2/27/2016.
 */
public class DeletePortfolioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String portfolioName = req.getParameter("portfolioName");
        PortfolioDatastore.delete(portfolioName);
        resp.sendRedirect("/queryPortfolio.do");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }
}
