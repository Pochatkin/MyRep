package ru.jchess.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dima on 09.04.16.
 */
public class MainServlet extends HttpServlet {
    private final static String ACTION_SIGN_IN = "sign in";
    private final static String ACTION_SIGN_OUT = "sign out";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        session.setAttribute("login", "Dima");
        if (ACTION_SIGN_IN.equals(action)) {
            String url = request.getContextPath() + "/login";
            response.sendRedirect(url);
        } else if (ACTION_SIGN_OUT.equals(action)) {
            //do something to log out from session
            //TODO
            session.setAttribute("login", null);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
