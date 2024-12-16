<%-- 
    Document   : index
    Created on : Dec 15, 2024, 2:26:14 PM
    Author     : Hakeem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <h2>Send OTP to Telegram</h2>

    <!-- Display status message -->
    <% if (request.getAttribute("status") != null) { %>
        <p><b><%= request.getAttribute("status") %></b></p>
    <% } %>

    <!-- Form to send OTP -->
    <form action="sendOtpServlet" method="post">
        <input type="submit" value="Send OTP">
    </form>
</body>
</html>
