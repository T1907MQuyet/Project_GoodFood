<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <h4 class="text-left">Service:</h4>
    <br>
    <table class="table table-bordered extra-padding">
        <tbody>
        <tr>
            <th>Type Service</th>
            <td><span class="amount">${service.service_name}</span></td>
        </tr>
        <tr>
            <td colspan="2">${service.description}</td>
        </tr>
        <tr>
            <th>Solar calendar</th>
            <th>Lunar calendar</th>
        </tr>
        <c:forEach var="calender" items="${listCalender}">
            <tr>
                <td><fmt:formatDate value="${calender.solar_calendar}" pattern="dd-MM-yyyy" /></td>
                <td><fmt:formatDate value="${calender.lunar_calendar}" pattern="dd-MM-yyyy" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
