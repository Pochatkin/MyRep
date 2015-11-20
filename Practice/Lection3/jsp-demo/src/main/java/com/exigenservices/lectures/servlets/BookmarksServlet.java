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

    private final static String ACTION_ADD = "add";
    private final static String ACTION_DELETE = "delete";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Bookmark> savedBookmarks = getBookmarksFromSession(request.getSession());
        if (savedBookmarks.isEmpty()) {
            redirectToEmpty(request, response) ;
        } else {
            redirectToView(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, Bookmark> savedBookmarks = getBookmarksFromSession(session);

        String action = request.getParameter("action");

        if (ACTION_ADD.equals(action)) {
            String name = request.getParameter("name");
            String url = request.getParameter("url");
            if (!addBookmark(savedBookmarks, name, url)) {
                request.setAttribute("error", "Incorrect bookmark name or URL");
            }

        } else if (ACTION_DELETE.equals(action)) {
            String id = request.getParameter("id");
            if (savedBookmarks.remove(id) == null) {
                request.setAttribute("error", "Bookmark cannot be deleted: not found");
            }
        }
        redirectToView(request, response);
    }

    private Map<String, Bookmark> getBookmarksFromSession(HttpSession session) {
        Map<String, Bookmark> bookmarks = (Map) session.getAttribute("bookmarks");
        if (bookmarks == null) {
            bookmarks = new LinkedHashMap<String, Bookmark>();
            session.setAttribute("bookmarks", bookmarks);
        }
        return bookmarks;
    }

    private boolean addBookmark(Map<String, Bookmark> savedBookmarks,
                                String name, String url) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(url)) {
            return false;
        }
        /* create and add new bookmark to savedBookmarks map by id */
        Bookmark bookmark = new Bookmark(generateNewId(savedBookmarks), name, url);
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

    private void redirectToView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view.jsp").forward(request, response);
    }

    private void redirectToEmpty(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/empty.jsp").forward(request, response);
    }
}
