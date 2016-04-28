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

        <title>Flock Sign Up</title>

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
                text-align: left;
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

            <c:if test="${param.userAlreadyExists == 'true'}">
                <div class="error" style="text-align: center">
                    <br>Account already exists for this email<br><br>Please sign up with a different email or Sign In
                </div>
            </c:if>

            <c:if test="${param.InvalidEmail == 'true'}">
                <div class="error" style="text-align: center">
                    <br>Please provide a valid school email
                </div>
            </c:if>

            <c:if test="${param.unknownError == 'true'}">
                <div class="error" style="text-align: center">
                    <br>Account could not be created<br><br>Please try again
                </div>
            </c:if>

            <form:form class="mainForm" method="post" action="/signUp/submit" modelAttribute="user">
                <table class="mainTable" style="margin: auto;">
                    <caption style="font-size: x-large;font-weight: bold;text-decoration: underline;padding: 20px">User Sign Up</caption>
                    <tr>
                        <th>First Name</th>
                        <td>:</td>
                        <td><form:input path="firstName" required="true" maxlength="50" title="Maximum 50 characters"/></td>
                    </tr>
                    <tr>
                        <th>Last Name</th>
                        <td>:</td>
                        <td><form:input path="lastName" required="true" maxlength="50" title="Maximum 50 characters"/></td>
                    </tr>
                    <tr>
                        <th>Email Address</th>
                        <td>:</td>
                        <td><form:input type="email" path="emailAddress" required="true"
                                        title="Enter a valid email address"/></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Clear" title="clear all fields"></td>
                        <td></td>
                        <td><input type="submit" value="Submit" title="create your account"/></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                </table>
            </form:form>
            <table class="linkTable" style="margin: auto;">
                <tr>
                    <th><a href="#" onclick="history.go(-1)" title="go back to previous page">Go Back</a></th>
                </tr>
            </table>

        </div>

    </body>

</html>
