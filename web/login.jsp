<%-- 
    Document   : login
    Created on : Mar 13, 2023, 10:20:12 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <c:set var="errors" value="${requestScope.ERROR_LOGIN}"/>
        <form action="loginController" method="POST">
            <c:if test="${not empty errors.acountNotFound}">
                <font color="red">
                ${errors.acountNotFound}
                </font>
            </c:if>
            <table>
                <tr>
                    <td>
                        User Name* <input type="text" name="txtUsername" value="${param.txtUsername}"/><br/>
                        <c:if test="${not empty errors.userNameEmpty}">
                            <font color="red">
                                ${errors.userNameEmpty}
                            </font>
                        </c:if>
                    </td><br/>
                </tr>
                <tr>
                    <td>
                        Password* <input type="password" name="txtPassword" value=""/><br/>
                        <c:if test="${not empty errors.passwordEmpty}">
                            <font color="red">
                                ${errors.passwordEmpty}
                            </font>
                        </c:if>
                    </td><br/>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="btAction" value="Login"/>
                        <input type="reset" value="Reset"/>
                    </td>
                </tr>
            </table>
        </form><br/>

        <button><a href="onlineShoppingHtml">Shopping Now for static!!!</a></button><br/>
        <br/>
        <!--ShoppingPageServlet-->
        <button><a href="shoppingPageController">Shopping with dynamic</a></button><br/>
        <br/>
        <button><a href="createNewAccountHtml">Create Account on HTML</a></button>
    </body>
</html>
