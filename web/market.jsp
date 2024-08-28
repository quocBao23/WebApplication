<%@page import="java.util.ArrayList"%>
<%@page import="baonhq.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="baonhq.product.ProductDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Book Market</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        Demo
        <!-- Bang chi hien khi quantity > 0 va Status = true -->
        <form action="DispatchServlet">
            Choose Book <select name="dtlBook">
                <c:set var="resutl" value="${requestScope.SHOW_PRODUCT}"/>
                <c:if test="${not empty resutl}">
                    <c:forEach var="product" items="${resutl}">
                        <option>${product.name}</option>
                    </c:forEach>
                </c:if>
            </select>
            Quantity <input type="number" name="txtQuantity" value="1" /><br/>
            <input type="submit" value="Add Book to your Cart" name="btAction" />
            <input type="submit" value="View Cart" name="btAction" /> <br>
            <a href="login.html">Back To Login</a>
        </form>


        <%-- Old version     
    <form action="DispatchServlet">
        Choose Book <select name="dtlBook">
                 <%
                     List<ProductDTO> resutl = (ArrayList<ProductDTO>)request.getAttribute("SHOW_PRODUCT");
                     if ( resutl != null ){
                         for (ProductDTO product : resutl) {
                                 %>
                             <option><%= product.getName() %></option>
                             
                 <%
                         }
                     }
                 
                 %>
             </select> 
             Quantity <input type="number" name="txtQuantity" value="1" /><br/>
             <input type="submit" value="Add Book to your Cart" name="btAction" />
             <input type="submit" value="View Cart" name="btAction" />
    </form> --%>
    </body>
</html>
