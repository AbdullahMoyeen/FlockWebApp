<%--
  Created by IntelliJ IDEA.
  User: mPartovi
  Date: 1/29/16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <c:choose>
        <c:when test="${empty event}">
            <title>New Event</title>
        </c:when>
        <c:otherwise>
            <title>View Event</title>
        </c:otherwise>
    </c:choose>

    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
    href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">

    <script type="text/javascript"
            src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
    </script>
    <script type="text/javascript"
            src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
    </script>
    <script type="text/javascript"
            src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
    </script>
    <script type="text/javascript"
            src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
    </script>


    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>

    <script type="text/javascript">

        var geocoder = new google.maps.Geocoder();
        var address = "6314 SHADY BROOK LANE DALLAS TX 75206";

        geocoder.geocode( { 'address': address}, function(results, status) {

            if (status == google.maps.GeocoderStatus.OK) {
                var latitude = results[0].geometry.location.lat();
                var longitude = results[0].geometry.location.lng();
                alert(latitude);
                alert(longitude);
            }
        });
    </script>

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
            font-size: 20px;
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
                    <th><a href="/">Home</a></th>
                    <th><a href="/product/list?searchString=">Products</a></th>
                    <th>
                        <security:authorize access="! isAuthenticated()">
                            <a href="<c:url value="/signIn" />">Sign In</a>
                        </security:authorize>
                    </th>
                    <th><a href="/">About Us</a></th>
                    <th><a href="/">Contact Us</a></th>
                    <th>
                        <security:authorize access="isAuthenticated()">
                            Hello ${sessionScope.customer.firstName}!
                            <c:set var="userID">
                                <security:authentication property="principal.userId" />
                            </c:set>
                            <a href="<c:url value="/customer/viewProfile?customerID=${userID}" />"><br>View Account</a>
                        </security:authorize>
                    </th>
                    <th>
                        <security:authorize access="isAuthenticated()">
                            Not ${sessionScope.customer.firstName}?
                            <a href="<c:url value="/signOut" />"><br>Sign Out</a>
                        </security:authorize>
                    </th>
                    <c:if test="${sessionScope.order.orderLineCount>0}"><th><a href="/shoppingCart/viewCart?orderID=${sessionScope.order.orderID}">View Cart(${sessionScope.order.orderLineCount})</a></th></c:if>
                </tr>
            </table>

        </div>
        <div class="section">
            <form:form class="mainForm" method="post" action="/groupEventDetail/submit" modelAttribute="event">
                <form:hidden path="eventID" value="${event.eventID}"/>
                <form:hidden path="groupID" value="${event.groupID}"/>

                <form:hidden path="createUser" value="${event.createUser}"/>
<%--
                <form:hidden path="createDate" value="${event.createDate}"/>
--%>
                <form:hidden path="updateUser" value="${event.updateUser}"/>
<%--
                <form:hidden path="updateDate" value="${event.updateDate}"/>
--%>

                <legend>Event</legend>
                <table class="mainTable" style="margin: auto;">
                    <tr>
                        <td>Event Name</td>
                        <td>:</td>
                        <td><form:input path="eventName" value="${event.eventName}" required="true" autocomplete="off" maxlength="20" title="Maximum 20 characters"/></td>
                    </tr>
                    <tr>
                        <td>Event Description</td>
                        <td>:</td>
                        <td><form:input path="eventDescription" value="${event.eventDescription}" required="true" maxlength="4" title="Maximum 4 characters"/></td>
                    </tr>
                    <tr>
                        <td>Event Start</td>
                        <td>:</td>
                        <td>
                            <div id="datetimepicker2" class="input-append date">
                                <form:input path="eventStartDatetime" value="${event.eventStartDatetime}"  autocomplete="off"/>
                          <span class="add-on">
                            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                          </span>
                                <script type="text/javascript">
                                    $('#datetimepicker2').datetimepicker({
                                        format: 'dd/MM/yyyy HH:mm PP',
                                        language: 'en',
                                        pick12HourFormat: true
                                    });
                                </script>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Event End</td>
                        <td>:</td>
                        <td>
                            <div id="datetimepicker3" class="input-append date">
                                <form:input path="eventEndDatetime" value="${event.eventEndDatetime}"  autocomplete="off"/>
                          <span class="add-on">
                            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                          </span>
                                <script type="text/javascript">
                                    $('#datetimepicker3').datetimepicker({
                                        format: 'dd/MM/yyyy HH:mm PP',
                                        language: 'en',
                                        pick12HourFormat: true
                                    });
                                </script>
                            </div>
                        </td>
                    </tr>

<%--
                    <tr>
                        <td>EventModel Start</td>
                        <td>:</td>
                        <td><form:input path="eventStartDatetime" value="${event.eventStartDatetime}"  autocomplete="off"/></td> &lt;%&ndash;required="true"&ndash;%&gt;
                    </tr>
                    <tr>
                        <td>EventModel End</td>
                        <td>:</td>
                        <td><form:input path="eventEndDatetime" value="${event.eventEndDatetime}"  autocomplete="off"/></td>
                    </tr>
--%>
--%>
<%--
                    <tr>
                        <td class="labelTextarea"><label >EventModel Keywords:</label></td>
                        <td><textarea rows="4" cols="50" id="id" type="text" path="eventKeywords"></textarea></td>
                    </tr>
--%>
                    <tr>
                        <td>Event Address Line 1</td>
                        <td>:</td>
                        <td><form:input path="eventAddressLine1" value="${event.eventAddressLine1}" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td>Event Address Line 2</td>
                        <td>:</td>
                        <td><form:input path="eventAddressLine2" value="${event.eventAddressLine2}" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td>Event City</td>
                        <td>:</td>
                        <td><form:input path="eventCity" value="${event.eventCity}" title="Maximum 100 characters"/></td>
                    </tr>
                    <tr>
                        <td>Event State Code</td>
                        <td>:</td>
                        <td>
                            <form:select path="eventStateCode" >
                                <form:option value="${event.eventStateCode}" label="${event.eventStateCode}"/>
                                <form:options items="${addressStateList}" itemValue="stateCode" itemLabel="stateCodeAndName"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Event Postal Code</td>
                        <td>:</td>
                        <td><form:input path="eventPostalCode" value="${event.eventPostalCode}" title="Maximum 100 characters"/></td>
                    </tr>
                    <tr>
                        <td>Event Latitude</td>
                        <td>:</td>
                        <td><form:input path="eventLatitude" value="${event.eventLatitude}" autocomplete="off" maxlength="20"/></td>
                    </tr>
                    <tr>
                        <td>Event Longitude</td>
                        <td>:</td>
                        <td><form:input path="eventLongitude" value="${event.eventLongitude}" autocomplete="off" maxlength="20"/></td>
                    </tr>
<%--
                    <tr>
                        <td></td>
                        <td></td>
                        <td><form:checkbox cssStyle="width: 50px;" path="privateEventInd" value="N"/>Private EventModel</td>
                    </tr>
--%>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="button" value="Cancel" onclick="location.href='/customer/viewProfile?customerID=${customerPaymentCard.customerID}'" title="Cancel and go back to your profile"></td>
                        <td></td>
                        <td><input type="submit" value="Save" title="event detail"/></td>
                    </tr>

                </table>

            </form:form>

        </div>

    </body>

</html>
