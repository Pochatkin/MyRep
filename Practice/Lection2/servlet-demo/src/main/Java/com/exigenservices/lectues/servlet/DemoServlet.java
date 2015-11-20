package com.exigenservices.lectues.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Михаил on 29.10.2015.
 */
public class DemoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        long curTime = System.currentTimeMillis();
        Date data = new Date(curTime);

        PrintWriter writer = res.getWriter();
        writer.println("<html>");
        writer.println("<head><title>My page</title></head>");
        writer.println("<body>The current time is " + data.toString() + " in the server </body>");
        writer.println("</html>");
        writer.close();
    }
}
