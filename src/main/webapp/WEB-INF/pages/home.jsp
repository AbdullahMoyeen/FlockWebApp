<%--
  Created by IntelliJ IDEA.
  User: amoyeen
  Date: 2/24/15
  Time: 6:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>

    <head>

        <title>Flock Home</title>

        <style>
            .header {
                background-color:darkorange;
                color:white;
                width:100%;
                height:80px;
                padding:10px;
                text-align:center;
            }
            .nav {
                background-color:gold;
                width:100%;
                height:40px;
                padding:10px;
                float:top;
            }
            .section {
                width:100%;
                height: 1180px;
                padding:10px;
                float:top;
            }
        </style>

    </head>

    <body>

        <div class="header">

            <h1>Flock</h1>

        </div>

        <div class="nav">

            <table width="100%" style="margin: auto;">
                <tr>
                    <th><a href="<c:url value="/product/list?searchString="/>">Products</a></th>
                    <security:authorize access="! isAuthenticated()">
                        <th>
                            <a href="<c:url value="/signUp"/>">Sign Up</a>
                        </th>
                    </security:authorize>
                    <security:authorize access="! isAuthenticated()">
                        <th>
                            <a href="<c:url value="/signIn"/>">Sign In</a>
                        </th>
                    </security:authorize>
                    <th><a href="<c:url value="/aboutUs"/>">About Us</a></th>
                    <th><a href="<c:url value="/contactUs"/>">Contact Us</a></th>
                    <th>
                        <security:authorize access="isAuthenticated()">
                            Hello ${sessionScope.customer.firstName}!
                            <c:set var="userID">
                                <security:authentication property="principal.userId" />
                            </c:set>
                            <a href="<c:url value="/customer/viewProfile?customerID=${userID}" />"><br>View Account</a>
                        </security:authorize>
                    </th>
                    <th>
                        <security:authorize access="isAuthenticated()">
                            Not ${sessionScope.customer.firstName}?
                            <a href="<c:url value="/signOut"/>"><br>Sign Out</a>
                        </security:authorize>
                    </th>
                    <c:if test="${sessionScope.order.orderLineCount>0}"><th><a href="<c:url value = "/shoppingCart/viewCart?orderID=${sessionScope.order.orderID}"/>">View Cart(${sessionScope.order.orderLineCount})</a></th></c:if>
                </tr>
            </table>

        </div>

        <div class="section">

            <table style="margin: auto;">
                <tr>
                    <td>
                        <c:url value="/product/list" var="formUrl"/>
                        <form action="${formUrl}" method="GET">
                            <INPUT TYPE = "search" NAME="searchString" STYLE="width: 500px; height: 50px; font-size: x-large; border-radius: 5px; border-width: thick; border-color: cadetblue;">
                            <INPUT TYPE = "Submit" VALUE = "Search" STYLE="width: 100px; height: 30px; font-size: large;">
                        </form>
                    </td>
                </tr>
            </table>

        </div>

    </body>

</html>
