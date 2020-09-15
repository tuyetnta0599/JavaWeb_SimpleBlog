<%-- 
    Document   : home
    Created on : Sep 1, 2020, 5:35:13 PM
    Author     : tuyet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <input type="submit" value="Load User" name="btnAction" />
        </form>

        <%--user--%>
        <c:import url="includes/user.jsp"/>
        <br/>
        <%--end user--%>

        <%--message--%>
        <c:import url="includes/message.jsp"/>
        <br/>
        <%--end message--%>

        <%--post article--%>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.role.name eq 'member'}">
            <a href="postArticle.jsp" style="text-decoration: none">Post Article</a>
        </c:if>
        <%--end post article--%>

        <%--search--%>
        <c:if test="${sessionScope.USER.role.name eq 'admin'}">
            <c:import url="includes/adminSearch.jsp"/>
        </c:if>
        <c:if test="${empty sessionScope.USER or sessionScope.USER.role.name eq 'member'}">
            <c:import url="includes/memberSearch.jsp"/>
        </c:if>
        <br/>
        <%--end search--%>

        <%--article--%>
        <c:if test="${sessionScope.USER.role.name eq 'admin'}">
            <c:import url="includes/adminArticle.jsp"/>
        </c:if>
        <c:if test="${empty sessionScope.USER or sessionScope.USER.role.name eq 'member'}">
            <c:import url="includes/memberArticle.jsp"/>
        </c:if>
        <br/>
        <%--end article--%>
    </body>
</html>
