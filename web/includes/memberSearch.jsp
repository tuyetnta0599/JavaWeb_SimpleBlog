<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="MainController" method="POST">
    Search : <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br/>
    <input type="submit" value="Search" name="btnAction" />
</form><br/>