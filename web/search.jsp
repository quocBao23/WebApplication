<%-- 
    Document   : search
    Created on : Jun 6, 2024, 1:11:21 PM
    Author     : Admin
--%>

<%@page import="baonhq.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <% 
            // if exitsted cookies => login 
            //1. get all Cookies
            Cookie [] cookies = request.getCookies();
            //2. check exitsted cookies
            if ( cookies != null ){
                Cookie recentCookie = cookies[cookies.length - 1];
                String username = recentCookie.getName();
        %>
                <font color="color">
                    welcome <%= username %>
                 </font>
                
            <%
                }
            %>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearch" value="<%= request.getParameter("txtSearch") %>" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        
            <%
                 String searchValue = request.getParameter("txtSearch");
                 
                 if (searchValue != null){
                     List<RegistrationDTO> result = (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
                     if (result != null){
                         //has values
                         %> 
                         <table border="1">
                             <thead>
                                 <tr>
                                     <th>No</th>
                                     <th>Username</th>
                                     <th>Password</th>
                                     <th>LastName</th>
                                     <th>Role</th>
                                     <th>Delete</th>
                                     <th>Update</th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <%
                                     int count =0;
                                     for (RegistrationDTO dto : result) {
                                         String urlRewiting = "DispatchServlet"
                                                 + "?btAction=Delete"
                                                 + "&pk="+dto.getUsername()
                                                 + "&lastSearchValue="+searchValue;
                                        %> 
                             <form action="DispatchServlet" method="POST">
                                        <tr>
                                     <td>
                                         <%= ++count %>
                                     </td>
                                     <td>
                                         <%= dto.getUsername() %>
                                         <input type="hidden" name="txtUsername" value="<%= dto.getUsername() %>" />
                                     </td>
                                     <td>
                                         <input type="text" name="txtPassword" value="<%= dto.getPassword() %>" />
                                     </td>
                                     <td>
                                         <%= dto.getFullname() %>
                                     </td>
                                     <td>
                                         <input type="checkbox" name="chkAdmin" value="ON" 
                                                <% 
                                                    if (dto.isIsAdmin()){
                                                        %>
                                                        checked="checked"
                                                <%
                                                    }
                                                %>
                                                />
                                     </td>
                                     <td>
                                         <a href="<%=urlRewiting%>">Delete</a>
                                     </td>
                                     <td>
                                         <input type="submit" value="Update" name="btAction" />
                                         <input type="hidden" name="lastSearchValue" value="<%= searchValue %>" />
                                     </td>
                                 </tr>
                             </form> 
                                 <%
                                         }//process
                                 %>
                             </tbody>
                         </table>

        
        <%
                     }
                     else{
                         %> 
                         <h2>
                             No record is matched !!!
                         </h2>
                         
            <%//no value
                     }
                 }
                 
            %>
    </body>
</html>
