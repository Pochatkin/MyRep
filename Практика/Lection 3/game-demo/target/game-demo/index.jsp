<%@ taglib uri="http://exigen.ru/students/tags" prefix="util" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Game page</title>
        <link rel="stylesheet" type="text/css"
                               href="${pageContext.request.contextPath}/css/common.css">
    </head>
    <body>
        <tags:hello userName="Player"/>
            <form action="LoseOrWin.jsp" method="POST">
                <p> Value: <input type="text" name="value"> </p>
                <input type="hidden" name="action" value="add">
                <input type="submit" name="add" value="add">
                <input type="hidden" name="var1" value="11">
            </form>
    </body>
</html>
