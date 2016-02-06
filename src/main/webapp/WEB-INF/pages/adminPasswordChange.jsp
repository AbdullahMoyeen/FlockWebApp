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

        <title>Flock Change Password</title>

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

            <%--<c:url value="/signIn/resetPassword/submit" var="formUrl"/>--%>
            <%--<form:form class="mainForm" method="post" action="${formUrl}" modelAttribute="customer">--%>
                <%--<form:hidden path="customerID" value="${passwordSecurity.customerID}"/>--%>
                <%--<table class="mainTable" style="margin: auto;">--%>
                    <%--<caption style="font-size: x-large;font-weight: bold;text-decoration: underline;padding: 20px">Change Password</caption>--%>
                    <%--<tr>--%>
                        <%--<th>Current Password</th>--%>
                        <%--<td>:</td>--%>
                        <%--<td><form:input type="password" path="password" required="true" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}" title="Must be between 8 and 16 characters and contain at least one number, one uppercase and one lowercase letters"/></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<th>New Password</th>--%>
                        <%--<td>:</td>--%>
                        <%--<td><form:input type="password" path="" required="true" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}" title="Must be between 8 and 16 characters and contain at least one number, one uppercase and one lowercase letters"/></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<th>Re-enter New Password</th>--%>
                        <%--<td>:</td>--%>
                        <%--<td><form:input type="password" path="reEnteredPassword" required="true" title="Must match the entered password"/></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td><input type="button" value="Cancel" onclick="location.href='<c:url value="/"/>'" title="Cancel and go back to home"></td>--%>
                        <%--<td></td>--%>
                        <%--<td><input type="submit" value="Save" title="Reset your password"/></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td></td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</form:form>--%>

        </div>

        <script type="text/javascript">

            var password = document.getElementById("password")
                    , reEnteredPassword = document.getElementById("reEnteredPassword");

            function validatePassword() {
                console.log('validating ' + password.value + "  " + reEnteredPassword.value);
                if (password.value != reEnteredPassword.value) {
                    reEnteredPassword.setCustomValidity("Passwords do not match");
                } else {
                    reEnteredPassword.setCustomValidity('');
                }
            }

            password.onchange = validatePassword;
            reEnteredPassword.onkeyup = validatePassword;

        </script>

    </body>

</html>
