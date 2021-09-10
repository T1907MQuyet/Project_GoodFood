<%@ page import="tomato_th.project_tomato.model.service.Calender" %>
<%@ page import="tomato_th.project_tomato.model.service.CustomerService" %>
<%@ page import="tomato_th.project_tomato.controller.backend.services.ServicesController" %>
<%@ page import="java.util.List" %>
<%@ page import="tomato_th.project_tomato.model.service.CustomerServiceOrder" %>
<%@ page import="tomato_th.project_tomato.service.Impl.services.CustomerSOServiceImpl" %>
<%@ page import="tomato_th.project_tomato.repository.service.CustomerSORepository" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Service management"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Service Customer management</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">Service Customer management</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <div class="container-fluid mb-3">
        <%@include file="/WEB-INF/views/admin/services/lokkUpServiceCustomer.jsp" %>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">Service List <span class="text-bold" style="font-size: 13px">(${totalItems} item)</span>.</h3>
                        <a class="btn btn-info float-lg-right" data-toggle="modal" data-target="#exampleModal6" href="javascript:void(0)"><i class="fas fa-plus"> </i> Create orders for all services</a>
                    </div>
                    <div class="card-body ">
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
                                            <span class="text-bold">Email:</span> ${customerService.customer.email }
                                            <br>
                                            <c:if test="${customerService.status ==1}">
                                                <span class="text-bold">status:</span> <span class="badge badge-success infoTextSize" >active</span>
                                            </c:if>
                                            <c:if test="${customerService.status ==2}">
                                                <span class="text-bold">status:</span> <span class="badge badge-danger infoTextSize">non active</span>
                                            </c:if>
                                            <c:if test="${customerService.status ==0}">
                                                <span class="text-bold">status:</span> <span class="badge badge-primary infoTextSize">waiting</span>
                                            </c:if>
                                            <br>
                                            <span class="text-bold">Registration Date:</span>  <fmt:formatDate value="${customerService.created}" pattern="dd-MM-yyyy" />
                                            <br>
                                            <span class="text-bold">Order:</span> <a href="javascript:void(0);" data-toggle="modal" data-target="#exampleModal4" onclick="getAllCustServiceOrder(${customerService.customer_service_id})" class="badge badge-primary infoTextSize">Check</a>
                                        </td>
                                        <td><span class="text-bold">Name:</span> ${customerService.services.service_name }
                                            <br>
                                            <c:if test="${customerService.received_date !=0}">
                                                <span class="text-bold">Received Date:</span> Before ${customerService.received_date} Day
                                            </c:if>
                                            <c:if test="${customerService.received_date ==0}">
                                                <span class="text-bold">Received Date:</span> Right Day
                                            </c:if>
                                            <br>
                                            <a  data-toggle="modal" data-target="#exampleModal1" class="badge badge-primary infoTextSize" href="javascript:void(0);" onclick="getCalender(${customerService.services.service_id})">calendar</a>
                                            <a  data-toggle="modal" data-target="#exampleModal2" class="badge badge-primary infoTextSize" href="javascript:void(0);" onclick="getCustomerServiceProduct(${customerService.customer_service_id})">check product</a>
                                            <br>

                                            <c:choose>
                                                <c:when test="${calender.solar_calendar!=null}">
                                                    <span class="text-bold">Upcoming Date:</span> <span class="badge badge-success infoTextSize"><fmt:formatDate value="${calender.solar_calendar}" pattern="dd-MM-yyyy" /></span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="text-bold">Upcoming Date:</span> <span class="badge badge-danger infoTextSize">Event has ended</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:if test="${customerService.status ==1}"></c:if>
                                            <c:if test="${customerService.status !=0}">
                                                <a href="${pageContext.request.contextPath}/admin/service/detailCustomerService?id=${customerService.customer_service_id}" class="btn  btn-primary btn-sm"><i class="fas fa-folder"> </i> </a>
                                            </c:if>
                                            <c:if test="${customerService.status ==0}">
                                                <a href="${pageContext.request.contextPath}/admin/service/activeService?id=${customerService.customer_service_id}" onclick="return confirm('Are you sure?')"  class="btn  btn-success btn-sm"><i class="fas fa-plus"> </i> Active</a>
                                            </c:if>
                                            <%int i=0;%>
                                            <c:if test="${calender.solar_calendar!=null}">
                                                <c:forEach items="${customerServiceOrderList}" var="custSOD">
                                                    <c:if test="${custSOD.customerService.customer_service_id == customerService.customer_service_id}">
                                                        <% CustomerServiceOrder customerServiceOrder = (CustomerServiceOrder) pageContext.getAttribute("custSOD");
                                                            Calender calen = (Calender) request.getAttribute("calender");
                                                            if(calen.getSolar_calendar().equals(customerServiceOrder.getReceived_date())) { i=1; }
                                                            else{ i+=2; }%>
                                                    </c:if>
                                                </c:forEach>
                                                <c:choose>
                                                    <c:when test="<%= i<1%>">
                                                        <a href="${pageContext.request.contextPath}/admin/service/createOrderByEvenDate?id=${customerService.customer_service_id}&service_id=${customerService.services.service_id}&date_received=${param.date_received}" onclick="return confirm('Are you sure?')" class="btn  btn-info btn-sm"><i class="fas fa-plus"> </i> Create order</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a style="cursor: no-drop;" href="javascript:void(0)" class="btn  btn-success btn-sm"><i class="fas fa-check-circle"></i> Create order</a>
                                                    </c:otherwise>
                                                </c:choose>
                                                <%--                                            <c:if test="<%= i<1%>">--%>
                                                <%--                                                <a href="${pageContext.request.contextPath}/admin/service/createOrderByEvenDate?id=${customerService.customer_service_id}&service_id=${customerService.services.service_id}&date_received=${param.date_received}" onclick="return confirm('Are you sure?')" class="btn  btn-info btn-sm"><i class="fas fa-plus"> </i> Create order</a>--%>
                                                <%--                                            </c:if>--%>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${list.size()==0}">
                            <p class="text-center">No service yet</p>
                        </c:if>
                    </div>
                    <div class="card-footer clearfix">
                        <ul class="pagination pagination-sm m-0 float-right">
                            <c:if test="${totalPages > 1}">
                                <c:if test="${currentPage>1}">
                                    <li class="page-item"><a class="page-link" href="/admin/service/searchServiceReceived/page/${currentPage-1}?service_id=${service_id}&&date_received=${date_received}">«</a></li>
                                </c:if>
                                <c:forEach  end="${totalPages}" begin="1" varStatus="loop">
                                    <c:if test="${currentPage != loop.index}">
                                        <li class="page-item "><a class="page-link" href="/admin/service/searchServiceReceived/page/${loop.index}?service_id=${service_id}&&date_received=${date_received}">${loop.index}</a></li>
                                    </c:if>
                                    <c:if test="${currentPage == loop.index}">
                                        <li class="page-item active"><a class="page-link" href="/admin/service/searchServiceReceived/page/${loop.index}?service_id=${service_id}&&date_received=${date_received}">${loop.index}</a></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${currentPage<totalPages}">
                                    <li class="page-item"><a class="page-link" href="/admin/service/searchServiceReceived/page/${currentPage+1}?service_id=${service_id}&&date_received=${date_received}">»</a></li>
                                </c:if>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <!-- /.card -->
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/admin/services/modalService.jsp" %>

<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>
<script>
    function getCalender(_service_id) {
        $.ajax({
            method:'GET',
            url:'/admin/service/checkCalenderService?id='+_service_id,
            success:function (res) {
                $("#calender").html(res);
            }})
    }
    function getCustomerServiceProduct(_customerServiceProduct_id) {
        $.ajax({
            method: 'GET',
            url: '/admin/service/checkCustPro?custService_id='+_customerServiceProduct_id,
            success:function (res) {
                $("#customerServiceProduct").html(res);
            }})
    }
    function getCustSPOrder() {
        $.ajax({
            method:'GET',
            url:'/admin/service/checkOrderToday',
            success:function (res) {
                $("#customerServiceOrder").html(res);
            }})
    }
    function getAllCustServiceOrder(_customer_service_id) {
        $.ajax({
            method:'GET',
            url:'/admin/service/checkOrderCS?id='+_customer_service_id,
            success:function (res) {
                $("#customerSO").html(res);
            }})
    }
</script>
</body>
</html>