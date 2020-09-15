<%-- 
    Document   : error
    Created on : Aug 31, 2020, 9:26:18 PM
    Author     : tuyet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <c:if test="${not empty requestScope.ERROR}">
            <font color="red">
            ${requestScope.ERROR}
            </font>
        </c:if>
        <c:import url="includes/backToSearch.jsp"/>
    </body>
</html>
