<%@ taglib uri="http://exigen.ru/students/tags" prefix="util" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>
                <c:set var="number">
                    <%= request.getParameter("value") %>
                </c:set>

                <c:set var="var1">
                    <util:check value = "${number}"/>
                </c:set>

                 <c:if test="${var1==true}">
                    <jsp:forward page="win.jsp">
                    <jsp:param name="number" value="${var1}"/>
                    </jsp:forward>
                 </c:if>

                 <c:if test="${var1==false}">
                    <jsp:forward page="lose.jsp">
                    <jsp:param name="number" value="${var1}"/>
                    </jsp:forward>
                </c:if>



    </body>
</html>