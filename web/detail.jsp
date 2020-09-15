<%-- 
    Document   : detail
    Created on : Sep 2, 2020, 7:59:04 PM
    Author     : tuyet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
    </head>
    <body>

        <%--user--%>
        <c:import url="includes/user.jsp"/>
        <br/>
        <%--end user--%>

        <%--back to search--%>
        <c:import url="includes/backToSearch.jsp"/>
        <br/>
        <%--end back to search--%>

        <h1>Title: ${requestScope.ART_DETAIL.title}</h1>
        <p> Short Description: ${requestScope.ART_DETAIL.shortDescription}</p>
        <p> Content : ${requestScope.ART_DETAIL.content}</p>
        <p> Author: ${requestScope.ART_DETAIL.author.fullname}</p>
        <p> Posted: ${requestScope.ART_DETAIL.postingDate}</p>
        <c:if test="${not empty sessionScope.USER && sessionScope.USER.role.name eq 'admin'}">
            <p> Status : ${requestScope.ART_DETAIL.status}</p>
        </c:if>
        <br/>

        <c:if test="${not empty requestScope.ART_COMMENT}">
            <c:forEach var="commentX" items="${requestScope.ART_COMMENT}">
                <p>(<fmt:formatDate value="${commentX.commentDate}" pattern="dd-MM-yyyy hh:mm:ss"/>) <span style="color: green;">${commentX.userComment.fullname}</span>: ${commentX.comment}</p>
            </c:forEach>
        </c:if> 

        <c:if test="${empty requestScope.ART_COMMENT}">
            <p>No one has commented on this article yet</p>
        </c:if>
        <br/>

        <%--message--%>
        <c:import url="includes/message.jsp"/>
        <br/>
        <%--end message--%>

        <%--comment--%>
        <c:if test="${empty sessionScope.USER or sessionScope.USER.role.name eq 'member'}">
            <c:import url="includes/commentArticle.jsp"/>
        </c:if>
        <%--end comment--%>
    </body>
</html>
