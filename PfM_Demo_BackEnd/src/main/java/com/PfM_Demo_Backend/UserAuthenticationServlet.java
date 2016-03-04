package com.PfM_Demo_Backend;

import com.PfM_Demo_Backend.data.Portfolio;
import com.PfM_Demo_Backend.data.PortfolioDatastore;
import com.PfM_Demo_Backend.data.User;
import com.PfM_Demo_Backend.data.UserDatastore;
import com.google.appengine.repackaged.com.google.gson.Gson;

import org.json.simple.JSONObject;

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

public class UserAuthenticationServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String AUTHERIZETAG = "_authresponse";
    private static final String USERTAG ="user";
    private static final String TRUETAG ="true";
    private static final String FALSETAG ="false";
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String username = req.getParameter("uName").trim();
        String password = req.getParameter("pass").trim();

        if (username == null || username.equals("")) {
            req.setAttribute(AUTHERIZETAG, username+ ":invalid");
            return;
        }
        User user = new User(username, password);

        JSONObject json = new JSONObject();

        json.put(USERTAG, username );
        UserDatastore.add(user);//just for testing
        boolean ret = UserDatastore.authenticateUser(user);
        if (ret) {
            json.put(AUTHERIZETAG, TRUETAG );
            MessagingEndpoint msg = new MessagingEndpoint();
            msg.sendMessage("Valid User");
        } else {
            json.put(AUTHERIZETAG , FALSETAG );
            MessagingEndpoint msg = new MessagingEndpoint();
            msg.sendMessage("InValid User");
        }
        String jsonString = new Gson().toJson(json);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonString);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }

}