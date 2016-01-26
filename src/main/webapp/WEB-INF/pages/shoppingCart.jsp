<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2/24/2015
  Time: 8:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>

    <head>

        <title>Flock Shopping Cart</title>

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
            .warning {
                font-weight: bold;
                font-size: x-large;
                color: orange;
                text-align: center;
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
                </tr>
            </table>

        </div>

        <div class="section">

            <c:if test="${param.quantityOnHand > 0}">
                <div class="warning">
                    <br>The item you just added may be back-ordered due to limited availability
                    <br>Please consider reducing the quantity to ${param.quantityOnHand} or less<br><br>
                </div>
            </c:if>

            <table class="childTable" border="1" style="width: 820px;margin: auto;border-spacing: 0px; empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">Shopping Cart</caption>
                <tr>
                    <th colspan="2" style="text-align: center">Product</th>
                    <th style="text-align: right">Price</th>
                    <th style="text-align: right">Quantity</th>
                    <th style="text-align: right">Subtotal</th>
                </tr>
                <c:forEach var="shoppingCart" items="${shoppingCartList}">
                    <tr>
                        <td style="text-align: center; border-right: hidden;"><a href="<c:url value="/product/detail?productID=${shoppingCart.productID}"/>"><img src="${shoppingCart.imageFileLocation}/${shoppingCart.imageFileName}" width="100"></a></td>
                        <td style="text-align: left; border-left: hidden;"><a href="<c:url value="/product/detail?productID=${shoppingCart.productID}"/>">${shoppingCart.productName}<br>${shoppingCart.productDescription}</a><br><br>
                            <form action="/shoppingCart/removeItem" method="GET">
                                <input type = "hidden" name="orderID" value="${shoppingCart.orderID}"/>
                                <input type = "hidden" name="orderItemID" value="${shoppingCart.orderItemID}"/>
                                <input type = "submit" value = "Remove from Cart">
                            </form>
                        </td>
                        <td style="text-align: right">$${shoppingCart.pricePaid}</td>
                        <td style="text-align: right">
                            <form action="/shoppingCart/updateItem" method="GET">
                                <input type = "hidden" name="orderID" value="${shoppingCart.orderID}"/>
                                <input type = "hidden" name="orderItemID" value="${shoppingCart.orderItemID}"/>
                                <input style="width: 100%; text-align: right;" name="quantity" type="number" value=${shoppingCart.quantity} required="true" min="1"><br><br><input type = "submit" value = "Update Quantity">
                            </form>
                        </td>
                        <td style="text-align: right">$${shoppingCart.orderItemSubTotal}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <th colspan="4" style="text-align: left">Order Subtotal<c:if test="${order.orderItemCount>0}"> (${order.orderItemCount} <c:choose><c:when test="${order.orderItemCount==1}">Item</c:when><c:otherwise>Items</c:otherwise></c:choose>)</c:if></th>
                    <th colspan="1" style="text-align: right">$${order.orderSubTotal}</th>
                </tr>
            </table>

            <form:form class="mainForm" method="post" action="/order/enterShipping">
                <input type = "hidden" name="orderID" value="${order.orderID}"/>
                <table class="mainTable" style="margin: auto;">
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="button" value="Continue Shopping" onclick="location.href='/product/list?searchString='"/></td>
                        <td></td>
                        <c:if test="${order.orderLineCount>0 && order.orderStatusCode=='P'}"><td><input type="submit" value="Proceed to Checkout"/></td></c:if>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                </table>
            </form:form>

        </div>

    </body>

</html>
