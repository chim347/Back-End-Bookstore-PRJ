<%-- 
    Document   : createNewAccount
    Created on : Mar 9, 2023, 9:40:38 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="createNewAccountController" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"></c:set>

                Username* <input type="text" name="txtUsername" 
                                 value="${param.txtUsername}"/>(6 - 20 chars)<br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                ${errors.usernameLengthError}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}
                </font><br/>
            </c:if>    

            Password* <input type="password" name="txtPassword" value=""/>(6 - 20 chars)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                ${errors.passwordLengthError}
                </font><br/>
            </c:if>

            Confirm* <input type="password" name="txtConfirm" value=""/><br/>
            <c:if test="${not empty errors.confirmNotMacthed}">
                <font color="red">
                ${errors.confirmNotMacthed}
                </font><br/>
            </c:if>

            Full name* <input type="text" name="txtFullname" 
                              value="${param.txtFullname}"/>(6 - 20 chars)<br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">
                ${errors.fullnameLengthError}
                </font><br/>
            </c:if>

            <input type="submit" name="btAction" value="CreateNewAccount"/>
            <input type="reset" value="Reset"/>
        </form>
        <a href="loginPage">Sign up!!!</a>
    </body>
</html>
