<%-- 
    Document   : commentArticle
    Created on : Sep 3, 2020, 8:11:09 PM
    Author     : tuyet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 style="font : bold">Your Comment</h3>
<form action="MainController" method="POST">
    <input type="text" name="txtComment" value="" />
    <input type="hidden" name="txtID" value="${param.txtID}" />
    <input type="submit" value="Post Comment" name="btnAction" />
</form>