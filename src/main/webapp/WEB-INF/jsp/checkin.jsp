<%@ page import="com.egartech.lab.auction.data.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check in page</title>
    <style type="text/css">
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
        <div>
            <b>Hello<b>
                 <%
                    HttpSession hSession = request.getSession();
                    if(hSession.getAttribute("user") != null) {
                        User user = (User) hSession.getAttribute("user");
                 %>
                        <b><%=user.getLogin()%>!<b>
                 <%} else {%>
                        <b> guest!<b>
                <%  }%>
        </div>
        <div>
            <a href="/LogOut">Log Out</a>
        </div>
    </div>

</head>
<body>
<div class="center" >
    <b>Check in form</b>
    <form action = "/Registration" method = "post">
        <table border = "0">

            <tr>
                <td><b>Login</b></td>
                <td><input type = "text" name = "login"
                           value = "login" size = "50"/></td>
            </tr>

            <tr>
                <td><b>Password</b></td>
                <td><input type = "password" name = "password"
                           value = "password" size = "50"/></td>
            </tr>
            <tr>
                <td><div><input type = "submit" value = "Registration"/></div></td>
            </tr>
        </table>
    </form>
</div>
<div align="center">
    <table border = "0">
        <tr>
            <td><a href="/Index">log in</a></td>
        </tr>
        <tr>
            <td><b>${loginError}</b></td>
        </tr>
        <tr>
            <td><b>${pasError}</b></td>
        </tr>
        <tr>
            <td><b>${error}</b></td>
        </tr>
    </table>
</div>
</body>
</html>