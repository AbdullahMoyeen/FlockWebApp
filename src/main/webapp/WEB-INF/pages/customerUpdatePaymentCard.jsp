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

        <title>Flock Update Payment Card</title>

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

            <form:form class="mainForm" method="post" action="/customer/updatePaymentCard/submit" modelAttribute="customerPaymentCard">
                <form:hidden path="customerID" value="${customerPaymentCard.customerID}"/>
                <form:hidden path="paymentCardID" value="${customerPaymentCard.paymentCardID}"/>
                <form:hidden path="displayInd" value="${customerPaymentCard.displayInd}"/>
                <table class="mainTable" style="margin: auto;">
                    <tr>
                        <td>Card Type</td>
                        <td>:</td>
                        <td>
                            <form:select path="paymentCardTypeID" required="true">
                                <form:option value="${customerPaymentCard.paymentCardTypeID}" label="${customerPaymentCard.paymentCardTypeName}"/>
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
                        <td><form:input path="paymentCardExpiry" value="${customerPaymentCard.paymentCardExpiry}" required="true" autocomplete="off" title="Enter 2 digits month and 4 digit year separated by /"/></td>
                    </tr>
                    <tr>
                        <td>Name on Card</td>
                        <td>:</td>
                        <td><form:input path="paymentCardHolderName" value="${customerPaymentCard.paymentCardHolderName}" required="true" title="Maximum 100 characters"/></td>
                    </tr>
                    <tr>
                        <td>Billing Address Line 1</td>
                        <td>:</td>
                        <td><form:input path="paymentCardAddressLine1" value="${customerPaymentCard.paymentCardAddressLine1}" required="true" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td>Billing Address Line 2</td>
                        <td>:</td>
                        <td><form:input path="paymentCardAddressLine2" value="${customerPaymentCard.paymentCardAddressLine2}" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td>Billing Address City</td>
                        <td>:</td>
                        <td><form:input path="paymentCardCity" value="${customerPaymentCard.paymentCardCity}" required="true" title="Maximum 100 characters"/></td>
                    </tr>
                    <tr>
                        <td>Billing Address State</td>
                        <td>:</td>
                        <td>
                            <form:select path="paymentCardStateCode" required="true">
                            <form:option value="${customerPaymentCard.paymentCardStateCode}" label="${customerPaymentCard.paymentCardStateCode}"/>
                            <form:options items="${addressStateList}" itemValue="stateCode" itemLabel="stateCodeAndName"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Billing Address Zip Code</td>
                        <td>:</td>
                        <td><form:input path="paymentCardPostalCode" value="${customerPaymentCard.paymentCardPostalCode}" required="true" /></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="button" value="Cancel" onclick="location.href='/customer/viewProfile?customerID=${customerPaymentCard.customerID}'" title="Cancel and go back to your profile"></td>
                        <td></td>
                        <td><input type="submit" value="Save" title="Update payment card"/></td>
                    </tr>
                </table>
            </form:form>

        </div>

    </body>

</html>
