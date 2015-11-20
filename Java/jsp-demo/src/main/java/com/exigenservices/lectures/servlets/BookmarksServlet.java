package com.exigenservices.lectures.servlets;

import com.exigenservices.lectures.model.Bookmark;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Servlet for bookmarks operations
 * Created by bolbin
 * on 07.11.2014.
 */
public class BookmarksServlet extends HttpServlet {

    private static final long serialVersionUID = -2981592634968409643L;

    private final static String ACTION_START = "Start";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Bookmark> savedValue = getValueFromSession(request.getSession());
        if (savedBookmarks.isEmpty()) {
            redirectToWin(request, response);
        } else {
            redirectToLose(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, Bookmark> savedBookmarks = getValueFromSession(session);

        String action = request.getParameter("action");

        if (ACTION_START.equals(action)) {
            String value = request.getParameter("value");
            if (!addBookmark(savedBookmarks, value)) {
                request.setAttribute("error", "Incorrect value");
            }
        }
    }

    private Map<String, Bookmark> getValueFromSession(HttpSession session) {
        Map<String, Bookmark> bookmarks = (Map) session.getAttribute("bookmarks");
        if (bookmarks == null) {
            bookmarks = new LinkedHashMap<String, Bookmark>();
            session.setAttribute("bookmarks", bookmarks);
        }
        return bookmarks;
    }

    private boolean addBookmark(Map<String, Bookmark> savedBookmarks,
                                String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        /* create and add new bookmark to savedBookmarks map by id */
        Bookmark bookmark = new Bookmark(generateNewId(savedBookmarks), value);
        savedBookmarks.put(bookmark.getId(), bookmark);
        return true;
    }

    private String generateNewId(Map<String, Bookmark> savedBookmarks) {
        /* generate new id */
        Long id = savedBookmarks.size() + 1L;
        while (savedBookmarks.containsKey(id.toString())) {
            id += 1L;
        }
        return id.toString();
    }

    private void redirectToWin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/win.jsp").forward(request, response);
    }

    private void redirectToLose(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/lose.jsp").forward(request, response);
    }
}
