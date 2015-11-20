<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Bookmark Page</title>
    <link rel="stylesheet" type="text/css"
                           href="${pageContext.request.contextPath}/css/common.css">
</head>

<body>
    <div>
        You have no bookmarks. Please add at least one.
    </div>

    <div>
        <form action="" method="POST">
            <p>Name: <input type="text" name="name"></p>
            <p>URL:  <input type="text" name="url"></p>
            <p><input type="submit" name="add" value="add"></p>
            <input type="hidden" name="action" value="add">
        </form>
    </div>

    <p>
        <a href="${pageContext.request.contextPath}">Get Back</a>
    </p>
</body>
</html>
