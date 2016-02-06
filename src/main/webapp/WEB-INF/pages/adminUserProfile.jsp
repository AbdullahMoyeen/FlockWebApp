<%--
  Created by IntelliJ IDEA.
  User: amoyeen
  Date: 2/17/2015
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>

    <head>

        <title>Flock Customer Profile</title>

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
                text-align: center;
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
                            <br>View Account
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

            <c:url value="/admin/user/updateProfile" var="formUrl"/>
            <form:form class="mainForm" method="post" action="${formUrl}" modelAttribute="user">
                <form:hidden path="userId" value="${user.userId}" />
                <table class="mainTable" style="margin: auto;">
                    <caption style="font-size: x-large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">${user.firstName}'s Profile</caption>
                    <tr>
                        <th>First Name</th>
                        <td>:</td>
                        <td><form:input path="firstName" value="${user.firstName}"/></td>
                    </tr>
                    <tr>
                        <th>Last Name</th>
                        <td>:</td>
                        <td><form:input path="lastName" value="${user.lastName}"/></td>
                    </tr>
                    <tr>
                        <th>Email Address</th>
                        <td>:</td>
                        <td style="text-align: left;">${user.emailAddress}</td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Cancel Changes" title="Cancel changes to your profile"/></td>
                        <td></td>
                        <td><input type="submit" value="Save Changes" title="Save changes to your profile"/></td>
                    </tr>
                </table>
            </form:form>
            <table class="linkTable" style="margin: auto;">
                <tr>
                    <th><a href="<c:url value="/admin/user/changePassword?userId=${user.userId}"/>">Change Password</a></th>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <th><a href="#" onclick="history.go(-1)" title="go back to previous page">Go Back</a></th>
                </tr>
            </table>

        </div>

    </body>

</html>
