<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Bookmarks page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
</head>

<body>

    <c:if test="${not empty requestScope.error}">
        <div class="error">
            ${requestScope.error}
        </div>
    </c:if>
    
    <table>
        <tr>
            <td>#</td>
            <td>Name</td>
            <td>URL</td>
            <td>&nbsp;</td>
        </tr>
        <c:forEach var="bookmarkEntry" items="${sessionScope.bookmarks}">
            <c:set var="bookmark" value="${bookmarkEntry.value}"/>
            <tr>
                <td>${bookmark.id}</td>
                <td>${bookmark.name}</td>
                <td><a href="${bookmark.url}">${bookmark.url}</a></td>
                <td>
                    <form action="" method="POST">
                        <input type="hidden" name="id" value="${bookmark.id}">
                        <input type="hidden" name="action" value="delete">
                        <input type="submit" name="delete" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
        <form action="" method="POST">
            <tr>
                <td>Add new bookmark:</td>
                <td><input type="text" name="name"></td>
                <td><input type="text" name="url"></td>
                <td>
                    <input type="hidden" name="action" value="add">
                    <input type="submit" name="add" value="add">
                </td>
            </tr>
        </form>
    </table>

    <p>
        <a href="${pageContext.request.contextPath}">Get Back</a>
    </p>
</body>
</html>
