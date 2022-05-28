<%-- 
    Document   : index.jsp
    Created on : Nov 19, 2021, 10:33:03 AM
    Author     : Koddy Rae Madriaga
--%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Exam 2: Product Manager</h1>
        
        <h2>Add Product</h2><br>
        <form action="ProductController" method="GET">
            Enter product name: <input type="text" size="25" name="productname"><br>
            Enter unit cost: <input type="int" size="25" name="unitcost"><br>
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
            </form>
        <b> ${requestScope.message}</b><br>
        
        <h2>List of Products</h2>
        <table border="1">
            <th>Product</th><th>Product Cost</th><th>Delete</th>
            
            <c:forTokens var="productLine" delims=";" items="${requestScope.productList}" >
                <c:set var="productDetails" value="${fn:split(productLine,',')}"/>
                <tr>
                    <td>${productDetails[2]}</td>
                    <td>${productDetails[1]}</a></td>
                    <td><a href="ProductController?delete=${productDetails[0]}">Delete</a></td>
                </tr>
            </c:forTokens>
        </table>   
    </body>
</html>
