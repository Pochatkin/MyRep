<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Users management</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
</head>

<body>

<h3>Users</h3>
<table>
    <tr>
        <td>#</td>
        <td>User Name</td>
        <td>Date of Birth</td>
    </tr>
    <c:forEach var="user" items="${requestScope.allUsers}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>
                <fmt:formatDate value="${user.dateOfBirth}" pattern="dd.MM.yyyy"/>
            </td>
        </tr>
    </c:forEach>
</table>

<div class="add_user_form">
    <h3>Add new user</h3>
    <form action="" method="POST">
        <table width="100%">
            <tr>
                <td>name:  </td>
                <td class="right_cell"><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>date of birth (dd.mm.yyyy): </td>
                <td class="right_cell"><input type="text" name="dateOfBirth"></td>
            </tr>
            <tr>
                <td colspan="2" class="right_cell">
                    <input type="hidden" name="action" value="add">
                    <input type="submit" name="add" value="add user">
                </td>
            </tr>
        </table>

        <c:if test="${not empty requestScope.error}">
            <div class="error">
                    ${requestScope.error}
            </div>
        </c:if>
    </form>
</div>

</body>
</html>
