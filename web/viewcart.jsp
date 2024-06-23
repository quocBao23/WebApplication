<%-- 
    Document   : viewcart
    Created on : Jun 17, 2024, 2:01:27 PM
    Author     : Admin
--%>

<%@page import="java.util.Map"%>
<%@page import="baonhq.cart.CartBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your Cart</h1>

        <%
            //1. Customer goes to his/her cart
            if (session != null) {
                //2. Customer take his/her cart
                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer take his/her items ( ngan chua )
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Show tat ca mon do
                        %>
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
                                <%
                                    int count = 0;
                                    for (String key : items.keySet()) {
                                    %>
                                    <tr>
                                        <td> <%=++count%></td>
                                         <td><%= key %></td>
                                         <td><%=items.get(key)%></td>
                                         <td>
                                             <input type="checkbox" name="chkItem" value="<%= key %>" />
                                         </td>
                                    </tr>
                                <%        
                                        }
                                %>
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
                                <br><br>
                                CustomerName <input type="text" name="customerName" value="" /> <br>
                                Address <textarea id="w3review" name="customerAddress" rows="2" cols="20"></textarea> <br>
                                Email <input type="email" name="customerEmail" value=""  /> <br>
                                <input type="submit" value="Check Out" name="btAction" />
                        </form>
                        <%
                    }
                }
            }
        %>
        <h2>
           
            
        </h2>
    </body>
</html>
