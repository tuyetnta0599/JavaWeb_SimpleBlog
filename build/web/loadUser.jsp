<%-- 
    Document   : loadUser
    Created on : Sep 5, 2020, 12:48:06 PM
    Author     : tuyet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <c:if test="${not empty requestScope.LIST_USER}">

            <table border='1'>
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Fullname</th>
                        <th>Role name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${requestScope.LIST_USER}" varStatus="Counter">
                        <tr>
                            <td>${Counter.count}</td>
                            <td>${dto.email}</td>
                            <td>${dto.password}</td>
                            <td>${dto.fullname}</td>
                            <td>${dto.role.name}</td>
                            
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${empty requestScope.LIST_USER}">
                No result   
            </c:if>

        </c:if>
    </body>
</html>
