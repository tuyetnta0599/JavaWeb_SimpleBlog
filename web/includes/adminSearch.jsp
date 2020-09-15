<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="MainController" method="POST">
    Search : <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br/>
    Status : <select name="statusSearch">
        <option value="all"<c:if test="${param.statusSearch eq 'all'}">selected</c:if>>All</option>
        <option value="new"<c:if test="${param.statusSearch eq 'new'}">selected</c:if>>New</option>
        <option value="deleted"<c:if test="${param.statusSearch eq 'deleted'}">selected</c:if>>Deleted</option>
        <option value="active"<c:if test="${param.statusSearch eq 'active'}">selected</c:if>>Active</option>
    </select> 
    <input type="submit" value="Search" name="btnAction" />
</form><br/>