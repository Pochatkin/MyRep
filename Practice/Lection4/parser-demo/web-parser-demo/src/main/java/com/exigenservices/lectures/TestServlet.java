package com.exigenservices.lectures;

import com.sun.net.httpserver.HttpServer;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Михаил on 13.11.2015.
 */
public class TestServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String temp = (String)request;
        Parser();

    }
}
