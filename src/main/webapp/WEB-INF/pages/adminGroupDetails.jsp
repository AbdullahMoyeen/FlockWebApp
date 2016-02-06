<%--
  Created by IntelliJ IDEA.
  User: amoyeen
  Date: 3/1/15
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>

    <head>

        <title>Flock Group Details</title>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            form.mainForm textarea {
                width: 400px;
                height: 100px;
                font-size: large;
                font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif;
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
                            Hello<br>${sessionScope.userFirstName}!
                            <%--<c:set var="userID">--%>
                            <%--<security:authentication property="principal.userId" />--%>
                            <%--</c:set>--%>
                            <%--<a href="<c:url value="/customer/viewProfile?customerID=${userID}" />"><br>View Account</a>--%>
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

            <c:url value="/admin/group/update" var="formUrl"/>
            <form:form class="mainForm" method="post" action="${formUrl}" modelAttribute="adminGroupDetails">
                <form:hidden path="groupId" value="${adminGroupDetails.groupId}" />
                <table class="mainTable" style="margin: auto;">
                    <caption style="font-size: x-large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">${adminGroupDetails.groupName}<br>Group Details</caption>
                    <tr>
                        <th>Group Name</th>
                        <td>:</td>
                        <td><form:input path="groupName" value="${adminGroupDetails.groupName}"/></td>
                    </tr>
                    <tr>
                        <th>Group Description</th>
                        <td>:</td>
                        <td><form:textarea path="groupDescription" value="${adminGroupDetails.groupDescription}"/></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Cancel Changes" title="Cancel changes to the group"/></td>
                        <td></td>
                        <td><input type="submit" value="Save Changes" title="Save changes to the group"/></td>
                    </tr>
                </table>
            </form:form>

            <table class="linkTable" style="margin: auto;">
                <tr>
                    <th><a href="#" onclick="history.go(-1)" title="go back to group list">Go Back to List</a></th>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </table>
            <table style=" margin: auto;">
                    <tr>
                        <td><input type="button" align="center " value="Add Event Details" style=" width: 1000px; height: 40px; font-size: medium" onClick="location.href='<c:url value="/admin/group/event/details?eventID=0&groupID=${adminGroupDetails.groupId}"/>'" title="go to event details"></td>
                    </tr>
                </table>

            <table class="childTable" border="1" style="width: 100%;margin: auto;border-spacing: 10px;empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">Group Events</caption>
                <tr>
                    <th>Private</th>
                    <th>Event Name</th>
                    <th>Event Description</th>
                    <th>Event Address</th>
                    <th>Event Start Date</th>
                    <th>Event End Date</th>
                    <th></th>
                </tr>
                <c:forEach var="adminGroupEvent" items="${adminGroupEvents}">
                <tr>
                    <td><a style="text-decoration: none" href="<c:url value="/admin/group/event/details?eventID=${adminGroupEvent.eventID}&groupID=${adminGroupEvent.groupID}"/>"><c:if test="${adminGroupEvent.privateEventInd =='Y'}">Private</c:if></a></td>
                    <%--<td><a style="text-decoration: none" href="<c:url value="/admin/group/event/details?eventID=${adminGroupEvent.eventID}&groupID=${adminGroupEvent.groupID}"/>">${adminGroupEvent.privateEventInd}</a></td>--%>
                    <td><a style="text-decoration: none" href="<c:url value="/admin/group/event/details?eventID=${adminGroupEvent.eventID}&groupID=${adminGroupEvent.groupID}"/>">${adminGroupEvent.eventName}</a></td>
                    <td><a style="text-decoration: none" href="<c:url value="/admin/group/event/details?eventID=${adminGroupEvent.eventID}&groupID=${adminGroupEvent.groupID}"/>">${adminGroupEvent.eventDescription}</a></td>
                    <td><a style="text-decoration: none" href="<c:url value="/admin/group/event/details?eventID=${adminGroupEvent.eventID}&groupID=${adminGroupEvent.groupID}"/>">${adminGroupEvent.eventAddressLine1} ${adminGroupEvent.eventAddressLine2}, ${adminGroupEvent.eventCity}, ${adminGroupEvent.eventStateCode}, ${adminGroupEvent.eventPostalCode}</a></td>
                    <td><a style="text-decoration: none" href="<c:url value="/admin/group/event/details?eventID=${adminGroupEvent.eventID}&groupID=${adminGroupEvent.groupID}"/>"><fmt:formatDate type="date" pattern="MM/dd/yyyy hh:mm a" value="${adminGroupEvent.eventStartDatetime}"/></a></td>
                    <td><a style="text-decoration: none" href="<c:url value="/admin/group/event/details?eventID=${adminGroupEvent.eventID}&groupID=${adminGroupEvent.groupID}"/>"><fmt:formatDate type="date" pattern="MM/dd/yyyy hh:mm a" value="${adminGroupEvent.eventEndDatetime}"/></a></td>
                    <td style="text-align: center"><input type="button" value="Event Details" style="width: 150px; height: 40px; font-size: large" onClick="location.href='<c:url value="/admin/group/event/details?eventID=${adminGroupEvent.eventID}&groupID=${adminGroupEvent.groupID}"/>'" title="go to event details"></td>
                   </c:forEach>
            </table>

            <table class="childTable" border="1" style="width: 100%;margin: auto;border-spacing: 10px;empty-cells: show;">
                <caption style="font-size: large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">Group Members</caption>
                <tr>
                    <th>Email Address</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Group Membership Status</th>
                    <c:if test="${adminGroupDetails.pendingMemberCount > 0}"><th></th></c:if>
                    <c:if test="${adminGroupDetails.pendingMemberCount > 0}"><th></th></c:if>
                </tr>
                <c:forEach var="adminGroupUser" items="${adminGroupUsers}">
                    <tr>
                        <td>${adminGroupUser.emailAddress}</td>
                        <td>${adminGroupUser.firstName}</td>
                        <td>${adminGroupUser.lastName}</td>
                        <td>${adminGroupUser.groupMembershipStatus}</td>
                        <c:if test="${adminGroupDetails.pendingMemberCount > 0}"><td style="text-align: center"><c:if test="${adminGroupUser.groupMembershipStatus == 'P'}"><input type="button" value="Approve" style="width: 150px; height: 40px; font-size: large" onClick="location.href='<c:url value="/admin/group/membership/approve?groupId=${adminGroupUser.groupId}&userId=${adminGroupUser.userId}"/>'" title="approve group membership"></c:if></td></c:if>
                        <c:if test="${adminGroupDetails.pendingMemberCount > 0}"><td style="text-align: center"><c:if test="${adminGroupUser.groupMembershipStatus == 'P'}"><input type="button" value="Deny" style="width: 150px; height: 40px; font-size: large" onClick="location.href='<c:url value="/admin/group/membership/deny?groupId=${adminGroupUser.groupId}&userId=${adminGroupUser.userId}"/>'" title="deny group membership"></c:if></td></c:if>
                </c:forEach>
            </table>

        </div>

    </body>

</html>