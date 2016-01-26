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

        <title>Flock About Us</title>

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
                    <th><a href="<c:url value="/"/>">Home</a></th>
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

        <div class="section" style="text-align: center; font-size: x-large">

            <p>
                This site is a production of Team 7up.
            </p>

        </div>

    </body>

</html>
