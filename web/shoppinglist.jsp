<%-- 
    Document   : shoppinglist
    Created on : Mar 2, 2023, 8:02:32 PM
    Author     : ADMIN
--%>

<%@page import="nhan.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <h3>
            <a href="loginPage">Back to Login</a>
        </h3>
        <c:set var="listItems" value="${requestScope.ITEMS_LIST}"/>
        <c:if test="${not empty listItems}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>SKU</th>
                        <th>Name Product</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${listItems}" varStatus="counter">
                    <form action="addItemToCartController" method="POST">            
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${item.getSku()}
                                <input type="hidden" name="txtSku" value="${item.getSku()}">
                                <input type="hidden" name="txtQuanity" value="${item.getQuantity()}"/>
                            </td>
                            <td>${item.getNameProduct()}</td>
                            <td>${item.getPrice()}</td>
                            <td>${item.getDescription()}</td>
                            <td>
                                <input type="submit" name="btAction" value="AddItemToYourCart"/>
                            </td>
                        </tr>
                    </form> 
                </c:forEach>
            </tbody>
        </table>
        <!--view cart-->
        <form action="bookCartBySkuController" method="POST">
            <br/><input type="submit" value="ViewYourCart" name="btAction">
        </form>
    </c:if>
    <c:if test="${empty listItems}">
        <h2>No record in here</h2>
    </c:if>
</body>
</html>





<%--
<%@page import="nhan.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book Store</h1>
        
        <%
            List<ProductDTO> listItems = (List<ProductDTO>)request.getAttribute("ITEMS_LIST");
            if(listItems != null) {
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>SKU</th>
                            <th>Name Product</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count = 0;
                            for (ProductDTO item : listItems) {
                                    %>
                        <form action="DispatchServlet" method="POST">            
                            <tr>
                                <td><%= ++count %></td>
                                <td>
                                    <%= item.getSku() %>
                                    <input type="hidden" name="txtSku" value="<%= item.getSku() %>">
                                    <input type="hidden" name="txtQuanity" value="<%= item.getQuantity() %>"/>
                                </td>
                                <td><%= item.getNameProduct() %></td>
                                <td><%= item.getPrice() %></td>
                                <td><%= item.getDescription() %></td>
                                <td>
                                    <input type="submit" name="btAction" value="AddItemToYourCart"/>
                                </td>
                            </tr>
                        </form>   
                        <%
                                }
                        %>
                    </tbody>
                </table>
                    <form action="DispatchServlet" method="POST">
                        <br/><input type="submit" value="ViewYourCart" name="btAction">
                    </form>
        <%
            } else {
                %>
                <h2>No record in here</h2>
        <%
            }
        %>
    </body>
</html>
--%>