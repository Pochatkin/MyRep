<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>JChess</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>

</head>

<body>
<div>
    <c:choose>
        <c:when test="${not empty sessionScope.login}">
            <div>
                ${sessionScope.login}
            </div>
            <div>
                <form action="" method="post">
                    <input type="hidden" name="action" value="sign out">
                    <input type="submit" name="Sign out" value="sign out">
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <form action="" method="post">
                    <input type="hidden" name="action" value="sign in">
                    <input type="submit" name="Sign in" value="sign in">
                </form>
            </div>
            <div>
            </div>
        </c:otherwise>
    </c:choose>

    <p>
        <a href="${pageContext.request.contextPath}">Home page</a>
    </p>
</div>
</body>
</html>