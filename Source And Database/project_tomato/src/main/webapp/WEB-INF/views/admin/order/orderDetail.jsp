<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Order Manager"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Order management</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">Order management</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-12">
                <div class="row">
                    <div class="col-md-4">
                        <div class="row">
                        <div class="col-md-12">
                            <div class="card card-info">
                                <div class="card-header">
                                    <div class="card-title">
                                        Orders Information:
                                    </div>
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <tbody>
                                        <tr>
                                            <td class="text-bold">ID Order:</td>
                                            <td>${order.orderId}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Name of consignee:</td>
                                            <td>${order.fullname}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Created order:</td>
                                            <td><fmt:formatDate value="${order.created}" pattern="dd-MM-yyyy" /></td>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Total price:</td>
                                            <td><fmt:formatNumber>${order.total_price}</fmt:formatNumber> $</td>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Payments:</td>
                                            <td>${order.payment}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Received date:</td>
                                            <td><fmt:formatDate value="${order.received_date}" pattern="dd-MM-yyyy" /></td>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Email:</td>
                                            <td>${order.order_email}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Address:</td>
                                            <td>${order.order_address}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Phone number:</td>
                                            <td>${order.phone_number}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Status:</td>
                                            <td><a  class="btn ${btnbtn} btn-sm" style="color: white">${trangThai}</a></td>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Payment Status:</td>
                                            <c:if test="${order.payment_status == 1}">
                                                <td><span class="badge badge-success infoTextSize">Paid</span></td>
                                            </c:if>
                                            <c:if test="${order.payment_status != 1}">
                                                <td><span class="badge badge-danger infoTextSize">Unpaid</span></td>
                                            </c:if>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Service</td>
                                            <c:if test="${order.customerServiceOrders.customerService.services.service_name != null}">
                                                <td><span class="badge badge-success infoTextSize">${order.customerServiceOrders.customerService.services.service_name}</span></td>
                                            </c:if>
                                            <c:if test="${order.customerServiceOrders.customerService.services.service_name == null}">
                                                <td><span class="badge badge-danger infoTextSize">None service</span></td>
                                            </c:if>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Detail Service</td>
                                            <c:if test="${order.customerServiceOrders.customerService.services.service_name != null}">
                                                <td><a href="${pageContext.request.contextPath}/admin/service/detailCustomerService?id=${order.customerServiceOrders.customerService.customer_service_id}" class="badge badge-primary infoTextSize">Detail service</a></td>
                                            </c:if>
                                            <c:if test="${order.customerServiceOrders.customerService.services.service_name == null}">
                                                <td><span class="badge badge-danger infoTextSize">Non service</span></td>
                                            </c:if>
                                        </tr>
                                        <tr>
                                            <td class="text-bold">Note:</td>
                                            <td>${order.order_note}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                            </div>
                        </div>
                        </div>
                        <div class="col-md-12">
                            <div class="card card-info">
                                <div class="card-header">
                                    <div class="card-title">
                                        Status Order:
                                    </div>
                                </div>
                                <%--@elvariable id="order" type=""--%>
                                <f:form  method="POST" action="${pageContext.request.contextPath}/admin/order/updateStatusOrder" modelAttribute="order">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <input type="hidden" name="orderId" value="${order.orderId}"/>
                                                <!-- select -->
                                                <div class="form-group">
                                                    <label>Update Status Oredr:</label>
                                                    <f:select class="form-control" name="status" path="status">
                                                        <c:if test="${order.status ==1}">
                                                            <f:option value="1">Waiting</f:option>
                                                            <f:option value="2">Confirmed</f:option>
                                                            <f:option value="3">Shipping</f:option>
                                                            <f:option value="4">Complete</f:option>
                                                            <f:option value="5">Cancelled</f:option>
                                                        </c:if>
                                                        <c:if test="${order.status ==2}">
                                                            <f:option value="2">Confirmed</f:option>
                                                            <f:option value="3">Shipping</f:option>
                                                            <f:option value="4">Complete</f:option>
                                                            <f:option value="5">Cancelled</f:option>
                                                        </c:if>
                                                        <c:if test="${order.status ==3}">
                                                            <f:option value="3">Shipping</f:option>
                                                            <f:option value="4">Complete</f:option>
                                                            <f:option value="5">Cancelled</f:option>
                                                        </c:if>
                                                        <c:if test="${order.status ==4}">
                                                            <f:option value="4">Complete</f:option>
                                                        </c:if>
                                                        <c:if test="${order.status ==5}">
                                                            <f:option value="5">Cancelled</f:option>
                                                        </c:if>
                                                    </f:select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <button type="submit" class="btn btn-info">Update</button>
                                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/order"><i class="fas fa-arrow-left"></i>Back Order List</a>
<%--                                        <a class="btn btn-primary" href="${historyAdmin_url}"><i class="fas fa-arrow-left"></i> Back</a>--%>
                                    </div>
                                </f:form>
                            </div>
                        </div>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="Row">
                            <div class="col-12">
                                <div class="card card-info ">
                                    <div class="card-header">
                                        <div class="card-title">
                                            Orders Detail
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th style="width: 10px">#</th>
                                                <th>Product image</th>
                                                <th>Product name</th>
                                                <th>Quantity</th>
                                                <th>Category</th>
                                                <th>Price</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${listOrderDetail}" var="lsOdDetail" varStatus="itr">
                                                <tr>
                                                    <th>${offset+itr.index+1}.</th>
                                                    <td><img src="${lsOdDetail.product.image}" width="100"></td>
                                                    <td>${lsOdDetail.product.product_name}</td>
                                                    <td>${lsOdDetail.quantity}</td>
                                                    <td>${lsOdDetail.product.category_detail.cate_detail_name}</td>
                                                    <td><fmt:formatNumber>${lsOdDetail.price}</fmt:formatNumber>$</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>

</body>
</html>



