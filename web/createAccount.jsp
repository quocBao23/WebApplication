<%-- 
    Document   : createAccount
    Created on : Jun 27, 2024, 1:33:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <form action="DispatchServlet">
            <c:set var="errors" value="${requestScope.FOUND_ERROR}" />
            
            username <input type="text" name="txtUsername" value="${param.txtUsername}" /> <br>
            <c:if test="${not empty errors.userNameLength}" >
                <font color ="red">
                ${errors.userNameLength}
                </font> <br> 
            </c:if>
             
            <c:if test="${not empty errors.userNameIsExisted}" >
                <font color ="red">
                ${errors.userNameIsExisted}
                </font> <br> 
            </c:if>
                
                
            password <input type="password" name="txtPassword" value="${param.txtPassword}" /> <br>
            <c:if test="${not empty errors.userPasswordLength}" >
                <font color ="red">
                ${errors.userPasswordLength}
                </font> <br> 
            </c:if>
                
            Comfirm <input type="password" name="txtConfirm" value="${param.txtConfirm}" /> <br>
            <c:if test="${not empty errors.confirmNotMatched}" >
                <font color ="red">
                ${errors.confirmNotMatched}
                </font> <br> 
            </c:if>
                
                FullName <input type="text" name="txtFullname" value="${param.txtFullname}" /> <br>
            <c:if test="${not empty errors.userFullNameLength}" >
                <font color ="red">
                ${errors.userFullNameLength}
                </font> <br> 
            </c:if>
                
            <input type="submit" value="Create Account" name="btAction"/>
            <a href="login.html">Already have an account?</a> <br>
            <input type="reset" value="reset" />
        </form>
    </body>
</html>
