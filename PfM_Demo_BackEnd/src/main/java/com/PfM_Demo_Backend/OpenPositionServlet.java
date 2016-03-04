package com.PfM_Demo_Backend;

import com.PfM_Demo_Backend.data.Portfolio;
import com.PfM_Demo_Backend.data.PortfolioDatastore;
import com.google.appengine.repackaged.com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by CODEMUD on 3/3/2016.
 */
public class OpenPositionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String OPENDATETAGE ="opendate";
    private static final String PORTFOLIOTAG = "portfolio";
    private static final String OPENPOSITIONRESPONSE= "openPositionResponse";
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String opendate = req.getParameter(OPENDATETAGE).trim();
        String portfolioName  = req.getParameter(PORTFOLIOTAG).trim();



        if (portfolioName == null || portfolioName.equals("")) {
            return;
        }

        ArrayList<Portfolio> portfolioList = PortfolioDatastore.getListOfPositions(portfolioName, opendate);
        JSONObject finalObject = new JSONObject();
        if (portfolioList.size()>0) {
            req.setAttribute("_retStr", "Add portfolio " + portfolioName + " succ");
            MessagingEndpoint msg = new MessagingEndpoint();
            msg.sendMessage("Getting List of Portfolios");
            JSONArray jsArray = new JSONArray();
            for(Portfolio portfolio: portfolioList) {
                JSONObject json = new JSONObject();
                json.put(Portfolio.FIELD_NAME_NAME, portfolio.portfolioName);
                json.put(Portfolio.FIELD_NAME_CODE, portfolio.code);
                json.put(Portfolio.FIELD_NAME_CUSIP, portfolio.cusip);
                json.put(Portfolio.FIELD_NAME_DESC, portfolio.desc );
                json.put(Portfolio.FIELD_NAME_EXPDATE, portfolio.expdate);
                json.put(Portfolio.FIELD_NAME_OPENDATE, portfolio.opendate);
                jsArray.add(json);
            }

            finalObject.put(OPENPOSITIONRESPONSE, jsArray);

        } else {
            finalObject.put(OPENPOSITIONRESPONSE, null);
        }

        String jsonString = new Gson().toJson(finalObject);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonString);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }

}