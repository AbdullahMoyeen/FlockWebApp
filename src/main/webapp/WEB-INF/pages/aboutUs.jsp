<%--
  Created by IntelliJ IDEA.
  User: AbdullahMoyeen
  Date: 2/6/16
  Time: 6:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
<html>

    <head>

        <title>About Flock</title>

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
                padding:10px;
                float:top;
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
                    <th><security:authorize access="isAuthenticated()">About Us</security:authorize></th>
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

        <div class="section" style="text-align: center; font-size: x-large">

            <p>
                This site is a production of Team 7up.
            </p>

        </div>

    </body>

</html>
