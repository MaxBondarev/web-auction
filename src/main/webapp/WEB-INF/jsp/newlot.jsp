<%@ page import="com.egartech.lab.auction.data.User" %><%--
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
    <style>
        .center {
            text-align: center;
            padding-top: 5%;
            padding-left: 33%;
            padding-right: 33%;
            margin-left:auto;
            margin-right:auto;

        }
        .right {
            text-align: right;
            padding-right: 25px;
            padding-top: 13px;
            font-size: medium;
        }
    </style>
    <div class="right">
    <b>Hello<b>
     <%
         HttpSession hSession = request.getSession();
         if(hSession.getAttribute("user") != null){
             User user = (User) hSession.getAttribute("user");
     %>
        <b><%=user.getLogin()%>!<b>
     <%} else {%>
        <b> guest!<b>
     <%}%>
     </div>
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
