<%--
  Created by IntelliJ IDEA.
  User: AbdullahMoyeen
  Date: 2/1/16
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

        <title>Flock Password Reset</title>

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

            <c:if test="${param.userNotFound == 'true'}">
                <div class="error" style="text-align: center">
                    <br>Account not found<br><br>Please try again
                </div>
            </c:if>

            <c:if test="${param.unknownError == 'true'}">
                <div class="error" style="text-align: center">
                    <br>Password could not be Reset<br><br>Please try again
                </div>
            </c:if>

            <form class="mainForm" method="get" action="/resetPassword">
                <table class="mainTable" style="margin: auto;">
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <th>Enter Email Address to Continue</th>
                    </tr>
                    <tr>
                        <td><input type="email" name="emailAddress" required="true" title="enter your account email address" /></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Continue" title="continue to receive a temporary password" /></td>
                    </tr>
                </table>
            </form>

            <table class="linkTable" style="margin: auto;">
                <tr>
                    <th><a href="#" onclick="history.go(-1)" title="go back to previous page">Go Back</a></th>
                </tr>
            </table>

        </div>

    </body>
</html>