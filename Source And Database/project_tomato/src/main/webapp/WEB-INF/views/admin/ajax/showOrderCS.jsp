<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${list.size()>0}">
<table class="table table-striped projects">
    <thead>
    <tr>
        <th style="width: 10px">STT</th>
        <th>Order</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="customerSO" varStatus="itr">
        <tr>
            <td>${offset+itr.index+1}</td>
            <td><span class="text-bold">ID:</span> ${customerSO.orders.orderId }
                <br>
                    ${customerSO.orders.order_email }
                <br>
                <c:if test="${customerSO.orders.status ==1}">
                    <span class="text-bold">status:</span> <span class="badge badge-primary">Waiting</span>
                </c:if>
                <c:if test="${customerSO.orders.status ==2}">
                    <span class="text-bold">status:</span> <span class="badge badge-info">Confirmed</span>
                </c:if>
                <c:if test="${customerSO.orders.status ==3}">
                    <span class="text-bold">status:</span> <span class="badge badge-warning btn-sm">Shipping</span>
                </c:if>
                <c:if test="${customerSO.orders.status ==4}">
                    <span class="text-bold">status:</span> <span class="badge badge-success btn-sm">Complete</span>
                </c:if>
                <c:if test="${customerSO.orders.status ==5}">
                    <span class="text-bold">status:</span> <span class="badge badge-danger btn-sm">Cancelled</span>
                </c:if>
                <br>
                <span class="text-bold">Received Date:</span> <fmt:formatDate value="${customerSO.orders.received_date}" pattern="dd-MM-yyyy" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/order/detailOrder?id=${customerSO.orders.orderId}" class="btn  btn-success btn-sm"><i class="fas fa-plus"> </i>Detail order</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>
<c:if test="${list.size()==0}">
    <p>No orders yet</p>
</c:if>