<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<table class="table table-bordered extra-padding">
    <tbody>
    <tr>
        <th>Email:</th>
        <td>
            <span class="amount">${custService.customer.email}</span>
        </td>
    </tr>
    <tr>
        <th>Register Date:</th>
        <td>
            <span class="amount"><fmt:formatDate value="${custService.created}" pattern="dd-MM-yyyy" /></span>
        </td>
    </tr>
    <tr>
        <th>Active Date:</th>
        <td>
            <span class="amount"><fmt:formatDate value="${custService.updated}" pattern="dd-MM-yyyy" /></span>
        </td>
    </tr>
    <tr>
        <th>Received address:</th>
        <td>
            <span class="amount">${custService.received_address}</span>
        </td>
    </tr>
    <tr>
        <th>Received phone number:</th>
        <td>
            <span class="amount">${custService.received_phone_number}</span>
        </td>
    </tr>
    <tr>
        <th>Event start date:</th>
        <td>${calender}</td>
    </tr>
    <tr>
        <th>Status:</th>
        <td>
            <c:if test="${custService.status ==1}">
                <span  class="badge badge-success">active</span>
            </c:if>
            <c:if test="${custService.status ==2}">
                <span  class="badge badge-info">none active</span>
            </c:if>
            <c:if test="${custService.status ==0}">
                <span  class="badge badge-primary">waiting</span>
            </c:if>
        </td>
    </tr>
    <tr>
        <th>Received date:</th>
        <td>
            <span class="amount">Before ${custService.received_date} day</span>
        </td>
    </tr>
    <tr>
        <td colspan="2">${custService.services.description}</td>
    </tr>
    </tbody>
</table>
<table class="table table-striped projects">
    <thead>
    <tr>
        <th style="width: 10px">STT</th>
        <th>Product</th>
        <th>Image</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${listCSP}" var="product" varStatus="itr">
        <tr>
            <th>${offset+itr.index+1}
            </th>
            <td><span style="font-weight: bold">Name:</span> ${product.product.product_name}
                <br>
                <span style="font-weight: bold">Quantity:</span> ${product.quantity}
                <br>
                <span style="font-weight: bold">Category:</span> ${product.product.category_detail.cate_detail_name}
            </td>
            <td><img src="${product.product.image}" width="50"></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${custService.status==1}">
  <a href="${pageContext.request.contextPath}/food/canceledService?customer_service_id=${custService.customer_service_id}" class="badge badge-danger btn-sm">Cancel Service</a>
</c:if>
<c:if test="${custService.status==0}">
    <a href="${pageContext.request.contextPath}/food/canceledService?customer_service_id=${custService.customer_service_id}" class="badge badge-danger btn-sm">Cancel Service</a>
</c:if>


