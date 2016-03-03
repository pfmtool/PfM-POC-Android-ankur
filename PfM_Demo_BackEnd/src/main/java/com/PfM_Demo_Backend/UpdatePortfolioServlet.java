package com.PfM_Demo_Backend;

import com.PfM_Demo_Backend.data.Portfolio;
import com.PfM_Demo_Backend.data.PortfolioDatastore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdatePortfolioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String portfolioName = req.getParameter("portfolioName");
        String cusip = req.getParameter("cusip");
        String opendate  = req.getParameter("opendate");
        String expdate = req.getParameter("expdate");
        String desc = req.getParameter("desc");
        String code = req.getParameter("code");

        if (portfolioName != null && !portfolioName.equals("")) {
            Portfolio portfolio = new Portfolio(portfolioName, cusip, code, opendate,expdate, desc );
            PortfolioDatastore.update(portfolio);
        }

        resp.sendRedirect("/queryPortfolio.do");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }

}