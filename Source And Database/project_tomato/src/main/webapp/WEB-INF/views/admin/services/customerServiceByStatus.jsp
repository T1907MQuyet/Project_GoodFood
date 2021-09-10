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
                    </div>
                    <%@include file="/WEB-INF/views/admin/services/tableCustomerService.jsp" %>
                    <div class="card-footer clearfix">
                        <ul class="pagination pagination-sm m-0 float-right">
                            <c:if test="${totalPages > 1}">
                                <c:if test="${currentPage>1}">
                                    <li class="page-item"><a class="page-link" href="/admin/service/customerSstatus/page/${currentPage-1}?status=${status}">«</a></li>
                                </c:if>
                                <c:forEach  end="${totalPages}" begin="1" varStatus="loop">
                                    <c:if test="${currentPage != loop.index}">
                                        <li class="page-item "><a class="page-link" href="/admin/service/customerSstatus/page/${loop.index}?status=${status}">${loop.index}</a></li>
                                    </c:if>
                                    <c:if test="${currentPage == loop.index}">
                                        <li class="page-item active"><a class="page-link" href="/admin/service/customerSstatus/page/${loop.index}?status=${status}">${loop.index}</a></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${currentPage<totalPages}">
                                    <li class="page-item"><a class="page-link" href="/admin/service/customerSstatus/page/${currentPage+1}?status=${status}">»</a></li>
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