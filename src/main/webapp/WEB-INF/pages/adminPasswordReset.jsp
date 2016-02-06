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
        </style>

    </head>

    <body>

        <div class="header">

            <h1>Flock</h1>

        </div>

        <div class="nav">

            <table width="100%" style="margin: auto;">

            </table>

        </div>

        <div class="section">

            <c:if test="${param.emailAddressExists == 'false'}">
                <div class="error" style="text-align: center">
                    <br>Account not Found<br>Please Try Again
                </div>
            </c:if>

            <form class="mainForm" method="get" action="/requestTempPassword">
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