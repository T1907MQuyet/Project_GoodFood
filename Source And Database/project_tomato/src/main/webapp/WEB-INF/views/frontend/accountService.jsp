<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@include file="/WEB-INF/views/layout/frontend/header.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/navbar.jsp" %>
<section class="page_header" style="background: url('/assets/frontend/img/bg4-1.png')">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="text-uppercase">Account</h2>
            </div>
        </div>
    </div>
</section>

<section class="shop-content">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <a href="${pageContext.request.contextPath}/food/account?customer_id=${customer.customer_id}" class="btn btn-primary">Order List</a>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <h3>Service</h3>
                <br>
                <c:if test="${listCustService.size()>0}">
                    <table class="cart-table account-table table table-bordered">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>ID</th>
                                <th>Order</th>
                                <th>Status</th>
                                <th>Name Service</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="custService" items="${listCustService}" varStatus="itr">
                                <tr>
                                    <td>${offset+itr.index+1}</td>
                                    <td>${custService.customer_service_id}</td>
                                    <td><a data-toggle="modal" data-target="#exampleShowOrder" onclick="getOrderListByCustomer(${custService.customer_service_id})" href="javascript:void(0);">Show Order</a></td>
                                    <td><c:if test="${custService.status ==1}">
                                        <p  class="badge badge-success">active</p>
                                        </c:if>
                                        <c:if test="${custService.status ==2}">
                                            <p  class="badge badge-info">non active</p>
                                        </c:if>
                                        <c:if test="${custService.status ==0}">
                                            <p  class="badge badge-primary">waiting</p>
                                        </c:if>
                                    </td>
                                    <td><a data-toggle="modal" data-target="#exampleCalenderService" onclick="getCalenderService(${custService.services.service_id})" href="javascript:void(0);">${custService.services.service_name}</a></td>
                                    <td><a data-toggle="modal" data-target="#exampleModal2" href="javascript:void(0);"  onclick="getServiceDetail(${custService.customer_service_id})">View</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br>
                    <div class="card-footer clearfix">
                        <ul class="pagination pagination-sm m-0 float-right">
                            <c:if test="${totalPages > 1}">
                                <c:if test="${currentPage>1}">
                                    <li class="page-item"><a class="page-link" href="/food/service/accountService/page/${currentPage-1}?customer_id=${customer_id}">«</a></li>
                                </c:if>
                                <c:forEach  end="${totalPages}" begin="1" varStatus="loop">
                                    <c:if test="${currentPage != loop.index}">
                                        <li class="page-item "><a class="page-link" href="/food/service/accountService/page/${loop.index}?customer_id=${customer_id}">${loop.index}</a></li>
                                    </c:if>
                                    <c:if test="${currentPage == loop.index}">
                                        <li class="page-item active"><a class="page-link" href="/food/service/accountService/page/${loop.index}?customer_id=${customer_id}">${loop.index}</a></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${currentPage<totalPages}">
                                    <li class="page-item"><a class="page-link" href="/food/service/accountService/page/${currentPage+1}?customer_id=${customer_id}">»</a></li>
                                </c:if>
                            </c:if>
                        </ul>
                    </div>
                </c:if>
                <br>
                <br>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="ma-address">
                    <h3 class="text-left">Account information</h3>
                    <a data-toggle="modal" data-target="#accountInfomation" class="btn btn-info " style="color: #FFF">Detail</a>
                </div>
            </div>
        </div>
    </div>
</section>

<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Order information</h5>
            </div>
            <div id="contentOrderDetail" class="modal-body">
                <label>Category:</label>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel2">Service information</h5>
            </div>
            <div id="contentService" class="modal-body">
                <label>Services:</label>


            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="exampleCalenderService" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleCalenderServicelabel">Calender infomation</h5>
            </div>
            <div id="calenderService" class="modal-body">

            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>
<div class="modal fade" id="accountInfomation" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" >Account</h5>
            </div>
            <div  class="modal-body">
                <table class="table table-bordered extra-padding">
                    <tbody>
                    <tr>
                        <th>Email</th>
                        <td>
                            <span class="amount">${customer.email}</span>
                        </td>
                    </tr>
                    <tr>
                        <th>Full Name:</th>
                        <td>
                            <span class="amount">${customer.username}</span>
                        </td>
                    </tr>
                    <tr>
                        <th>Address:</th>
                        <c:if test="${customer.address!=null}">
                            <td>
                                <span class="amount">${customer.address}</span>
                            </td>
                        </c:if>
                        <c:if test="${customer.address==null}">
                            <td>
                                <span class="amount">Updating ...</span>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <th>Phone Number:</th>
                        <c:if test="${customer.phonenumber != null}">
                            <td>
                                <span class="amount">${customer.phonenumber}</span>
                            </td>
                        </c:if>
                        <c:if test="${customer.phonenumber == null}">
                            <td>
                                <span class="amount">Updating ...</span>
                            </td>
                        </c:if>
                    </tr>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="exampleShowOrder" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleShowOrderCustomerService">Order List</h5>
            </div>
            <div id="showOrder" class="modal-body">

            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>


<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/ajaxScript.jsp" %>
<script>
    function getOrderDetail(_order_id){
        $.ajax({
            method:'GET',
            url:'/food/account/orderDetail?order_id='+_order_id,
            success:function (res) {
                $("#contentOrderDetail").html(res);
            }
        })
    }
    function getServiceDetail(_custServiceId){
        $.ajax({
            method: 'GET',
            url: '/food/service/serviceDetail?id='+_custServiceId,
            success:function (res)
            {
                $("#contentService").html(res);
            }
        })
    }
    function getCalenderService(_service_id)
    {
        $.ajax({
            method:'GET',
            url:'/food/service/getCalender?service_id='+_service_id,
            success:function (res)
            {
                $("#calenderService").html(res);
            }
        })
    }
    function getOrderListByCustomer(_customer_service_id)
    {
       $.ajax({
           method:'GET',
           url:'/food/service/showOrderCS?customer_service_id='+_customer_service_id,
           success:function (res)
           {
               $("#showOrder").html(res);
           }
       })
    }
</script>
