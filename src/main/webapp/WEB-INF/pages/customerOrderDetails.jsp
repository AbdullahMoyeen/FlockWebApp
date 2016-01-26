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

        <title>Flock Order Details</title>

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

        <div class="section">

            <table class="childTable" border="1" style="width: 820px;margin: auto;border-spacing: 0px; empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">Order Details</caption>
                <tr>
                    <th colspan="2" style="text-align: center">Product</th>
                    <th style="text-align: right">Price</th>
                    <th style="text-align: right">Quantity</th>
                    <th style="text-align: right">Subtotal</th>
                </tr>
                <c:forEach var="shoppingCart" items="${shoppingCartList}">
                    <tr>
                        <td style="text-align: center; border-right: hidden;"><a href="<c:url value="/product/detail?productID=${shoppingCart.productID}"/>"><img src="${shoppingCart.imageFileLocation}/${shoppingCart.imageFileName}" width="100"></a></td>
                        <td style="text-align: left; border-left: hidden;"><a href="<c:url value="/product/detail?productID=${shoppingCart.productID}"/>">${shoppingCart.productName}<br>${shoppingCart.productDescription}</a></td>
                        <td style="text-align: right">$${shoppingCart.pricePaid}</td>
                        <td style="text-align: right">${shoppingCart.quantity}</td>
                        <td style="text-align: right">$${shoppingCart.orderItemSubTotal}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <th colspan="4" style="text-align: left">Total before Tax</th>
                    <th colspan="1" style="text-align: right">$${order.orderSubTotal}</th>
                </tr>
                <tr>
                    <th colspan="4" style="text-align: left">Tax</th>
                    <th colspan="1" style="text-align: right">$${order.taxTotal}</th>
                </tr>
                <tr>
                    <th colspan="4" style="text-align: left">Shipping & Handling</th>
                    <th colspan="1" style="text-align: right">$${order.shippingTotal}</th>
                </tr>
                <tr>
                    <th colspan="4" style="text-align: left">Order Total</th>
                    <th colspan="1" style="text-align: right">$${order.orderTotal}</th>
                </tr>
            </table>

            <table class="mainTable" style="margin: auto;">
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="button" value="Go Back" style="width: 400px; height: 40px; font-size: large" onclick="history.go(-1);" title="Go back to order list"></td>
                    <td></td>
                    <td><input type="button" value="Cancel Order" style="width: 400px; height: 40px; font-size: large" onClick="location.href='<c:url value="/order/cancelOrder?orderID=${order.orderID}"/>'"></td>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </table>

        </div>

    </body>

</html>
