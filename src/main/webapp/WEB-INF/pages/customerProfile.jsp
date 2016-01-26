<%--
  Created by IntelliJ IDEA.
  User: amoyeen
  Date: 2/17/2015
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>

    <head>

        <title>Flock Customer Profile</title>

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
                text-align: center;
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
                    <th><a href="/product/list?searchString=">Products</a></th>
                    <security:authorize access="! isAuthenticated()">
                        <th>
                            <a href="<c:url value="/signIn" />">Sign In</a>
                        </th>
                    </security:authorize>
                    <th><a href="/">About Us</a></th>
                    <th><a href="/">Contact Us</a></th>
                    <security:authorize access="isAuthenticated()">
                        <th>
                            Not ${sessionScope.customer.firstName}?
                            <a href="<c:url value="/signOut" />"><br>Sign Out</a>
                        </th>
                    </security:authorize>
                    <c:if test="${sessionScope.order.orderLineCount>0}"><th><a href="/shoppingCart/viewCart?orderID=${sessionScope.order.orderID}">View Cart(${sessionScope.order.orderLineCount})</a></th></c:if>
                </tr>
            </table>

        </div>

        <div class="section">

            <c:url value="/customer/updateProfile" var="formUrl"/>
            <form:form class="mainForm" method="post" action="${formUrl}" modelAttribute="customer">
                <form:hidden path="customerID" value="${customer.customerID}" />
                <table class="mainTable" style="margin: auto;">
                    <caption style="font-size: x-large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">${customer.firstName}'s Profile</caption>
                    <tr>
                        <th>First Name</th>
                        <td>:</td>
                        <td><form:input path="firstName" value="${customer.firstName}"/></td>
                    </tr>
                    <tr>
                        <th>Last Name</th>
                        <td>:</td>
                        <td><form:input path="lastName" value="${customer.lastName}"/></td>
                    </tr>
                    <tr>
                        <th>Email Address</th>
                        <td>:</td>
                        <td style="text-align: left;">${customer.emailAddress}</td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="button" value="Cancel Changes" onClick="location.href='<c:url value="/customer/viewProfile?customerID=${customer.customerID}"/>'" title="Cancel changes to your profile"/></td>
                        <td></td>
                        <td><input type="submit" value="Save Changes" title="Save changes to your profile"/></td>
                    </tr>
                </table>
            </form:form>
            <table class="linkTable" style="margin: auto;">
                <tr>
                    <th><a href="<c:url value="/customer/updatePassword?customerID=${customer.customerID}"/>">Change Password</a></th>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </table>
            <table class="mainTable" style="margin: auto;">
                <tr>
                    <td><input type="button" value="Your Open Orders" style="width: 400px; height: 40px; font-size: large" onClick="location.href='<c:url value="/customer/viewOpenOrders?customerID=${customer.customerID}"/>'" title="View your open orders"/></td>
                    <td></td>
                    <td><input type="button" value="View Order History" style="width: 400px; height: 40px; font-size: large" onClick="location.href='<c:url value="/customer/viewOrderHistory?customerID=${customer.customerID}&daysBefore=30"/>'" title="View order history"/></td>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </table>

            <table class="childTable" border="1" style="width: 100%;margin: auto;border-spacing: 10px;empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">${customer.firstName}'s Addresses</caption>
                <tr>
                    <th>Address Line 1</th>
                    <th>Address Line 2</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip Code</th>
                    <th>Edit</td>
                    <th>Delete</th>
                </tr>
                <c:forEach var="customerAddress" items="${customerAddressList}">
                    <tr>
                        <td>${customerAddress.addressLine1}</td>
                        <td>${customerAddress.addressLine2}</td>
                        <td>${customerAddress.city}</td>
                        <td>${customerAddress.stateCode}</td>
                        <td>${customerAddress.postalCode}</td>
                        <td><a href="<c:url value="/customer/updateAddress?addressID=${customerAddress.addressID}"/>">Edit</a></td>
                        <td><a href="<c:url value="/customer/deleteAddress?addressID=${customerAddress.addressID}&customerID=${customerAddress.customerID}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <table class="linkTable" style="margin: auto;">
                <tr>
                    <th><a href="<c:url value="/customer/addAddress?customerID=${customer.customerID}"/>">Add New Address</a></th>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </table>

            <table class="childTable" border="1" style="width: 100%;margin: auto;border-spacing:10px;empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">${customer.firstName}'s Phones</caption>
                <tr>
                    <th>Phone Type</th>
                    <th>Phone Number</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="customerPhone" items="${customerPhoneList}">
                    <tr>
                        <td>${customerPhone.phoneTypeName}</td>
                        <td>${customerPhone.phoneNumber}</td>
                        <td><a href="<c:url value="/customer/updatePhone?phoneID=${customerPhone.phoneID}"/>">Edit</a></td>
                        <td><a href="<c:url value="/customer/deletePhone?phoneID=${customerPhone.phoneID}&customerID=${customerPhone.customerID}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <table class="linkTable" style="margin: auto;">
                <tr>
                    <th><a href="<c:url value="/customer/addPhone?customerID=${customer.customerID}"/>">Add New Phone</a></th>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </table>

            <table class="childTable" border="1" style="width: 100%;margin: auto;border-spacing:10px;empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">${customer.firstName}'s Payment Cards</caption>
                <tr>
                    <th>Card Type</th>
                    <th>Card Number</th>
                    <th>CVV</th>
                    <th>Expiry</th>
                    <th>Name on Card</th>
                    <th>Card Billing Address</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="customerPaymentCard" items="${customerPaymentCardList}">
                    <tr>
                        <td>${customerPaymentCard.paymentCardTypeName}</td>
                        <td>${customerPaymentCard.paymentCardNumber}</td>
                        <td>${customerPaymentCard.paymentCardCVV}</td>
                        <td>${customerPaymentCard.paymentCardExpiry}</td>
                        <td>${customerPaymentCard.paymentCardHolderName}</td>
                        <td>${customerPaymentCard.paymentCardFullAddress}</td>
                        <td><a href="<c:url value="/customer/updatePaymentCard?paymentCardID=${customerPaymentCard.paymentCardID}"/>">Edit</a></td>
                        <td><a href="<c:url value="/customer/deletePaymentCard?paymentCardID=${customerPaymentCard.paymentCardID}&customerID=${customerPaymentCard.customerID}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <table class="linkTable" style="margin: auto;">
                <tr>
                    <th><a href="<c:url value="/customer/addPaymentCard?customerID=${customer.customerID}"/>">Add New Payment Card</a></th>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </table>

        </div>

    </body>

</html>
