<%-- 
    Document   : message
    Created on : Sep 2, 2020, 5:13:22 PM
    Author     : tuyet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${not empty requestScope.MSG_ERROR}">
    <font color="red">
    ${requestScope.MSG_ERROR}
    </font>
</c:if>
<c:if test="${not empty requestScope.MSG_SUCCESS}">
    <font color="green">
    ${requestScope.MSG_SUCCESS}
    </font>
</c:if>