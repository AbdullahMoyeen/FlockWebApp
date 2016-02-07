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
<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
<html>

<head>

    <title>Flock </title>

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
                <th><security:authorize access="isAuthenticated()"><a href="/aboutUs">About Us</a></security:authorize></th>
                <th><security:authorize access="isAuthenticated()"><a href="/contactUs">Contact Us</a></security:authorize></th>
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
                        <a href="<c:url value="/admin/groups?userId=${sessionScope.userId}" />">View Groups</a>
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
                <br>Temporary password sent<br><br>Please check your email
            </div>
        </c:if>

        <c:if test="${param.messageCode == 'tempChangeSuccess'}">
            <div class="info" style="text-align: center">
                <br>Password change successful<br><br>Please sign in
            </div>
        </c:if>

        <c:if test="${param.messageCode == 'passwordChangeSuccess'}">
            <div class="info" style="text-align: center">
                <br>Password change successful
            </div>
        </c:if>

        <table class="linkTable" style="margin: auto;">
            <tr>
                <td></td>
            </tr>
            <tr>
                <c:choose>
                    <c:when test="${param.messageCode == 'tempPasswordSent'}"><th><a href="<c:url value="/"/>">Go to Sign In Page</a></th></c:when>
                    <c:when test="${param.messageCode == 'tempChangeSuccess'}"><th><a href="<c:url value="/"/>">Go to Sign In Page</a></th></c:when>
                    <c:when test="${param.messageCode == 'passwordChangeSuccess'}"><th><a href="<c:url value="/admin/groups?userId=${sessionScope.userId}"/>">Go to Your Groups</a></th></c:when>
                    <c:otherwise><th><a href="#" onclick="history.go(-1)" title="go back to previous page">Go Back</a></th></c:otherwise>
                </c:choose>
            </tr>
        </table>

    </div>

</body>

</html>