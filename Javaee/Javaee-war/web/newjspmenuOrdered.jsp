<%-- 
    Document   : newjspmenuOrdered
    Created on : 2018-4-5, 2:02:57
    Author     : Zheng Liang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Ordered Information</title>
    </head>
    <body>
        <h1>Menu Ordered Information</h1>
        <form action="./MenuOrderedServlet" method="POST">
            <table>
            <tr>
                <td>Menu ID</td>
                <td><input type="text" name="menuOrderedId" value="${menuOrdered.menuOrdermedId}" /></td>
            </tr>
            <tr>
                <td>Menu Name</td>
                <td><input type="text" name="menuOrderedName" value="${menuOrdered.menuOrderedName}" /></td>
            </tr>
            <tr>
                <td>Type</td>
                <td><input type="text" name="menuOrderedtype" value="${menuOrdered.menuOrderedtype}" /></td>
            </tr>
            <tr>
                <td>Picture</td>
                <td><input type="text" name="menuOrderedpic" value="${menuOrdered.menuOrderedpic}" /></td>
            </tr>
             <tr>
                <td>Number</td>
                <td><input type="text" name="number" value="${menuOrdered.number}" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" name="action" value="Add" />
                    <input type="submit" name="action" value="More" />
                    <input type="submit" name="action" value="Less" />
                    <input type="submit" name="action" value="Delete" />
                    <input type="submit" name="action" value="Search" />
                </td>
            </tr>
        </table>
        </form>
        
            <br>
            <table border="1">
                <th>ID</th>
                <th>Menu Name</th>
                <th>Type</th>
                <th>Picture</th>
                <th>Number</th>
                <c:forEach items="${allMenuOrdered}" var="menuO">
                    <tr>
                        <td>${menuO.menuOrdermedId}</td>
                        <td>${menuO.menuOrderedName}</td>
                        <td>${menuO.menuOrderedtype}</td>
                        <td>${menuO.menuOrderedpic}</td>
                        <td>${menuO.number}</td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
