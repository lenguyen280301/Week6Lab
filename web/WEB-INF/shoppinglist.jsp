<%-- 
    Document   : shoppinglist
    Created on : Feb 26, 2023, 8:59:28 PM
    Author     : Wicncjjd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello ${username}</p>
        <p><a href="ShippingList?action=logout">Logout</a></p>
        
        <form action="ShippingList" method="POST">
            <h2>Add item</h2>
            <input type="text" name="item"><input type="submit" value="Add item"> 
            <input type="hidden" name="action" value="add"> 
        
        </form>

        <form action="ShippingList" method="POST"> 
        <c:forEach items="${itemlist}" var="item" varStatus="status">
            
            <input type="radio" name="itemSelected"
                   value="${item}"> ${item}
            <br>
            
        </c:forEach>
                    
            <input type="submit" value="delete">
            <input type="hidden" name="action" value="delete">
            
        </form>
    </body>
</html>
