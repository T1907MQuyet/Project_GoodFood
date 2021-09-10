
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Customer service Manager"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Customer service management</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">Customer service management</li>
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
                                        <div class="card-title">Customer Service Information:</div>
                                    </div>
                                    <div class="card-body">
                                        <table class="table table-striped">
                                            <tbody>
                                            <tr>
                                                <td class="text-bold">ID Customer Service:</td>
                                                <td>${customerService.customer_service_id}</td>
                                            </tr>
                                            <tr>
                                                <td class="text-bold">Email:</td>
                                                <td>${customerService.customer.email}</td>
                                            </tr>
                                            <tr>
                                                <td class="text-bold">Created date:</td>
                                                <td><fmt:formatDate value="${customerService.created}" pattern="dd-MM-yyyy" /></td>
                                            </tr>
                                            <tr>
                                                <td class="text-bold">Status:</td>
                                                <td><c:if test="${customerService.status ==1}">
                                                        <span class="badge badge-success infoTextSize">active</span>
                                                    </c:if>
                                                    <c:if test="${customerService.status ==2}">
                                                        <span class="badge badge-danger infoTextSize">none active</span>
                                                    </c:if>
                                                    <c:if test="${customerService.status ==0}">
                                                        <span class="badge badge-primary infoTextSize">waiting</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-bold">Received address:</td>
                                                <td>${customerService.received_address}</td>
                                            </tr>
                                            <tr>
                                                <td class="text-bold" width="50%">Service Name:</td>
                                                <td>${customerService.services.service_name }</td>
                                            </tr>
                                            <tr>
                                                <td class="text-bold">Receiving time:</td>
                                                <td><c:if test="${customerService.received_date !=0}">
                                                        Before ${customerService.received_date} Day
                                                    </c:if>
                                                    <c:if test="${customerService.received_date ==0}">
                                                         Right Day
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-bold">Total:</td>
                                                <td>${totalMoney}$</td>
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
                                            Product information
                                        </div>
                                    </div>
                                    <div class="card-body p-0">
                                        <table class="table table-striped projects">
                                            <thead>
                                            <tr>
                                                <th>Product</th>
                                                <th>Image</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${customerServiceProductList}" var="pro" varStatus="itr">
                                                <tr>
                                                    <td>
                                                        <span class="text-bold">Name:</span> ${pro.product.product_name}<br>
                                                        <span class="text-bold">Quantity:</span> ${pro.quantity}<br>
                                                        <span class="text-bold">Price:</span><fmt:formatNumber>${pro.product.price * pro.quantity}</fmt:formatNumber> $
                                                    </td>
                                                    <td class="project-actions ">
                                                        <img src="${pro.product.image}" width="100">
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="Row">
                            <div class="col-12">
                                <div class="card card-info ">
                                    <div class="card-header">
                                        <div class="card-title">Services</div>
                                    </div>
                                    <div class="card-body">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th style="width: 10px">#</th>
                                                    <th>Calender</th>
                                                    <th>Order</th>
                                                    <th>Status</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${calenderOrderList}" var="customerSO" varStatus="itr">
                                                    <tr>
                                                        <th>${offset+itr.index+1}.</th>
                                                        <td><fmt:formatDate value="${customerSO.calender.solar_calendar}" pattern="dd-MM-yyyy" /><br>
                                                            <c:if test="${customerSO.active==1}">
                                                                <span class="badge badge-success infoTextSize">Upcoming events</span>
                                                            </c:if>
                                                        </td>
                                                        <td><c:if test="${customerSO.customerServiceOrder!=null}">
                                                            <span class="text-bold">ID Order:</span> ${customerSO.customerServiceOrder.orders.orderId }
                                                            <br>
                                                            <c:if test="${customerSO.customerServiceOrder.orders.status ==1}">
                                                                <span class="text-bold">Status:</span> <span class="badge badge-primary infoTextSize">Waiting</span>
                                                            </c:if>
                                                            <c:if test="${customerSO.customerServiceOrder.orders.status ==2}">
                                                                <span class="text-bold">Status:</span> <span class="badge badge-info infoTextSize">Confirmed</span>
                                                            </c:if>
                                                            <c:if test="${customerSO.customerServiceOrder.orders.status ==3}">
                                                                <span class="text-bold">Status:</span> <span class="badge badge-warning infoTextSize">Shipping</span>
                                                            </c:if>
                                                            <c:if test="${customerSO.customerServiceOrder.orders.status ==4}">
                                                                <span class="text-bold">Status:</span> <span class="badge badge-success infoTextSize">Complete</span>
                                                            </c:if>
                                                            <c:if test="${customerSO.customerServiceOrder.orders.status ==5}">
                                                                <span class="text-bold">Status:</span> <span class="badge badge-danger infoTextSize">Cancelled</span>
                                                            </c:if>
                                                            <br>
                                                            <span class="text-bold">Received Date:</span> <fmt:formatDate value="${customerSO.customerServiceOrder.orders.received_date}" pattern="dd-MM-yyyy" />
                                                            </c:if>
                                                            <c:if test="${customerSO.customerServiceOrder==null}">
                                                                <p>No orders</p>
                                                            </c:if>
                                                        </td>
                                                        <td><c:if test="${customerSO.status ==1}">
                                                                <span class="badge badge-success infoTextSize">Already have an order</span>
                                                            </c:if>
                                                            <c:if test="${customerSO.status ==2}">
                                                                <c:if test="${calender != customerSO.calender.solar_calendar}">
                                                                    <span class="badge badge-danger infoTextSize">no order yet</span>
                                                                </c:if>
                                                            </c:if>
                                                        </td>
                                                        <td><c:if test="${customerSO.customerServiceOrder!=null}">
                                                                <a href="${pageContext.request.contextPath}/admin/order/detailOrder?id=${customerSO.customerServiceOrder.orders.orderId}" class="btn  btn-primary btn-sm"><i class="fas fa-folder"> </i>Detail order</a>
                                                            </c:if>
                                                            <c:if test="${customerSO.active==1 && customerSO.customerServiceOrder==null}">
                                                                <a href="${pageContext.request.contextPath}/admin/service/createOrder2?id=${customerService.customer_service_id}"  class="btn  btn-success btn-sm"><i class="fas fa-plus"> </i> Create order</a>
                                                            </c:if>
                                                        </td>
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



