<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="cart-table account-table table table-bordered">
<thead>
<tr>
    <th>ID Order</th>
    <th>Order</th>
    <th>Status</th>
    <th>Total</th>
<%--    <th></th>--%>
</tr>
</thead>
<tbody>
<c:forEach var="order" items="${list}">
    <tr>
        <td>${order.orders.orderId}</td>
        <td style="text-align: left">
            <span style="font-weight: bold">Received name: </span>${order.orders.fullname}<br>
            <span style="font-weight: bold">Received date: </span><fmt:formatDate value="${order.orders.received_date}" pattern="dd-MM-yyyy" /><br>
            <span style="font-weight: bold">Payment: </span>${order.orders.payment}<br>
            <span style="font-weight: bold">Address: </span>${order.orders.order_address}<br>
        </td>
        <td>
            <c:if test="${order.orders.status ==1}">
                <p  class="badge badge-primary">Waiting</p>
            </c:if>
            <c:if test="${order.orders.status ==2}">
                <p  class="badge badge-info">Confirmed</p>
            </c:if>
            <c:if test="${order.orders.status ==3}">
                <p  class="badge badge-success">Shipping</p>
            </c:if>
            <c:if test="${order.orders.status ==4}">
                <p  class="badge badge-warning">Complete</p>
            </c:if>
            <c:if test="${order.orders.status ==5}">
                <p  class="badge badge-danger">Cancelled</p>
            </c:if>
        </td>
        <td>
            $${order.orders.total_price}
        </td>
<%--        <td>--%>
<%--            <a data-toggle="modal" data-target="#exampleModal1" onclick="getOrderDetail(${order.orderId})" href="javascript:void(0);">View</a>--%>
<%--        </td>--%>
    </tr>
</c:forEach>
</tbody>
</table>