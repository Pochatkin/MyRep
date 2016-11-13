package com.homework.servlets

import com.google.gson.Gson
import com.homework.models.InvertIndex
import com.homework.models.JsonPackage
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Created by mikhail on 13.09.16.
 */

class BaseServlet : HttpServlet() {

    private var invertIndex: InvertIndex? = null

    override fun init() {
        invertIndex = InvertIndex()
        //Logger log = Logger.getLogger(BaseServlet.class.getName());
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        doPost(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val ajax = "XMLHttpRequest" == request.getHeader("X-Requested-With")
        if (ajax) {
            val searchText = request.getParameter("text")
            val writer = response.getWriter()
            response.setContentType("text/plain")
            response.setCharacterEncoding("UTF-8")
            try {
                val docsNames = invertIndex!!.getDocNames(searchText)
                val textInDocs = invertIndex!!.getTextInDocs(searchText, docsNames)
                if (docsNames.size != 0) {
                    writer.write(Gson().toJson(JsonPackage("found", docsNames, textInDocs)))
                } else {
                    writer.write(Gson().toJson(JsonPackage("Not found")))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
