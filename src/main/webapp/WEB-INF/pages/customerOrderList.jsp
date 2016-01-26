<%--
  Created by IntelliJ IDEA.
  User: amoyeen
  Date: 3/1/15
  Time: 12:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>

    <head>

        <title>Flock Open Orders</title>

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
            table.linkTable th, td {
                font-size: 20px;
                color: black;
                text-align: left;
                height: 40px;
                padding:5px;
            }
            table.childTable th {
                font-size: 20px;
                color: black;
                text-align: left;
                height: 40px;
                padding:5px;
            }
            table.childTable td {
                font-size: 15px;
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
                    <th><a href="<c:url value="/"/>">Home</a></th>
                    <th><a href="<c:url value="/product/list?searchString="/>">Products</a></th>
                    <security:authorize access="! isAuthenticated()">
                        <th>
                            <a href="<c:url value="/signUp" />">Sign Up</a>
                        </th>
                    </security:authorize>
                    <security:authorize access="! isAuthenticated()">
                        <th>
                            <a href="<c:url value="/signIn" />">Sign In</a>
                        </th>
                    </security:authorize>
                    <th><a href="<c:url value="/aboutUs"/>">About Us</a></th>
                    <th><a href="<c:url value="/contactUs"/>">Contact Us</a></th>
                    <security:authorize access="isAuthenticated()">
                        <th>
                            Hello ${sessionScope.customer.firstName}!
                            <c:set var="userID">
                                <security:authentication property="principal.userId" />
                            </c:set>
                            <a href="<c:url value="/customer/viewProfile?customerID=${userID}" />"><br>View Account</a>
                        </th>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
                        <th>
                            Not ${sessionScope.customer.firstName}?
                            <a href="<c:url value="/signOut" />"><br>Sign Out</a>
                        </th>
                    </security:authorize>
                    <c:if test="${sessionScope.order.orderLineCount>0}"><th><a href="<c:url value="/shoppingCart/viewCart?orderID=${sessionScope.order.orderID}"/>">View Cart(${sessionScope.order.orderLineCount})</a></th></c:if>
                </tr>
            </table>

        </div>

        <div class="section">

            <table class="childTable" border="1" style="width: 100%;margin: auto;border-spacing:10px;empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">${sessionScope.customer.firstName}'s Open Orders</caption>
                <tr>
                    <th>Order #</th>
                    <th style="text-align: center;">Order Placed</th>
                    <th style="text-align: right;">Order Total</th>
                    <th>Ship To</th>
                    <th style="text-align: center;">Order Details</th>
                    <th style="text-align: center;">Cancel</th>
                </tr>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td>${order.orderID}</td>
                        <td style="text-align: center;">${order.orderPlaced}</td>
                        <td style="text-align: right;">$${order.orderTotal}</td>
                        <td><strong>${order.shippingToName}</strong><br>${order.shippingAddressLine1}<c:if test="${!empty order.shippingAddressLine2}"><br>${order.shippingAddressLine2}</c:if><br>${order.shippingCity}, ${order.shippingStateCode}-${order.shippingPostalCode}</td>
                        <td style="text-align: center"><a href="<c:url value="/customer/viewOrderDetails?orderID=${order.orderID}"/>">View Order Details</a></td>
                        <td style="text-align: center;"><input type="button" value="Cancel Order" onClick="location.href='<c:url value="/order/cancelOrder?orderID=${order.orderID}"/>'"></td>
                    </tr>
                </c:forEach>
            </table>

            <table class="mainTable" style="margin: auto;">
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="button" value="Go Back" style="width: 400px; height: 40px; font-size: large" onClick="location.href='<c:url value="/customer/viewProfile?customerID=${sessionScope.customer.customerID}"/>'" title="Go back to profile"></td>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </table>

        </div>

    </body>

</html>
