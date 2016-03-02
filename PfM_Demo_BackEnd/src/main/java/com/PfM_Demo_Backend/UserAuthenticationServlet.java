package com.PfM_Demo_Backend;

import com.PfM_Demo_Backend.data.Portfolio;
import com.PfM_Demo_Backend.data.PortfolioDatastore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by CODEMUD on 3/1/2016.
 */
public class UserAuthenticationServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Map<String, String> myMap;
    static {
        Map<String, String> aMap = new HashMap<String, String>();
        aMap.put("ankur", "kumar");
        aMap.put("harminder", "singh");
        aMap.put("mrinal", "mrinal");
        aMap.put("anudeep", "kansal");
        myMap = Collections.unmodifiableMap(aMap);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String username = req.getParameter("user");
        String password = req.getParameter("pass");

    if(myMap.containsKey(username)){
        ServletOutputStream out = resp.getOutputStream();

        resp.setContentType("text/plain");
        resp.getOutputStream();
        if(myMap.get(username).equals(password)){
            MessagingEndpoint msg = new MessagingEndpoint();
            msg.sendMessage(username+":true");

            out.println("true");
            out.close();
        }else{

            MessagingEndpoint msg = new MessagingEndpoint();
            msg.sendMessage(username+":false");
            out.println("false");
            out.close();
        }
    }else{

    }







        getServletContext().getRequestDispatcher("/query_result_portfolio.jsp").forward(
                req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }

}