<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://exigen.ru/students/tags" prefix="util" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
    <head>
        <title>Bookmark Page</title>
        <link rel="stylesheet" type="text/css"
                               href="${pageContext.request.contextPath}/css/common.css">
    </head>
    <body>
        <util:helloTag/>
        <tags:hello userName="Player"/>
           <div>
                   <form action="" method="POST">
                       <p>Value: <input type="text" name="value"></p>
                       <p><input type="submit" name="add" value="Start"></p>
                       <input type="hidden" name="action" value="Start">
                   </form>
           </div>

    </body>
</html>
