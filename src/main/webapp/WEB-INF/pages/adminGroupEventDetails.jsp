<%--
  Created by IntelliJ IDEA.
  User: mPartovi
  Date: 1/29/16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

    <head>
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
            function calcLatLng() {
                var geocoder = new google.maps.Geocoder();
                var address = $("#eventAddressLine1").val() + " " + $("#eventAddressLine2").val() + " " + $("#eventCity").val() + " " + $("#eventStateCode").val() + " " + $("#eventPostalCode").val()
                geocoder.geocode({'address': address}, function (results, status) {

                    if (status == google.maps.GeocoderStatus.OK) {
                        var latitude = results[0].geometry.location.lat();
                        var longitude = results[0].geometry.location.lng();

                        $("#eventLatitude").val(latitude.toFixed(6));
                        $("#eventLongitude").val(longitude.toFixed(6));
                    }
                });
            }
            $(document).ready(function () {
                $("input").change(function () {
                    if ($("#eventAddressLine1").val() !== "" && $("#eventCity").val() !== "" && $("#eventStateCode").val() !== "" && $("#eventPostalCode").val() !== "")
                        calcLatLng();
                    else {
                        $("#eventLatitude").val("");
                        $("#eventLongitude").val("");
                    }
                });
            });
        </script>

        <style>
            .header {
                background-color: darkorange;
                color: white;
                width: 100%;
                height: 80px;
                padding: 10px;
                text-align: center;
            }
            .nav {
                background-color: gold;
                width: 100%;
                height: 40px;
                padding: 10px;
                float: top;
            }
            .section {
                width: 100%;
                height: 600px;
                padding: 10px;
                float: top;
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
                    <th><security:authorize access="isAuthenticated()"><a href="/contactUs">Contact Us</a></security:authorize>
                    </th>
                    <th>
                        <security:authorize access="isAuthenticated()">
                            Hello<br>${sessionScope.userFirstName}!
                        </security:authorize>
                    </th>
                    <th>
                        <security:authorize access="isAuthenticated()">
                            <a href="<c:url value="/admin/groups?userId=${sessionScope.userId}" />">View Groups</a>
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

            <form:form class="mainForm" method="post" action="/admin/group/event/details/submit" modelAttribute="event">
                <form:hidden path="eventId" value="${event.eventId}"/>
                <form:hidden path="groupId" value="${event.groupId}"/>
                <table class="mainTable" style="margin: auto;">
                    <caption style="font-size: x-large;font-weight: bold;text-decoration: underline;padding: 20px;background-color: lightcyan;">Event Details</caption>
                    <tr>
                        <td style="text-align: left">Event Name</td>
                        <td>:</td>
                        <td><form:input path="eventName" value="${event.eventName}" required="true" autocomplete="off" maxlength="20" title="maximum 20 characters"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: left">Event Description</td>
                        <td>:</td>
                        <td><form:textarea path="eventDescription" value="${event.eventDescription}" required="true"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: left">Event Start Date</td>
                        <td>:</td>
                        <td>
                            <fmt:formatDate value="${event.eventStartDatetime}" type="date" pattern="MM/dd/yyyy hh:mm a" var="FormattedStartDate"/>
                            <div id="datetimepicker2" class="input-append date">
                                <form:input path="eventStartDatetime" autocomplete="off" value="${FormattedStartDate}"/>
                                    <span class="add-on">
                                    <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                  </span>
                                <script type="text/javascript">
                                    $('#datetimepicker2').datetimepicker({
                                        format: 'MM/dd/yyyy HH:mm PP',
                                        language: 'en',
                                        pick12HourFormat: true
                                    });
                                </script>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left">Event End Date</td>
                        <td>:</td>
                        <td>
                            <fmt:formatDate value="${event.eventEndDatetime}" type="date" pattern="MM/dd/yyyy hh:mm a" var="FormattedEndDate"/>
                            <div id="datetimepicker3" class="input-append date">
                                <form:input path="eventEndDatetime" value="${FormattedEndDate}" autocomplete="off"/>
                                  <span class="add-on">
                                    <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                  </span>
                                <script type="text/javascript">
                                    $('#datetimepicker3').datetimepicker({
                                        format: 'MM/dd/yyyy HH:mm PP',
                                        language: 'en',
                                        pick12HourFormat: true
                                    });
                                </script>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left">Event Keywords</td>
                        <td>:</td>
                        <td><form:input path="eventKeywords" value="${event.eventKeywords}"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: left">Event Address Line 1</td>
                        <td>:</td>
                        <td><form:input id="eventAddressLine1" path="eventAddressLine1" value="${event.eventAddressLine1}" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: left">Event Address Line 2</td>
                        <td>:</td>
                        <td><form:input id="eventAddressLine2" path="eventAddressLine2" value="${event.eventAddressLine2}" title="Maximum 200 characters"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: left">Event City</td>
                        <td>:</td>
                        <td><form:input id="eventCity" path="eventCity" value="${event.eventCity}" title="Maximum 100 characters"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: left">Event State Code</td>
                        <td>:</td>
                        <td>
                            <form:select id="eventStateCode" path="eventStateCode">
                                <form:option value="${event.eventStateCode}" label="${event.eventStateCode}"/>
                                <form:options items="${refStateList}" itemValue="stateCode" itemLabel="stateCodeAndName"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left">Event Postal Code</td>
                        <td>:</td>
                        <td><form:input id="eventPostalCode" path="eventPostalCode" value="${event.eventPostalCode}" title="Maximum 100 characters"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: left">Event Latitude</td>
                        <td>:</td>
                        <td><form:input id="eventLatitude" path="eventLatitude" value="${event.eventLatitude}"
                                        autocomplete="off" maxlength="20"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: left">Event Longitude</td>
                        <td>:</td>
                        <td><form:input id="eventLongitude" path="eventLongitude" value="${event.eventLongitude}" autocomplete="off" maxlength="20"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td><form:checkbox cssStyle="width: 50px;" path="privateEvent" value="Y"/>Private</td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Cancel" title="cancel changes"></td>
                        <td></td>
                        <td><input type="submit" value="Save" title="save changes to the event"/></td>
                    </tr>
                </table>
            </form:form>

            <table class="linkTable" style="margin: auto;">
                <tr>
                    <th><a href="#" onclick="history.go(-1)" title="go back to group details">Go Back</a></th>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </table>

        </div>

    </body>

</html>
