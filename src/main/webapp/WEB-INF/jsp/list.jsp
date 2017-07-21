<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.egartech.lab.auction.data.Lot" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.egartech.lab.auction.data.Bet" %>
<%@ page import="com.egartech.lab.auction.data.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of lots</title>
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
            <%} else {%>
                    <b> guest!<b>
            <%}%>
        </div>
        <div>
            <a href="/LogOut">Log Out</a>
        </div>
    </div>
</head>
<body>
    <h1>List of lots</h1>
    <b>${error}</b>
    <table border="1">
    <tr>
        <td><b>Lot</b></td>
        <td><b>Bet</b></td>
        <td><b>Bet owner</b></td>
        <td><b>New bet</b></td>
        <td><b></b></td>
    </tr>
    <%
        ArrayList<Lot> lots=(ArrayList<Lot>) request.getAttribute("lots");
        for (Lot lot: lots) {
    %>
    <tr>
        <form action = "/NewBet" method = "post">
            <td><%=lot.getName()%></td>
            <td>
                <% if(lot.getBet() != null){%>
                <%=lot.getBet().getPrice()%>
                <%}%>
            </td>
            <td>
                <% if(lot.getBet() != null){%>
                <%=lot.getBet().getUser().getLogin()%>
                <%}%>
            </td>
            <td>
                <input type="number" value="" name="bet_price" size="5" >
            </td>
            </td>
            <td>
                <input type="submit" value="Update bet" size="10">
                <input type="hidden" name="lot_id" value="<%=lot.getId()%>">
            </td>
        </form>
    </tr>
    <%}%>
    </table>
    <form action = "/NewLot" method = "post">
        <table border = "0">
            <tr>
                <td><div><input type = "submit" value = "New Lot"  /></div></td>
            </tr>
        </table>
    </form>
</body>
</html>
