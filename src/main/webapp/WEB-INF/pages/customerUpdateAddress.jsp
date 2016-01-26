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

        <title>Flock Address Change</title>

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

            <form:form class="mainForm" method="post" action="/customer/updateAddress/submit" modelAttribute="customerAddress">
                <form:hidden path="customerID" value="${customerAddress.customerID}" />
                <form:hidden path="addressID" value="${customerAddress.addressID}" />
                <table class="mainTable" style="margin: auto;">
                    <tr>
                        <td>Address Line 1</td>
                        <td>:</td>
                        <td><form:input path="addressLine1" value="${customerAddress.addressLine1}" required="true" maxlength="200" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td>Address Line 2</td>
                        <td>:</td>
                        <td><form:input path="addressLine2" value="${customerAddress.addressLine2}" maxlength="200" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td>:</td>
                        <td><form:input path="city" value="${customerAddress.city}" required="true" title="Enter city"/></td>
                    </tr>
                    <tr>
                        <td>State</td>
                        <td>:</td>
                        <td>
                            <form:select path="stateCode" required="true">
                                <form:option value="${customerAddress.stateCode}" label="${customerAddress.stateCode}"/>
                                <form:options items="${addressStateList}" itemValue="stateCode" itemLabel="stateCodeAndName"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Zip Code</td>
                        <td>:</td>
                        <td><form:input path="postalCode" value="${customerAddress.postalCode}" required="true" title="Enter zip code"/></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="button" value="Cancel" onclick="location.href='/customer/viewProfile?customerID=${customerAddress.customerID}'" title="Cancel and go back to your profile"></td>
                        <td></td>
                        <td><input type="submit" value="Save" title="Update address"/></td>
                    </tr>
                </table>
            </form:form>

        </div>

    </body>

</html>
