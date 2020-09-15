<%-- 
    Document   : postArticle
    Created on : Sep 3, 2020, 8:02:57 PM
    Author     : tuyet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Article Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER or sessionScope.USER.role.name ne 'member'}">
            <c:redirect url="/">
            </c:redirect>
        </c:if>
        <%--user--%>
        <c:import url="includes/user.jsp"/>
        <br/>
        <%--end user--%>

        <%--message--%>
        <c:import url="includes/message.jsp"/>
        <br/>
        <%--end message--%>

        <%--form post article--%>
        <form action="MainController" method="POST">
            <c:if test="${not empty requestScope.TITLE_ERROR}">
                <p style="color : red"> ${requestScope.TITLE_ERROR}</p>
            </c:if>
            Title : <input type="text" name="txtTitle" value="${param.txtTitle}" /><br/>
            <c:if test="${not empty requestScope.SD_ERROR}">
                <p style="color : red"> ${requestScope.SD_ERROR}</p>
            </c:if>
            Short description : <input type="text" name="txtShortDescription" value="${param.txtShortDescription}" ><br/>
            Content : <input type="text" name="txtContent" value="${param.txtContent}" /><br/>
            <input type="submit" value="Post Article" name="btnAction" /><br/>
        </form>
        <br/>
        <%--end form post article--%>

        <%--back to search--%>
        <c:import url="includes/backToSearch.jsp"/>
        <br/>
        <%--end back to search--%>
    </body>
</html>
