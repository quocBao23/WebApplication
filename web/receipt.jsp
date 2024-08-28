<%-- 
    Document   : receipt
    Created on : Jul 3, 2024, 2:14:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receipt</title>
    </head>
    <body>
        <c:set var="order" value="${requestScope.ORDER}" />
        Customer Name: ${order.customer} <br>
        Address: ${order.address} <br>
        Email: ${order.email}

        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Unit Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="orderDetail" value="${requestScope.ORDER_DETAIL}"/>
                <c:forEach var="item" items="${orderDetail}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>     
                        <td>${item.key}</td>
                        <td>${item.value.unitPrice}</td>
                        <td>${item.value.quantity}</td>
                        <td>${item.value.total}</td>
                    </tr>    
                </c:forEach>
                    <tr>
                        <td colspan="4">
                            Total
                        </td>
                        <td>
                            ${order.total}
                        </td>
                    </tr>         
            </tbody>
        </table>
                        
        <a href="ShowProductServlet">Continue Shopping</a>
    </body>
</html>
