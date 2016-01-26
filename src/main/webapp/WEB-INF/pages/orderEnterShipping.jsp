<%--
  Created by IntelliJ IDEA.
  User: amoyeen
  Date: 2/27/15
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>

    <head>

        <title>Flock Enter Shipping</title>

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
            .error {
                font-weight: bold;
                font-size: x-large;
                color: red;
                text-align: center;
            }
        </style>

    </head>

    <body>

        <div class="header">

            <h1>Flock</h1>

        </div>

        <div class="section">

            <c:if test="${param.shippingNotAllowed}"><div class="error"><br>Sorry, we are not shipping to ${param.stateCode} at this moment<br>Please enter a different address<br><br></div></c:if>

            <table class="childTable" border="1" style="width: 100%;margin: auto;border-spacing: 10px;empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">Select a Shipping Address</caption>
                <tr>
                    <th>Address Line 1</th>
                    <th>Address Line 2</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip Code</th>
                    <th style="text-align: center">Select</td>
                </tr>
                <c:forEach var="customerAddress" items="${customerAddressList}">
                    <tr>
                        <td>${customerAddress.addressLine1}</td>
                        <td>${customerAddress.addressLine2}</td>
                        <td>${customerAddress.city}</td>
                        <td>${customerAddress.stateCode}</td>
                        <td>${customerAddress.postalCode}</td>
                        <form action="/order/enterShipping/bindAddress" method="GET">
                            <input type = "hidden" name="orderID" value="${order.orderID}"/>
                            <input type = "hidden" name="addressID" value="${customerAddress.addressID}"/>
                            <td style="text-align: center"><input type = "submit" value = "Ship to this Address"></td>
                        </form>
                    </tr>
                </c:forEach>
            </table>

            <form:form class="mainForm" method="post" action="/order/enterShipping/submit" modelAttribute="order">
                <form:hidden path="orderID" value="${order.orderID}"/>
                <form:hidden path="shipperID" value="1"/>
                <table class="mainTable" style="margin: auto;">
                    <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">or<br>Enter a new Address</caption>
                    <tr>
                        <td>Name</td>
                        <td>:</td>
                        <td><form:input path="shippingToName" value="${order.shippingToName}" maxlength="100" title="Maximum 100 characters"/></td>
                    </tr>
                    <tr>
                        <td>Address Line 1</td>
                        <td>:</td>
                        <td><form:input path="shippingAddressLine1" value="${order.shippingAddressLine1}" required="true" maxlength="200" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td>Address Line 2</td>
                        <td>:</td>
                        <td><form:input path="shippingAddressLine2" value="${order.shippingAddressLine2}" maxlength="200" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td>:</td>
                        <td><form:input path="shippingCity" value="${order.shippingCity}" required="true" title="Enter city"/></td>
                    </tr>
                    <tr>
                        <td>State</td>
                        <td>:</td>
                        <td>
                            <form:select path="shippingStateCode" required="true">
                                <form:option value="" label="Select"/>
                                <form:options items="${addressStateList}" itemValue="stateCode" itemLabel="stateCodeAndName"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Zip Code</td>
                        <td>:</td>
                        <td><form:input path="shippingPostalCode" value="${order.shippingPostalCode}" required="true" title="Enter zip code"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <c:if test="${order.shippingAddressLine1==null}">
                            <td><form:checkbox cssStyle="width: 50px;" path="saveShippingAddress"/>Save Address for Future Use</td>
                        </c:if>
                    </tr>
                </table>

                <table class="mainTable" style="margin: auto;">
                    <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">Select a Shipping Option</caption>
                    <tr>
                        <td>
                            <form:radiobuttons cssStyle="width: 50px; display: table-cell; vertical-align: middle;" path="shippingSpeedCode" required="true" items="${shippingSpeedList}" itemValue="shippingSpeedCode" itemLabel="shippingSpeedName"/>
                        </td>
                    </tr>
                    <tr>
                        <c:if test="${order.orderStatusCode=='P'}"><td><input type="submit" value="Continue"/></td></c:if>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                </table>
            </form:form>

        </div>

    </body>

</html>
