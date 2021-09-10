<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="pageTitle" scope="request" value="Admin "/>
<%@include file="../layout/admin/header.jsp" %>


<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0 text-dark">Dashboard</h1>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Dashboard</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
    <section class="col-12">
        <div class="row">
            <div class="col-12 col-sm-6 col-md-3">
                <div class="info-box">
                    <span class="info-box-icon bg-info elevation-1"><i class="fas fa-cog"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Product Active</span>
                        <span class="info-box-number">${countProductActive}
                </span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-12 col-sm-6 col-md-3">
                <div class="info-box mb-3">
                    <span class="info-box-icon bg-danger elevation-1"><i class="fas fa-luggage-cart"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text"><a href="${pageContext.request.contextPath}/admin/service/customerSstatus?status=0" style="color: #0a0a0a">Service waiting</a></span>
                        <span class="info-box-number">${countServiceWaiting}</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->

            <!-- fix for small devices only -->
            <div class="clearfix hidden-md-up"></div>

            <div class="col-12 col-sm-6 col-md-3">
                <div class="info-box mb-3">
                    <span class="info-box-icon bg-success elevation-1"><i class="fas fa-shopping-cart"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text"><a href="${pageContext.request.contextPath}/admin/order/page/1/1" style="color: #0a0a0a">Order waiting</a></span>
                        <span class="info-box-number">${countOrderWaiting}</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-12 col-sm-6 col-md-3">
                <div class="info-box mb-3">
                    <span class="info-box-icon bg-warning elevation-1"><i class="fas fa-users"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Members</span>
                        <span class="info-box-number">${countCustomerList}</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->
        </div>

        <div class="row">
            <div class="col-7">
                <div class="card">
                    <div class="card-header border-0">
                        <h3 class="card-title">Revenue by day</h3>
                        <div class="card-tools">

                        </div>
                    </div>
                    <div class="col-12">
                        <div class="row"  role="form">
                            <div class="form-group col-5">
                                <input type="date" name="date_revenue" class="form-control" id="date_revenue" placeholder="Enter email ">
                            </div>
                            <div class="col-3">
                                <a onclick="getRevenueToDay()" class="btn btn-outline-primary">Check</a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body table-responsive p-0">
                        <table class="table  table-valign-middle">
                            <tr id="revenueToDay">
                                <th>Revenue:</th>
                                <td>0</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-5">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">All services are active </h3>
                        <span data-toggle="tooltip" title="3 New Messages" class="ml-1 badge badge-success">${servicesList.size()}</span>
                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                <i class="fas fa-minus"></i>
                            </button>
                            <button type="button" class="btn btn-tool" data-card-widget="remove">
                                <i class="fas fa-times"></i>
                            </button>
                        </div>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body p-0">
                        <ul class="products-list product-list-in-card pl-2 pr-2">
                            <c:forEach items="${servicesList}" var="services">
                                <li class="item">
                                    <div class="product-img">
                                        <i class="fas fa-check" style="color: #00b44e;width: 100%"></i>
                                    </div>
                                    <div class="product-info">
                                        <a href="${pageContext.request.contextPath}/admin/service/searchServiceReceived?service_id=${services.service_id}&date_received=4" class="product-title">${services.service_name}
                                            <span class="badge badge-info float-right">${services.customerServices.size()}</span></a>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <!-- /.card-body -->
                    <div class="card-footer text-center">
                        <a href="javascript:void(0)" class="uppercase">View All Service</a>
                    </div>
                    <!-- /.card-footer -->
                </div>
            </div>
        </div>
    </section>
    <!-- Main content -->
    <section class="content">
        <figure class="highcharts-figure">
            <div id="container"></div>

        </figure>
    </section>
    <!-- /.content -->
</div>

<jsp:include page="../layout/admin/footer.jsp"/>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script>
    // Create the chart
    Highcharts.chart('container', {
        chart: {type: 'column'},
        title: {text: 'Monthly revenue in 2021'},
        subtitle: {text: ''},
        accessibility: {announceNewData: {enabled: true}},
        xAxis: {type: 'category'},
        yAxis: {title: {text: 'Total percent market share'}},
        legend: {enabled: false},
        plotOptions: {series: {borderWidth: 0,dataLabels: {enabled: true,format: '{point.y:.1f}$'}}},
        tooltip: {headerFormat: '<span style="font-size:11px">{series.name}</span><br>',pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}$</b><br/>'},
        series: [{name: "Revenue",colorByPoint: true, data: [{name: "January", y: ${january},}, {name: "February", y:  ${february},}, {name: "March", y:  ${march},}, {name: "April", y:  ${april},}, {name: "May", y:  ${may},}, {name: "June", y: ${june},}, {name: "July", y: ${july},}, {name: "August", y: ${august},}, {name: "September", y: ${september},}, {name: "October", y: ${october},}, {name: "November", y: ${november},}, {name: "December",y: ${december},}]}]});

    function getRevenueToDay()
    {
        _date_revenue = $("#date_revenue").val();
        //alert(_date_revenue);
        $.ajax({
            method:'GET',
            url:'/admin/revenueToDay?date_revenue='+_date_revenue,
            success:function (res){
                $("#revenueToDay").html(res);
            }

        })
    }
</script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>
