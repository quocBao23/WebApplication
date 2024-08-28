<%-- 
    Document   : search
    Created on : Jun 6, 2024, 1:11:21 PM
    Author     : Admin
--%>


<%-- <%@page import="baonhq.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false"%> --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>

        <font color="red">
        Welcome ${sessionScope.USER.fullname}
        </font>

        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" value="Search" name="btAction" />
        </form><br>

        <c:set var="searchValue" value="${param.txtSearch}"/>

        <c:if test="${not empty searchValue}" >
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
            <c:if test="${not empty result }" >
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
                    <form action="DispatchServlet">
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                            <form>
                                <tr>
                                    <td>${counter.count}</td>

                                    <td>
                                        ${dto.username}
                                        <input type="hidden" name="txtUsername" value="${dto.username}" />
                                    </td>

                                    <td>                                        
                                        <input type="text" name="txtPassword" value="${dto.password}" />
                                    </td>

                                    <td>${dto.fullname}</td>

                                    <td>
                                        <input type="checkbox" name="chkAdmin" value="ON" 
                                               <c:if test="${dto.isAdmin}">
                                                   checked="checked" />
                                        </c:if>                                         
                                    </td>

                                    <td>
                                        <c:url var="urlRewriting" value="DispatchServlet">
                                            <c:param name="btAction" value="Delete"/>
                                            <c:param name="pk" value="${dto.username}"/>
                                            <c:param name="lastSearchValue" value="${searchValue}"/>
                                        </c:url>
                                        <a href="${urlRewriting}">Delete</a>
                                    </td>

                                    <td>
                                        <input type="submit" value="Update" name="btAction" />  
                                        <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                    </td>
                                </tr>
                            </form>

                        </c:forEach>
                        </tbody>
                    </form>

                </table>

            </c:if>
            <c:if test="${empty result }" >
                <h2>Not Found
                </c:if>
            </c:if>
            <br>
            <form action="DispatchServlet">
                <input type="submit" value="Log Out" name="btAction" />
            </form>        




            <%-- Old version     
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
                 
            %> --%>

    </body>
</html>
