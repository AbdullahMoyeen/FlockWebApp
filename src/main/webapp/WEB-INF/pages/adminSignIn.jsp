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

        </div>

        <div class="section">

            <c:if test="${param.loginError == 'true'}">
                <div class="error" style="text-align: center">
                    <br>Login Failed<br>Please Try Again<br>or<br>Click "Forgot Password" to Restore Access
                </div>
            </c:if>

            <c:if test="${param.passwordReset == 'true'}">
                <div class="info" style="text-align: center">
                    <br>Password Reset Successful<br>Please Sign In
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
                    <th style="text-align: center" title="Click to reset your password"><a href="<c:url value="/signIn/retrieveSecurity"/>">Forgot Password</a></th>
                </tr>
                <tr>
                    <th style="text-align: center" title="Click to create an account">New to Flock? First <a href="/signUp">Create an Account</a>.</th>
                </tr>
            </table>

        </div>

    </body>
</html>