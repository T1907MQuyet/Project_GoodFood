<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<c:if test="${listCalender.size()>0}">
<table class="table table-striped projects">
    <thead>
    <tr>
        <th style="width: 10px">STT</th>
        <th>solar calendar</th>
        <th>lunar calendar</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${listCalender}" var="calender" varStatus="itr">
        <tr>
            <td>${offset+itr.index+1}</td>
            <td><fmt:formatDate value="${calender.solar_calendar}" pattern="dd-MM-yyyy" /></td>
            <td><fmt:formatDate value="${calender.lunar_calendar}" pattern="dd-MM-yyyy" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>
<c:if test="${listCalender==null}">
    <p class="text-center">The event has ended</p>
</c:if>


