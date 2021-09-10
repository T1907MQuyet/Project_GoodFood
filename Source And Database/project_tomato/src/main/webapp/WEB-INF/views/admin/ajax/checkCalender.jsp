<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:forEach items="${list}" var="calender" varStatus="itr">
    <tr>
        <td>${offset+itr.index+1}</td>
        <td><fmt:formatDate value="${calender.solar_calendar}" pattern="dd-MM-yyyy" /></td>
        <td><fmt:formatDate value="${calender.lunar_calendar}" pattern="dd-MM-yyyy" /></td>
    </tr>
</c:forEach>