<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in page</title>
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
    <h1>Hello ${login}</h1>
    <div class="center" >
        <b>Log in form</b>
        <form action = "/" method = "post">
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
                    <td><div><input type = "submit" value = "Submit"/></div></td>
                </tr>
            </table>
        </form>
    </div>
    <div align="center">
    <table border = "0">
        <tr>
            <td><a href="/Checkin">Check in</a></td>
        </tr>
        <tr>
            <td><b>${log}</b></td>
        </tr>
        <tr>
            <td><b>${pas}</b></td>
        </tr>
    </table>
    </div>
</body>
</html>
