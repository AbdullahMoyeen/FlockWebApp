<%--
  Created by IntelliJ IDEA.
  User: amoyeen
  Date: 2/21/15
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>

<head>

    <title>Flock Password Retrieval</title>

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
            text-align: center;
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
        .error {
            font-weight: bold;
            font-size: x-large;
            color: red;
            text-align: center;
        }
        .info {
            font-weight: bold;
            font-size: x-large;
            color: green;
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
                <th><a href="/">About Us</a></th>
                <th><a href="/">Contact Us</a></th>
                <th>
                    <security:authorize access="isAuthenticated()">
                        Hello ${sessionScope.userFirstName}!
                        <c:set var="userId">
                            <security:authentication property="principal.userId" />
                        </c:set>
                        <a href="<c:url value="/admin/user/viewProfile?userId=${userId}" />"><br>View Account</a>
                    </security:authorize>
                </th>
                <th>
                    <security:authorize access="isAuthenticated()">
                        Not ${sessionScope.userFirstName}?
                        <a href="<c:url value="/signOut" />"><br>Sign Out</a>
                    </security:authorize>
                </th>
            </tr>
        </table>

    </div>

    <div class="section">

        <c:if test="${param.messageCode == 'tempPasswordSent'}">
            <div class="info" style="text-align: center">
                <br>Temporary Password Sent<br>Please Check Your Email
            </div>
        </c:if>

        <table class="linkTable" style="margin: auto;">
            <tr>
                <th></th>
            </tr>
            <tr>
                <c:choose>
                    <c:when test="${param.messageCode == 'tempPasswordSent'}"><th><a href="<c:url value="/"/>">Go to Sign In Page</a></th></c:when>
                    <c:otherwise><th><a href="#" onclick="history.go(-1)" title="go back to previous page">Go Back</a></th></c:otherwise>
                </c:choose>
            </tr>
        </table>

    </div>

</body>

</html>