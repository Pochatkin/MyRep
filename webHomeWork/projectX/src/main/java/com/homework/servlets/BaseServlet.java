package com.homework.servlets;


import com.google.gson.Gson;
import com.homework.models.InvertIndex;
import com.homework.models.JsonPackage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by mikhail on 13.09.16.
 */
public class BaseServlet extends HttpServlet {

    private InvertIndex invertIndex;

    @Override
    public void init() {
        invertIndex = new InvertIndex();
        //Logger log = Logger.getLogger(BaseServlet.class.getName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        if (ajax) {
            String searchText = request.getParameter("text");
            Writer writer = response.getWriter();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            try {
                String[] docsNames = invertIndex.getDocNames(searchText);
                String[] textInDocs = invertIndex.getTextInDocs(searchText, docsNames);
                if (docsNames.length != 0) {
                    writer.write(new Gson().toJson(new JsonPackage("found", docsNames, textInDocs)));
                } else {
                    writer.write(new Gson().toJson(new JsonPackage("Not found")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
