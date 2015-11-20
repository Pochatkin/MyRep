<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://exigen.ru/students/tags" prefix="util" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
    <body>
        <util:helloTag/>
        <tags:hello userName="Mrs. Hudson"/>

        <p>
            <a href="${pageContext.request.contextPath}/bookmark/">Bookmarks</a><br>
            <a href="${pageContext.request.contextPath}/format.jsp">Multi Language Support Example (EN)</a> or
            <a href="${pageContext.request.contextPath}/format.jsp?language=ru">Multi Language Support Example (RU)</a><br>
            <a href="${pageContext.request.contextPath}/url.jsp">Custom Tag Example</a><br>
        </p>
    </body>
</html>
