<%-- 
    Document   : viewCart
    Created on : Feb 28, 2023, 12:51:38 AM
    Author     : ADMIN
--%>

<%@page import="nhan.product.ProductDTO"%>
<%@page import="nhan.cart.CartProduct"%>
<%@page import="java.util.Map"%>
<%@page import="nhan.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <h1>View Book Store</h1>
        <%-- 1. Customer goes to his/her cart place --%>
        <c:if test="${not empty sessionScope}">
            <%-- 2. Customer takes --%>
            <c:set var="cart" value="${sessionScope.CART}"></c:set>
            <c:if test="${not empty cart}">
                <%-- 3. Customer gets items from cart --%>
                <c:set var="items" value="${cart.getItems()}"></c:set>
                <c:if test="${not empty items}">
                    <%-- 4. show items --%>
                    <form action="removeItemFromCartController">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>SKU</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Total</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${not empty sessionScope}">
                                    <c:set var="products" value="${sessionScope.CART_PRODUCT}"></c:set>
                                    <c:if test="${not empty products}">
                                        <c:set var="product" value="${products.getProducts()}" ></c:set>
                                        <c:if test="${not empty product}" >
                                            <c:set var="totalPrice" value="0"/>
                                            <c:forEach var="key" items="${items.keySet()}" varStatus="counter">
                                                <tr>
                                                    <td>${counter.count}</td>
                                                    <td>${key}</td>

                                                    <td><%-- quantity --%>
                                                        ${items.get(key)}
                                                    </td>

                                                    <td><%-- price --%>
                                                        ${product.get(Integer.parseInt(key)).getPrice()}
                                                    </td>
                                                    <td><%-- total --%>
                                                        ${product.get(Integer.parseInt(key)).getPrice() * items.get(key)}
                                                    </td>
                                                    <c:set var="totalPrice" value="${totalPrice + product.get(Integer.parseInt(key)).getPrice() * items.get(key)}"/>
                                                    <td>
                                                        <input type="checkbox" name="chkItem" value="${key}:${items.get(key)}"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <td colspan="3"> 
                                                    <%-- <a href="onlineShopping.html">Add more Items to Cart</a> --%>
                                                    <a href="shoppingPageController">Add more Items to Cart</a>
                                                </td>
                                                <th colspan="2">
                                                    Total Bill: ${totalPrice}  
                                                </th>
                                                <td> 
                                                    <input type="submit" value="RemoveSelectedItems" name="btAction" /> 
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:if>
                                </c:if> 
                            </tbody>
                        </table>
                    </form>
                    <form action="checkOutController" method="POST">
                        Order Id <input type="text" name="txtOrderId" value=""/><br/>
                        Name <input type="text" name="txtOrderName" value=""/><br/>
                        Address <input type="text" name="txtAddress" value=""/><br/>
                        <input type="submit" name="btAction" value="Check out"/>
                    </form>                        
                </c:if>
                <c:if test="${empty items}">
                    <h2>
                        No cart is existed
                    </h2>
                    <a href="shoppingPageController">shopping right now!!!</a>
                </c:if>         
            </c:if>
        </c:if>
        <c:if test="${empty sessionScope}">
            <h2>
                Customer not goes to his/her cart place
            </h2>
            <a href="shoppingPageController">shopping right now!!!</a>
        </c:if>
    </body>
</html>







<%--
<%@page import="nhan.product.ProductDTO"%>
<%@page import="nhan.cart.CartProduct"%>
<%@page import="java.util.Map"%>
<%@page import="nhan.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <%
            //1. Customer goes to his/her cart place
            if(session != null) { // check xem coi co ai add vao cart chua
                //2. Customer takes
                CartObject cart = (CartObject)session.getAttribute("CART");
                if(cart != null) {
                    //3. Customer gets items from cart
                    Map<String,Integer> items = cart.getItems();
                    if(items != null) {
                        //4. show items
                        %>
                        <form action="DispatchServlet">
                            <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Total</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    if(session != null) {
                                        CartProduct products = (CartProduct)session.getAttribute("CART_PRODUCT");
                                        if(products != null) {
                                            Map<Integer, ProductDTO> product = products.getProducts();
                                            if(product != null) {
                                                int count = 0;
                                                for (String key : items.keySet()) {
                                                    %>
                                                    <tr>
                                                        <td><%= ++count %></td>
                                                        <td><%= key %></td>
                                                
                                                        <td><!-- quantity -->
                                                            <%= items.get(key) %>
                                                        </td>
                                                        
                                                        <td><!-- price -->
                                                            <%= product.get(Integer.parseInt(key)).getPrice() %>
                                                        </td>
                                                        <td><!-- total -->
                                                            <%= product.get(Integer.parseInt(key)).getPrice() * product.get(Integer.parseInt(key)).getQuantity() %>
                                                        </td>

                                                        <td>
                                                            <input type="checkbox" name="chkItem" value="<%= key + ':' + items.get(key) %>"/>
                                                        </td>
                                                    </tr>
                                                    <%
                                                } // end get each item from items
                                            %>
                                                    <tr>
                                                        <td colspan="3"> 
                                                            <!-- <a href="onlineShopping.html">Add more Items to Cart</a> -->
                                                            <a href="ShoppingPageServlet">Add more Items to Cart</a>
                                                        </td>
                                                        <td> 
                                                            <input type="submit" value="RemoveSelectedItems" name="btAction" /> 
                                                        </td>
                                                    </tr>
                                <%
                                            }
                                        }
                                    }
                                            %>
                            </tbody>
                            </table>
                        </form>
                                        <form action="DispatchServlet" method="POST">
                                            Order Id <input type="text" name="txtOrderId" value=""/><br/>
                                            Name <input type="text" name="txtOrderName" value=""/><br/>
                                            Address <input type="text" name="txtAddress" value=""/><br/>
                                            <input type="submit" name="btAction" value="Check out"/>
                                        </form>
        <%
                        return;
                    }// items have values
                }// cart has existed
            }// seesion has existed
        %>
        <h2>
            No cart is existed
        </h2>
        <a href="ShoppingPageServlet">shopping right now!!!</a>
    </body>
</html>
--%>