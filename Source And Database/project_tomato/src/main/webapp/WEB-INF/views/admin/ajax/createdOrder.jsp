<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${list.size()>0}">
<table class="table table-striped projects">
    <thead>
    <tr>
        <th style="width: 10px">STT</th>
        <th>Customer</th>
        <th>Service</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="customerService" varStatus="itr">
        <tr>
            <td>${offset+itr.index+1}</td>
            <td><span class="text-bold">ID:</span> ${customerService.customer_service_id }
                <br>
                    ${customerService.customer.email }
                <br>
                <c:if test="${customerService.status ==1}">
                    <span class="text-bold">status:</span> <span class="badge badge-success">active</span>
                </c:if>
                <c:if test="${customerService.status ==2}">
                    <span class="text-bold">status:</span> <span class="badge badge-danger">none active</span>
                </c:if>
                <br>
                <span class="text-bold">Registration Date:</span>  <fmt:formatDate value="${customerService.created}" pattern="dd-MM-yyyy" />
            </td>
            <td>${customerService.services.service_name }
                <br>
                <span class="text-bold">Received Date:</span> Before ${customerService.received_date} Day
                <br>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/service/createOrder?id=${customerService.customer_service_id}" class="btn  btn-success btn-sm"><i class="fas fa-plus"> </i> Create order</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>
<c:if test="${list.size()==0}">
    <p>No services</p>
</c:if>