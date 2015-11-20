package com.exigenservices.lectures.servlet;

import com.exigenservices.lectures.api.UserManager;
import com.exigenservices.lectures.api.exception.UserManagementException;
import com.exigenservices.lectures.model.UserTO;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User Manager Servlet
 * Created by bolbin on 23.11.2014.
 */
public class UserManagerServlet extends HttpServlet {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.mm.yyyy");
    private static final Logger LOGGER = Logger.getLogger("UserManagerServlet");

    private static final String PARAM_ACTION = "action";
    private static final String ACTION_ADD = "add";

    @EJB(mappedName = UserManager.BEAN_NAME)
    private UserManager userManagerEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getAllUsersAndRedirectToView(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter(PARAM_ACTION);
        if(ACTION_ADD.equals(action)) {
            saveUser(request);
        }
        getAllUsersAndRedirectToView(request, response);
    }

    private void saveUser(HttpServletRequest request) {
        String userName = request.getParameter("name");
        Date dateOfBirth = parseDate(request.getParameter("dateOfBirth"));

        if(StringUtils.isEmpty(userName) || dateOfBirth == null){
            request.setAttribute("error", "Incorrect form parameters, user can not be created.");
            return;
        }

        UserTO userTO = new UserTO(null, userName, dateOfBirth);
        try {
            userManagerEJB.saveUser(userTO);
        } catch (UserManagementException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            request.setAttribute("error", e.getMessage());
        }
    }

    private Date parseDate(String dateStr){
        Date result = null;
        if(!StringUtils.isEmpty(dateStr)) {
            try {
                result = DATE_FORMAT.parse(dateStr);
            } catch (ParseException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
        return result;
    }

    private void getAllUsersAndRedirectToView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UserTO> allUsers = userManagerEJB.getAllUsers();
        request.setAttribute("allUsers", allUsers);
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }

}
