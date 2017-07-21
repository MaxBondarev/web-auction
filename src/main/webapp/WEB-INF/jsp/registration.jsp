<%@ page import="com.egartech.lab.auction.data.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome! New user created!</title>
    <meta http-equiv="refresh" content="2; url=List">
    <style>
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
                     if(hSession.getAttribute("user") != null){
                        User user = (User) hSession.getAttribute("user");
                %>
                        <b><%=user.getLogin()%>!<b>
                <%}  else {%>
                        <b> guest!<b>
                <%   }%>
        </div>
        <div>
            <a href="/LogOut">Log Out</a>
        </div>
    </div>
</head>
<body>
    <h1>Welcome! New user created!</h1>
</body>
</html>
