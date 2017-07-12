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
    </style>
</head>
<body>
<h1>Hello ${log}</h1>
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
            <td><b>${error}</b></td>
        </tr>
    </table>
</div>
</body>
</html>