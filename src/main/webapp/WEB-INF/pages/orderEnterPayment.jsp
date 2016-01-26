<%--
  Created by IntelliJ IDEA.
  User: amoyeen
  Date: 2/28/15
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>

    <head>

        <title>Flock Enter Payment</title>

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

            <table class="childTable" border="1" style="width: 100%;margin: auto;border-spacing:10px;empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">Select a Payment Card</caption>
                <tr>
                    <th>Card Type</th>
                    <th>Card Number</th>
                    <th>CVV</th>
                    <th>Expiry</th>
                    <th>Name on Card</th>
                    <th>Card Billing Address</th>
                    <th style="text-align: center">Select</td>
                </tr>
                <c:forEach var="customerPaymentCard" items="${customerPaymentCardList}">
                    <tr>
                        <td>${customerPaymentCard.paymentCardTypeName}</td>
                        <td>${customerPaymentCard.paymentCardNumber}</td>
                        <td>${customerPaymentCard.paymentCardCVV}</td>
                        <td>${customerPaymentCard.paymentCardExpiry}</td>
                        <td>${customerPaymentCard.paymentCardHolderName}</td>
                        <td>${customerPaymentCard.paymentCardFullAddress}</td>
                        <form action="/order/enterPayment/bindPaymentCard" method="GET">
                            <input type = "hidden" name="orderID" value="${order.orderID}"/>
                            <input type = "hidden" name="paymentCardID" value="${customerPaymentCard.paymentCardID}"/>
                            <td style="text-align: center"><input type = "submit" value = "Pay with this Card"></td>
                        </form>
                    </tr>
                </c:forEach>
            </table>

            <form:form class="mainForm" method="post" action="/order/enterPayment/submit" modelAttribute="order">
                <form:hidden path="orderID" value="${order.orderID}"/>
                <form:hidden path="paymentCardID" value="${order.paymentCardID}"/>
                <table class="mainTable" style="margin: auto;">
                    <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">or<br>Enter a new Card</caption>
                    <tr>
                        <td>Card Type</td>
                        <td>:</td>
                        <td>
                            <form:select path="paymentCardTypeID" required="true">
                                <form:option value="" label="Select"/>
                                <form:options items="${paymentCardTypeList}" itemValue="paymentCardTypeID" itemLabel="paymentCardTypeName"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Card Number</td>
                        <td>:</td>
                        <td><form:input path="paymentCardNumber" required="true" autocomplete="off" maxlength="20" title="Maximum 20 characters"/></td>
                    </tr>
                    <tr>
                        <td>Card CVV</td>
                        <td>:</td>
                        <td><form:input type="password" path="paymentCardCVV" required="true" maxlength="4" title="Maximum 4 characters"/></td>
                    </tr>
                    <tr>
                        <td>Card Expiry (MM/YYYY)</td>
                        <td>:</td>
                        <td><form:input path="paymentCardExpiry" required="true" autocomplete="off" title="Enter 2 digits month and 4 digit year separated by /"/></td>
                    </tr>
                    <tr>
                        <td>Name on Card</td>
                        <td>:</td>
                        <td><form:input path="paymentCardHolderName" required="true" title="Maximum 100 characters"/></td>
                    </tr>
                    <tr>
                        <td>Billing Address Line 1</td>
                        <td>:</td>
                        <td><form:input path="paymentCardAddressLine1" required="true" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td>Billing Address Line 2</td>
                        <td>:</td>
                        <td><form:input path="paymentCardAddressLine2" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td>Billing Address City</td>
                        <td>:</td>
                        <td><form:input path="paymentCardCity" required="true" title="Maximum 100 characters"/></td>
                    </tr>
                    <tr>
                        <td>Billing Address State</td>
                        <td>:</td>
                        <td>
                            <form:select path="paymentCardStateCode" required="true">
                            <form:option value="" label="Select"/>
                            <form:options items="${addressStateList}" itemValue="stateCode" itemLabel="stateCodeAndName"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Billing Address Zip Code</td>
                        <td>:</td>
                        <td><form:input path="paymentCardPostalCode" required="true" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <c:if test="${order.paymentCardDisplayInd != 'Y'}">
                            <td><form:checkbox cssStyle="width: 50px;" path="savePaymentCard" value="1"/>Save Card for Future Use</td>
                        </c:if>
                    </tr>
                </table>

                <table class="mainTable" style="margin: auto;">
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
