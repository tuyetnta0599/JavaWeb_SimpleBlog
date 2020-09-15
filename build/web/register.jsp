<%-- 
    Document   : register
    Created on : Sep 2, 2020, 8:13:40 PM
    Author     : tuyet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER}">
            <c:redirect url="/">
            </c:redirect>
        </c:if>
        <c:if test="${not empty sessionScope.USER}"></c:if>
        <h1>Register Account</h1>
        <form action="MainController" method="POST">
            <c:if test="${not empty requestScope.DUPLICATE}">
                <font color="red">
                <p> ${requestScope.DUPLICATE}</p>
                </font>
            </c:if>
            <c:if test="${not empty requestScope.EMAIL_PATTERN}">
                <font color="red">
                <p>${requestScope.EMAIL_PATTERN}</p>
                </font>
            </c:if>
            Email: <input type="email" name="txtEmail" value="${param.txtEmail}" /><br/>
            <c:if test="${not empty requestScope.PASSWORD_PATTERN}">
                <font color="red">
                <p>${requestScope.PASSWORD_PATTERN}</p>
                </font>
            </c:if>
            Password : <input type="password" name="txtPassword" value="${param.txtPassword}" /><br/>
            <c:if test="${not empty requestScope.FULLNAME_PATTERN}">
                <font color="red">
                <p>${requestScope.FULLNAME_PATTERN}</p>
                </font>
            </c:if>
            Full name : <input type="text" name="txtFullname" value="${param.txtFullname}" /><br/>
            <input type="submit" value="Register" name="btnAction" />
            <input type="submit" value="Reset" />
        </form>
        <br/>
        <%--back to search--%>
        <c:import url="includes/backToSearch.jsp"/>
        <br/>
        <%--end back to search--%>
    </body>
</html>
