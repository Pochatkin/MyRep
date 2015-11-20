<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="language" value="${(param.language != null && param.language != '')? param.language : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>
<html>
    <head>
        <title><fmt:message key="page.header"/></title>
    </head>
    <body>
        <h2><fmt:message key="page.title"/></h2>

        <p>
            <a href="${pageContext.request.contextPath}">Get Back</a>
        </p>
    </body>
</html>
