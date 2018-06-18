<%-- 
    Document   : calcjsp
    Created on : 2018-3-20, 14:59:26
    Author     : Zheng Liang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="calcservlet" method="POST">
            <input type="text" name="t1">
            <input type="text" name="t2">
            <input type="submit" value="ADD">
        </form>
    </body>
</html>
