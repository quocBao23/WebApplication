<%-- 
    Document   : viewcart
    Created on : Jun 17, 2024, 2:01:27 PM
    Author     : Admin
--%>

<%@page import="java.util.Map"%>
<%@page import="baonhq.cart.CartBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <%-- 1.Customer goes to his her cart --%>

        <c:if test="${not empty pageContext.session}">  
            <%-- 2. Customer get the cart --%>
            <c:set var="cart" value="${sessionScope.CART}"/>

            <c:if test="${empty cart}">
                <font color="red">
                Don't have any item in you cart <br>
                </font>

                <a href="ShowProductServlet">Buy Book</a>
            </c:if>

            <c:if test="${not empty cart}">
                <%-- 3. Customer get the items --%>
                <c:set var="items" value="${cart.items}"/>

                <c:if test="${empty items}">
                    <font color="red">
                    Don't have any item in you cart <br>
                    </font>

                    <a href="ShowProductServlet">Buy Book</a>
                </c:if>

                <c:if test="${not empty items}">
                    <%-- 4. Customer get the item --%>
                    <%-- Show All Item --%>
                    <form action="DispatchServlet">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${items}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count} </td>
                                        <td>${item.key}</td>
                                        <td>${item.value}</td>
                                        <td>
                                            <input type="checkbox" name="chkItem" value="${item.key}" />
                                        </td>
                                    </tr>
                                </c:forEach>     
                                <tr>
                                    <td colspan="3">
                                        <a href="ShowProductServlet">Add more book</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Items" name="btAction" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                    <br>
                    <%-- Check Out Form --%>
                    <form action="DispatchServlet">
                        <c:set var="errors" value="${requestScope.USER_INFORM_ERROR}" />
                        <br>
                        CustomerName <input type="text" name="customerName" value="${param.customerName}"/> <br>
                        <c:if test="${not empty errors.userNameError}" >
                            <font color ="red">
                            ${errors.userNameError}
                            </font> <br> 
                        </c:if>
                            
                        Address <textarea name="customerAddress" rows="2" cols="20"/>${param.customerAddress}</textarea> <br>
                        <c:if test="${not empty errors.userAddressError}" >
                        <font color ="red">
                        ${errors.userAddressError}
                        </font> <br> 
                        </c:if>
                        
                        Email <input type="text" name="customerEmail" value="${param.customerEmail}" placeholder="Optional"/> <br>
                        <c:if test="${not empty errors.userEmailFormatError}" >
                        <font color ="red">
                        ${errors.userEmailFormatError}
                        </font> <br> 
                        </c:if>
                        
                    <input type="submit" value="Check Out" name="btAction" />
                </form>
            </c:if>
        </c:if>             
    </c:if>




</body>
</html>
