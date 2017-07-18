<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 17.07.17
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new lot</title>
</head>
<body>
    <form action = "/CreateLot" method = "post">
        <table border = "0">

            <tr>
                <td><b>Create new lot</b></td>
                <td><input type = "text" name = "name"
                           value = "" size = "40"/></td>
            </tr>
            <tr>
                <td><div><input type = "submit" value = "Create"/></div></td>
            </tr>
        </table>
    </form>
    <b>${error}</b>
</body>
</html>
