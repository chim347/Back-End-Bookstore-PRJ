<%-- 
     Document   : search
    Created on : Feb 16, 2023, 11:49:14 PM
    Author     : ADMIN
--%>
<%@page import="nhan.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Search Page</title>
    </head>
    <body>
        <!--Welcome-->
        <h1>
            <font color="red">
                Welcome, ${sessionScope.USER.lastname}
            </font>
        </h1>
            
        <!--Logout-->
        <c:url var="urlLogout" value="logoutController">
            <c:param name="btAction" value="Logout"/>
        </c:url>
        <a href="${urlLogout}">Logout</a>
        
        <!--Search Page-->
        <h1>Search Page</h1>
        <form action="searchLastNameController" method="POST">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue.trim()}"/><br/>
            <input type="submit" value="Search" name="btAction"/>
            <%-- <input type="submit" value="Lougout" name="btAction"/> --%>
             
        </form><br/>
        <c:set var="searchValue" value="${param.txtSearchValue.trim()}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateAccountController" method="POST">
                        <tr>
                            <td><!-- ++count = 1 --> 
                                ${counter.count}
                            .</td>
                            <td><!--username-->
                                ${dto.username}
                                <input type="hidden" name="txtUsername" 
                                       value="${dto.username}" />
                            </td>
                            <td><!--password-->
                                <input type="text" name="txtPassword" 
                                       value="${dto.password}" />
                            </td>
                            <td> <!-- lastname -->
                                ${dto.lastname}
                            </td>
                            <td><!--role-->
                                <input type="checkbox" name="chkAdmin" value="ON" 
                               <c:if test="${dto.role}">
                                   checked="checked"
                               </c:if>
                               /> 
                            </td>
                            <td>
                                <!--String urlRewriting = "DispatchServlet"
                                + "?btAction=delete"
                                + "&primaryKey=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;-->
                                <c:url var="urlRewriting" value="deleteAccountController">
                                    <c:param name="btAction" value="delete"/>
                                    <c:param name="primaryKey" value="${dto.username}"/>
                                    <c:param name="lastSearchValue" value="${searchValue}"/>
                                </c:url>
                                <a href="${urlRewriting}">Delete</a>
                            </td>
                            <td><!-- update -->
                                <input type="submit" name="btAction" value="Update" />
                                <input type="hidden" name="lastSearchValue"
                                       value="${searchValue}" />
                            </td>
                        </tr>
                        </form>  
                        </c:forEach>
            </c:if>
            <c:if test="${empty result}">
                <h2>NO record is matched</h2>
            </c:if>
        </c:if>   
    </body>
</html>


<%--
<%@page import="nhan.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Search Page</title>
    </head>
    <body>
        <%
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                String username = "";
                //Cookie lastCookie = cookies[cookies.length - 1];
                for (Cookie cookie : cookies) {
                    if(!cookie.getName().equals("JSESSIONID")){
                        username = cookie.getName();
                    }
                }
                %>
                <h1>
                    <font color="red">
                    Welcome, <%= username %>
                    </font>
                </h1>
        <%
            }// end cookie are existed
        %>
        <a href="DispatchServlet?btAction=Logout">Logout</a>
        <h1>Search Page</h1>
        <form action="DispatchServlet" method="POST">
            Search Value <input type="text" name="txtSearchValue" 
                                value="<%= request.getParameter("txtSearchValue")%>"/><br/>
            <input type="submit" value="Search" name="btAction"/>
            <!-- <input type="submit" value="Lougout" name="btAction"/> -->
             
        </form><br/>
        
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                                for (RegistrationDTO dto : result) {
                                    String urlRewriting = "DispatchServlet"
                                            + "?btAction=delete"
                                            + "&primaryKey=" + dto.getUsername()
                                            + "&lastSearchValue=" + searchValue;
                                        %>
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td><!-- ++count = 1 --> 
                                    <%= ++count %>
                                </td>
                                <td>
                                    <%= dto.getUsername() %>
                                    <input type="hidden" name="txtUsername" 
                                           value="<%= dto.getUsername() %>" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="<%= dto.getPassword() %>" />
                                </td>
                                <td>
                                    <%= dto.getLastname() %>
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <%
                                               if(dto.isRole()) {
                                                   %>
                                                   checked="checked"
                                           <%
                                               }// role is admin
                                           %>
                                           /> 
                                </td>
                                <td>
                                    <a href="<%= urlRewriting %>">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" name="btAction" value="Update" />
                                    <input type="hidden" name="lastSearchValue"
                                           value="<%= searchValue %>" />
                                </td>
                            </tr>
                        </form>    
                            <%
                                } // end traverse DTO
                            %>
                        </tbody>
                    </table>

        <%
                } else {
                    %>
                    <h2>NO record is matched</h2>
        <%
                } // end no record is matched
            } // end search Value get valid value
        %>
    </body>
</html> 
--%>



