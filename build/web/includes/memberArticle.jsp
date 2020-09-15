<%-- 
    Document   : userSearch
    Created on : Sep 2, 2020, 5:13:11 PM
    Author     : tuyet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${not empty requestScope.ART_LIST}">
    <h2>Article</h2>
    <table border='1'>
        <thead>
            <tr>
                <th>No.</th>
                <th>Title</th>
                <th>Description</th>
                <th>Author</th>
                <th>Date Posted</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="artDTO" items="${requestScope.ART_LIST}" varStatus="Counter">
                <tr>
                    <td>${requestScope.NEXT + Counter.count}</td>
                    <td> <form action="MainController" method="POST">
                            <input type="hidden" name="txtID" value="${artDTO.id}" />
                            <input type="hidden" name="btnAction" value="Detail" />
                            <input type="submit" value="${artDTO.title}" />
                        </form>
                    </td>
                    <td>${artDTO.shortDescription}</td>
                    <td>${artDTO.author.fullname}</td>
                    <td>${artDTO.postingDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br/>
    <c:if test="${not empty requestScope.TOTAL_ROW and requestScope.TOTAL_PAGE > 1}">
        <c:forEach var="page" begin="1" end="${requestScope.TOTAL_PAGE}">
            <form action="MainController" method="POST" style="display: inline">
                <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
                <input type="hidden" name="btnAction" value="Search" />
                <input type="submit" value="${page}" name="page" />
            </form>
        </c:forEach>
    </c:if>

</c:if>
<c:if test="${empty requestScope.ART_LIST}">
    <h3>No Result</h3>
</c:if>
