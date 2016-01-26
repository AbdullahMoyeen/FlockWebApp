<%--
  Created by IntelliJ IDEA.
  User: amoyeen
  Date: 3/1/15
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>

    <head>

        <title>Flock Order Confirmation</title>

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
                height: 600px;
                padding:10px;
                float:top;
            }
            form.mainForm input, select {
                width: 400px;
                height: 40px;
                font-size: large;
            }
            table.mainTable th, td {
                font-size: 25px;
                color: black;
                text-align: left;
                height: 40px;
                padding:5px;
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
                <th><a href="/">Home</a></th>
                <th><a href="/product/list?searchString=">Products</a></th>
                <th>
                    <security:authorize access="! isAuthenticated()">
                        <a href="<c:url value="/signUp" />">Sign Up</a>
                    </security:authorize>
                </th>
                <th>
                    <security:authorize access="! isAuthenticated()">
                        <a href="<c:url value="/signIn" />">Sign In</a>
                    </security:authorize>
                </th>
                <th><a href="/">About Us</a></th>
                <th><a href="/">Contact Us</a></th>
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
                        <a href="<c:url value="/signOut" />"><br>Sign Out</a>
                    </security:authorize>
                </th>
                <c:if test="${sessionScope.order.orderLineCount>0}"><th><a href="/shoppingCart/viewCart?orderID=${sessionScope.order.orderID}">View Cart(${sessionScope.order.orderLineCount})</a></th></c:if>
            </tr>
        </table>

    </div>

    <div class="section">

        <table class="mainTable" style="margin: auto;">
            <caption style="font-size: x-large;font-weight: bold;text-decoration: underline;padding: 20px">Order Confirmation</caption>
            <tr>
                <th style="text-align: center;">Your order has been placed successfully<c:if test="${param.backOrderedItem}"><br><br><span style="color: orange;">Some items may be back-ordered due to limited availability</span></c:if><br><br>Please check your inbox for order confirmation email<br><br>Thanks for shopping with us ${sessionScope.customer.firstName}!</th>
            </tr>
        </table>

    </div>

    </body>

</html>
