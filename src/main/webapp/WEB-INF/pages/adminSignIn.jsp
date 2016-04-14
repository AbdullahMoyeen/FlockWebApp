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

        <title>Flock Sign In</title>

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

            <c:if test="${param.loginError == 'true'}">
                <div class="error" style="text-align: center">
                    <br>Login failed, please try again<br>or<br>Click "Forgot Password" to restore access
                </div>
            </c:if>

            <c:if test="${param.passwordReset == 'true'}">
                <div class="info" style="text-align: center">
                    <br>Password reset successful<br><br>Please sign in
                </div>
            </c:if>

            <form class="mainForm" method="POST" action="<c:url value='/j_spring_security_check' />">
                <table class="mainTable" style="margin: auto;">
                    <caption style="font-size: x-large;font-weight: bold;text-decoration: underline;padding: 20px">Sign In</caption>
                    <tr>
                        <th><label for="username">Email Address</label></th>
                        <td>:</td>
                        <td><input type="text" name="j_username" id="username"></td>
                    </tr>

                    <tr>
                        <th><label for="password">Password</label></th>
                        <td>:</td>
                        <td><input type="password" name="j_password" id="password"></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Clear" title="clear form"/></td>
                        <td></td>
                        <td><input type="submit" value="Sign In" title="sign in to your account"/></td>
                    </tr>
                </table>
            </form>
            <table class="linkTable" style="margin: auto;">
                <tr>
                    <th style="text-align: center" title="Click to reset your password"><a href="<c:url value="/passwordReset"/>">Forgot My Password</a></th>
                </tr>
                <tr>
                    <th style="text-align: center" title="Click to create an account">New to Flock? <a href="/signUp">Create an Account</a>.</th>
                </tr>
            </table>

        </div>

    </body>
</html>