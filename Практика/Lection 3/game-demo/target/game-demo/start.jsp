<%@ taglib uri="http://exigen.ru/students/tags" prefix="util" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Bookmark Page</title>
    <link rel="stylesheet" type="text/css"
                           href="${pageContext.request.contextPath}/css/common.css">
</head>

<body>
    <c:set var="check" value="${util:Check(value)}"/>
    <c:if test = "${check} = 1">
        <form action = "win.jsp" method = "POST"/>
    </c:if>
    <c:if test = "${check} = 0">
        <form action = "lose.jsp" method = "POST"/>
    </c:if>
</body>
</html>