<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>



<table class="table table-bordered extra-padding">
    <tbody>
    <tr>
        <th>ID Order</th>
        <td>
            <span class="amount">${orders.orderId}</span>
        </td>
    </tr>
    <tr>
        <th>Email:</th>
        <td>
            <span class="amount">${orders.order_email}</span>
        </td>
    </tr>
    <tr>
        <th>Receiver:</th>
        <td>
            <span class="amount">${orders.fullname}</span>
        </td>
    </tr>
    <tr>
        <th>Received phone number:</th>
        <td>
            <span class="amount">${orders.phone_number}</span>
        </td>
    </tr>
    <tr>
        <th>Status:</th>
        <td>
            <span class="amount">
                <c:if test="${orders.status ==1}">
                    <span  class="badge badge-primary">Waiting</span>
                </c:if>
                <c:if test="${orders.status ==2}">
                    <span  class="badge badge-info">Confirmed</span>
                </c:if>
                <c:if test="${orders.status ==3}">
                    <span  class="badge badge-success">Shipping</span>
                </c:if>
                <c:if test="${orders.status ==4}">
                    <span  class="badge badge-warning">Complete</span>
                </c:if>
                <c:if test="${orders.status ==5}">
                    <span  class="badge badge-danger">Cancelled</span>
                </c:if></span>
        </td>
    </tr>
    <tr>
        <th>Received date:</th>
        <td>
            <span class="amount"><fmt:formatDate value="${orders.received_date}" pattern="dd-MM-yyyy" /></span>
        </td>
    </tr>
    <c:if test="${orders.customerServiceOrders.customerService.customer_service_id != null}">
    <tr>
        <th>Service:</th>
        <td><p  class="badge badge-success">${orders.customerServiceOrders.customerService.services.service_name}</p></td>
    </tr>
    <tr>
        <th>ID Service: </th>
        <td>${orders.customerServiceOrders.customerService.customer_service_id}</td>
    </tr>
    </c:if>
    <tr>
        <td colspan="2">${orders.order_note}</td>

    </tr>
    </tbody>
</table>
<table class="table table-striped projects">
<thead>
<tr>
    <th style="width: 10px">STT</th>
    <th>Product Name</th>
    <th>Image</th>
</tr>
</thead>
<tbody id="allProduct">

<c:forEach items="${listOrderDetail}" var="order" varStatus="itr">
    <tr>
        <th>${offset+itr.index+1}</th>
        <td><span style="font-weight: bold">name:</span> ${order.product.product_name}
            <br>
            <span style="font-weight: bold">Price:</span> <fmt:formatNumber>${order.price}</fmt:formatNumber> $
            <br>
            <span style="font-weight: bold">Category:</span> ${order.product.category_detail.cate_detail_name}
        </td>
        <td><img src="${order.product.image}" width="50"></td>
    </tr>
</c:forEach>
</tbody>
</table>
<c:if test="${status ==1}">
    <a href="${pageContext.request.contextPath}/food/canceledOrder?order_id=${order_id}&&status=${status}" class="badge badge-danger btn-sm">Cancel orders</a>
</c:if>
<p class="badge-success"> ${payment}</p>
<c:if test="${payment_status ==1}">
    <p class="badge-success"> pay off</p>
</c:if>


