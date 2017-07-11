<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in page</title>
</head>
<body>
    <h1>Hello ${login}</h1>

    <h1>Log in form</h1>
    <form action = "servlet-parameters" method = "GET">
        <table border = "0">

            <tr>
                <td><b>Login</b></td>
                <td><input type = "text" name = "login"
                           value = "http://localhost/some-test-url" size = "70"/></td>
            </tr>

            <tr>
                <td><b>Password</b></td>
                <td><input type = "text" name = "password"
                           value = "001" size = "65"/></td>
            </tr>
        </table>
    </form>
</body>
</html>
