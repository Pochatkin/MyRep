package com.homework.servlets;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.*;

/**
 * Created by mikhail on 24.09.16.
 */
public class Log4jInit extends HttpServlet {

    @Override
    public void init() {
        String logFileName = getInitParameter("logfile");
        String pref = "";
        pref = getServletContext().getRealPath("/");

        PropertyConfigurator.configure(pref + logFileName);

        Logger globalLog = Logger.getRootLogger();

        getServletContext().setAttribute("log4", globalLog);
        getServletContext().setAttribute("logfilename", logFileName);
        globalLog.info("Load-onstart-up Servlet");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String pref = getServletContext().getRealPath("/");
            String logfile = (String) getServletContext().getAttribute("logfilename");
            /* TODO output your page here */
            out.println("<html><head>");
            out.println("<title>Servlet Log4jInit</title>");
            out.println("</head><body>");
            out.println("<h2>File properties Log4jInit " + pref + logfile + "</h2>");
            out.println("</body></html>");
        } finally {
            out.close();
        }
    }
}
