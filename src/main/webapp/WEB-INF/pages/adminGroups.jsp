<%--
  Created by IntelliJ IDEA.
  User: amoyeen
  Date: 3/1/15
  Time: 12:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
<html>

    <head>

        <title>Flock Groups</title>

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
            table.childTable th {
                font-size: 20px;
                color: black;
                text-align: center;
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
                            View Groups
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

            <table class="childTable" border="1" style="width: 100%;margin: auto;border-spacing:10px;empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">Your Groups</caption>
                <tr>
                    <th>Group Name</th>
                    <th>Description</th>
                    <th>Active Members</th>
                    <th>Pending Members</th>
                    <th>Upcoming Events</th>
                    <th></th>
                </tr>
                <c:forEach var="adminGroup" items="${adminGroups}">
                    <tr>
                        <td><a style="text-decoration: none" href="<c:url value="/admin/group/details?groupId=${adminGroup.groupId}"/>">${adminGroup.groupName}</a></td>
                        <td><a style="text-decoration: none" href="<c:url value="/admin/group/details?groupId=${adminGroup.groupId}"/>">${adminGroup.groupDescription}</a></td>
                        <td style="text-align: center"><a style="text-decoration: none" href="<c:url value="/admin/group/details?groupId=${adminGroup.groupId}"/>">${adminGroup.activeMemberCount}</a></td>
                        <td style="text-align: center"><a style="text-decoration: none" href="<c:url value="/admin/group/details?groupId=${adminGroup.groupId}"/>">${adminGroup.pendingMemberCount}</a></td>
                        <td style="text-align: center"><a style="text-decoration: none" href="<c:url value="/admin/group/details?groupId=${adminGroup.groupId}"/>">${adminGroup.upcomingEventCount}</a></td>
                        <td style="text-align: center"><input type="button" value="Manage Group" style="width: 150px; height: 40px; font-size: large" onClick="location.href='<c:url value="/admin/group/details?groupId=${adminGroup.groupId}"/>'" title="go to group details"></td>
                    </tr>
                </c:forEach>
            </table>

        </div>

    </body>

</html>