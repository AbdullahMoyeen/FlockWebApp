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

        <title>Flock Products</title>

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

            <%--<table width="100%" style="margin: auto;">--%>
                <%--<tr>--%>
                    <%--<th><a href="/">Home</a></th>--%>
                    <%--<th>--%>
                        <%--<security:authorize access="! isAuthenticated()">--%>
                            <%--<a href="<c:url value="/signUp" />">Sign Up</a>--%>
                        <%--</security:authorize>--%>
                    <%--</th>--%>
                    <%--<th>--%>
                        <%--<security:authorize access="! isAuthenticated()">--%>
                            <%--<a href="<c:url value="/signIn" />">Sign In</a>--%>
                        <%--</security:authorize>--%>
                    <%--</th>--%>
                    <%--<th><a href="/">About Us</a></th>--%>
                    <%--<th><a href="/">Contact Us</a></th>--%>
                    <%--<th>--%>
                        <%--<security:authorize access="isAuthenticated()">--%>
                            <%--Hello ${sessionScope.customer.firstName}!--%>
                            <%--<c:set var="userID">--%>
                                <%--<security:authentication property="principal.userId" />--%>
                            <%--</c:set>--%>
                            <%--<a href="<c:url value="/customer/viewProfile?customerID=${userID}" />"><br>View Account</a>--%>
                        <%--</security:authorize>--%>
                    <%--</th>--%>
                    <%--<th>--%>
                        <%--<security:authorize access="isAuthenticated()">--%>
                            <%--Not ${sessionScope.customer.firstName}?--%>
                            <%--<a href="<c:url value="/signOut" />"><br>Sign Out</a>--%>
                        <%--</security:authorize>--%>
                    <%--</th>--%>
                    <%--<c:if test="${sessionScope.order.orderLineCount>0}"><th><a href="/shoppingCart/viewCart?orderID=${sessionScope.order.orderID}">View Cart(${sessionScope.order.orderLineCount})</a></th></c:if>--%>
                <%--</tr>--%>
            <%--</table>--%>

        </div>

        <div class="section">

            <table style="margin: auto;">
                <tr>
                    <td>
                        <form action="/product/list" method="GET">
                            <INPUT TYPE = "search" NAME="searchString" STYLE="width: 500px; height: 50px; font-size: x-large; border-radius: 5px; border-width: thick; border-color: cadetblue;">
                            <INPUT TYPE = "Submit" VALUE = "Search" STYLE="width: 100px; height: 30px; font-size: large;">
                        </form>
                    </td>
                </tr>
            </table>

            <table class="childTable" border="1" style="width: 100%;margin: auto;border-spacing:10px;empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">Your Groups</caption>
                <%--<c:forEach var="product" items="${productList}">--%>
                    <%--<tr>--%>
                        <%--<td style="text-align: center"><a href="<c:url value="/product/detail?productID=${product.productID}"/>"><img <c:if test="${product.quantityOnHand < 1}"> style="opacity: 0.4" </c:if> src="${product.imageFileLocation}/${product.imageFileName}"/></a><c:if test="${product.quantityOnHand < 1}"><br>Out of Stock</c:if></td>--%>
                        <%--<td><a href="<c:url value="/product/detail?productID=${product.productID}"/>">${product.productName}</a></td>--%>
                        <%--<td>${product.productDescription}</td>--%>
                        <%--<td style="text-align: right;">$${product.price}</td>--%>
                        <%--<td style="text-align: center"><a href="<c:url value="/product/detail?productID=${product.productID}"/>">View Product Details</a></td>--%>
                        <%--<form action="/shoppingCart/addItem" method="GET">--%>
                            <%--<input type = "hidden" name="productID" value="${product.productID}"/>--%>
                            <%--<td><input type = "number" name="addToCartQuantity" required="true" style="text-align: right" min="1" value="0"></td>--%>
                            <%--<td><input type = "Submit" value = "Add to Cart" <c:if test="${product.quantityOnHand < 1}">disabled="true"</c:if>></td>--%>
                        <%--</form>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
            </table>

        </div>

    </body>

</html>
