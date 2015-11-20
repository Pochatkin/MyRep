<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://exigen.ru/students/tags" prefix="util" %>

<html>
    <head>
        <title>Testing URL validation</title>
    </head>

    <body>

        <h2>url validation</h2>

        <div>
           url http://google.com is valid:
           ${util:validateUrl("http://google.com")}
        </div>

        <div>
            url lalala is valid:
            ${util:validateUrl("lalala")}
        </div>

        <p>
            <a href="${pageContext.request.contextPath}">Get Back</a>
        </p>

    </body>
</html>
