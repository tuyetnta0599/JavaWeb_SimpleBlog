<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${not empty sessionScope.USER}">
    <h1>Welcome, ${sessionScope.USER.fullname}</h1>
    <form action="MainController" method="POST">
        <input type="submit" value="Logout" name="btnAction" />
    </form>
</c:if>
    
<c:if test="${empty sessionScope.USER}">
    <a href="login.jsp" style="text-decoration: none">Login</a>
</c:if><br/>

<c:if test="${empty sessionScope.USER and sessionScope.USER.role.name ne 'admin'}">
    <a href="register.jsp" style="text-decoration: none">Registration</a>
</c:if><br/>


