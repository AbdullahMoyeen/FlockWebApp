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

        <title>Flock Change Password</title>

        <style>
            .header {
                background-color:#263238;
                color:white;
                width:100%;
                height:80px;
                padding:10px;
                text-align:center;
            }
            .nav {
                background-color:#0f9d58;
                width:100%;
                height:40px;
                padding:10px;
                float:top;
            }
            .navLink {
                color:#FFFFFF;
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
            .error {
                font-weight: bold;
                font-size: x-large;
                color: red;
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
                    <th><security:authorize access="isAuthenticated()"><a class="navLink" href="/aboutUs">About Us</a></security:authorize></th>
                    <th><security:authorize access="isAuthenticated()"><a class="navLink" href="/contactUs">Contact Us</a></security:authorize></th>
                    <th>
                        <security:authorize access="isAuthenticated()">
                            Hello ${sessionScope.userFirstName}!
                            <c:set var="userId">
                                <security:authentication property="principal.userId" />
                            </c:set>
                            <a class="navLink" href="<c:url value="/admin/user/viewProfile?userId=${userId}" />"><br>View Account</a>
                        </security:authorize>
                    </th>
                    <th>
                        <security:authorize access="isAuthenticated()">
                            <a class="navLink" href="<c:url value="/admin/groups?userId=${sessionScope.userId}" />">View Groups</a>
                        </security:authorize>
                    </th>
                    <th>
                        <security:authorize access="isAuthenticated()">
                            Not ${sessionScope.userFirstName}?
                            <a class="navLink" href="<c:url value="/signOut" />"><br>Sign Out</a>
                        </security:authorize>
                    </th>
                </tr>
            </table>

        </div>

        <div class="section">

            <c:if test="${param.currentPasswordInvalid == 'true'}">
                <div class="error" style="text-align: center">
                    <br>Current password invalid<br><br>Please try again
                </div>
            </c:if>

            <c:if test="${param.unknownError == 'true'}">
                <div class="error" style="text-align: center">
                    <br>Password change failed<br><br>Please try again
                </div>
            </c:if>

            <c:url value="/admin/user/changePassword" var="formUrl"/>
            <form:form class="mainForm" method="post" action="${formUrl}" modelAttribute="userPassword">
                <form:hidden path="userId" value="${sessionScope.userId}"/>
                <form:hidden path="emailAddress" value="${sessionScope.userName}"/>
                <table class="mainTable" style="margin: auto;">
                    <caption style="font-size: x-large;font-weight: bold;text-decoration: underline;padding: 20px;">Change Password</caption>
                    <tr>
                        <th>Current Password</th>
                        <td>:</td>
                        <td><form:input id="currentPassword" type="password" path="password" required="true" title="enter current password"/></td>
                    </tr>
                    <tr>
                        <th>New Password</th>
                        <td>:</td>
                        <td><form:input id="newPassword" type="password" path="newPassword" required="true" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}" title="Must be between 8 and 16 characters and contain at least one number, one uppercase and one lowercase letters"/></td>
                    </tr>
                    <tr>
                        <th>Re-enter New Password</th>
                        <td>:</td>
                        <td><form:input id="reEnteredPassword" type="password" path="reEnteredPassword" required="true" title="must match the entered password"/></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Clear" title="clear all fields"></td>
                        <td></td>
                        <td><input type="submit" value="Change Password" title="change your password"/></td>
                    </tr>
                </table>
            </form:form>
            <table class="linkTable" style="margin: auto;">
                <tr>
                    <th><a href="#" onclick="history.go(-1)" title="go back to previous page">Go Back</a></th>
                </tr>
            </table>

        </div>

        <script type="text/javascript">

            var currentPassword = document.getElementById("currentPassword")
                    , newPassword = document.getElementById("newPassword")
                    , reEnteredPassword = document.getElementById("reEnteredPassword");

            function validatePassword() {
                console.log('validating ' + newPassword.value + "  " + reEnteredPassword.value);
                if (newPassword.value != reEnteredPassword.value) {
                    reEnteredPassword.setCustomValidity("passwords do not match");
                } else {
                    reEnteredPassword.setCustomValidity('');
                }
            }

            newPassword.onchange = validatePassword;
            reEnteredPassword.onkeyup = validatePassword;

        </script>

    </body>

</html>
