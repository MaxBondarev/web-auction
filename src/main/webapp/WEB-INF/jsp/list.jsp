<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.egartech.lab.auction.data.Lot" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>List of lots</h1>

    <table>
        <c:forEach items="${lots}" var="lot">
            <tr>
                <td>${lot.id}</td>
                <td>${lot.name}</td>
            </tr>
        </c:forEach>
    </table>
    <%
        ArrayList<Lot> lots=(ArrayList<Lot>) request.getAttribute("lots");
        for (Lot lot: lots) {
    %>
    <tr>
        <td><%=lot.getId()%></td>
        <td><%=lot.getName()%></td>
    </tr>
    <%}%>
</body>
</html>
