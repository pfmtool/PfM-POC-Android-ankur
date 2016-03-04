package com.PfM_Demo_Backend;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PfM_Demo_Backend.data.Portfolio;
import com.PfM_Demo_Backend.data.PortfolioDatastore;

public class AddPortfolioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String portfolioName = req.getParameter("portfolioName").trim();
        String cusip = req.getParameter("cusip").trim();
        String opendate  = req.getParameter("opendate").trim();
        String expdate = req.getParameter("expdate").trim();
        String desc = req.getParameter("desc").trim();
        String code = req.getParameter("code").trim();


        if (portfolioName == null || portfolioName.equals("")) {
            req.setAttribute("_retStr", "invalid input");
            getServletContext().getRequestDispatcher("/query_result_portfolio.jsp")
                    .forward(req, resp);
            return;
        }

        Portfolio portfolio = new Portfolio(portfolioName, cusip, code, opendate,expdate, desc );
        boolean ret = PortfolioDatastore.add(portfolio);
        if (ret) {
            req.setAttribute("_retStr", "Add portfolio " + portfolioName + " succ");
            MessagingEndpoint msg = new MessagingEndpoint();
            msg.sendMessage("Added");

            ArrayList<Portfolio> result = new ArrayList<Portfolio>();
            result.add(portfolio);
            req.setAttribute("result", result);
        } else {
            req.setAttribute("_retStr", portfolioName + " exists");
        }

        getServletContext().getRequestDispatcher("/query_result_portfolio.jsp").forward(
                req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }

}

