<%-- 
    Document   : login
    Created on : Aug 31, 2020, 9:12:58 PM
    Author     : tuyet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <c:import url="includes/message.jsp"/>
        <form action="MainController" method="POST">
            Email : <input type="email" name="txtEmail" value="" /><br/>
            Password : <input type="password" name="txtPassword" value="" /><br/>
            <input type="hidden" name="viewArt" value="<c:if test="${not empty param.viewArt}">${param.viewArt}</c:if><c:if test="${empty param.viewArt}">${requestScope.viewArt}</c:if>"/>
            <input type="submit" value="Login" name="btnAction" />
            <input type="reset" value="Reset" />
        </form>
        <br/>
        <%--back to search--%>
        <c:import url="includes/backToSearch.jsp"/>
        <br/>
        <%--end back to search--%>
    </body>
</html>
