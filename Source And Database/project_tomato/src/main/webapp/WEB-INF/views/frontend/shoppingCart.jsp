<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@include file="/WEB-INF/views/layout/frontend/header.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/navbar.jsp" %>
<section class="page_header"style="background: url('/assets/frontend/img/bg4-1.png')">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="text-uppercase">Cart</h2>
            </div>
        </div>
    </div>
</section>
<section class="shop-content">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <c:choose>
                    <c:when test="${listCart.size()>0}">
                        <f:form method="GET" action="${pageContext.request.contextPath}/food/cart/updateQuantity">
                            <table class="cart-table table table-bordered">
                                <thead>
                                <tr>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listCart}" var="cart">
                                    <tr>
                                        <td>
                                            <a href="" onclick="deleteToCart(${cart.product.product_id})" class="remove"><i class="fa fa-times"></i></a>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/food/productDetail?proId=${cart.product.product_id}"><img src="${cart.product.image}" alt="${cart.product.image}" height="90" width="90"></a>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/food/productDetail?proId=${cart.product.product_id}">${cart.product.product_name}</a>
                                        </td>
                                        <td>
                                <span class="amount">$${cart.product.price}
                                <c:if test="${cart.product.discount > 0}">(-${cart.product.discount}%)</c:if>
                                </span>
                                        </td>
                                        <td>
                                            <div class="quantity"><input type="number" style="width: 50px" value="${cart.quantity}" name="quantity"/></div>
                                        </td>
                                        <td>
                                            <span class="amount">$<fmt:formatNumber>${(cart.product.price*((100-cart.product.discount)/100))*cart.quantity}</fmt:formatNumber></span>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="6" class="actions">
                                        <div class="col-md-6 text-left">
                                            <div class="cart-btn" style="float: left !important;">
                                                    <%--                                        <c:if test="${listCart.size()>0}">--%>
                                                    <%--                                            <a class="btn btn-success" data-toggle="modal" data-target="#exampleService" >register Service</a>--%>
                                                    <%--                                        </c:if>--%>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="cart-btn">
                                                <c:if test="${listCart.size()>0}">
                                                    <button class="btn btn-warning" style="color: #0a0a0a" type="submit">Update Cart</button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </f:form>
                    </c:when>
                    <c:otherwise>
                        <div class="text-center " style="margin-bottom: 30px">
                            <p style="font-size: 100px"><i class="fas fa-shopping-cart"></i></p>
                            <p>Your cart is empty! Make a purchase to take the next step !</p>

                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="cart_totals">
                    <div class="col-md-6 push-md-6 no-padding">

                        <h4 class="text-left">Cart Totals:</h4>
                        <br>
                        <table class="table table-bordered col-md-6">
                            <tbody>
                                <tr>
                                    <th>Cart Subtotal</th>
                                    <td><span class="amount">${tongtien}$</span></td>
                                </tr>
                                <tr>
                                    <th>Shipping and Handling</th>
                                    <td>Free Shipping</td>
                                </tr>
                                <tr>
                                    <th>Order Total</th>
                                    <td><strong><span class="amount">${tongtien}$</span></strong> </td>
                                </tr>
                            </tbody>
                        </table>

                        <h4 class="text-left">Monthly Repeat Service:</h4>
                        <c:if test="${param.errorService !=null}">
                        <select class="form-control" id="selectServiceDetail" style="border: 1px solid red" name="service">
                            <option value="0" >-- Service Selection --</option>
                            <c:forEach items="${listServices}" var="service">
                                <option value="${service.service_id}">${service.service_name}</option>
                            </c:forEach>
                        </select>
                        </c:if>
                        <c:if test="${param.errorService ==null}">
                            <select class="form-control" id="selectServiceDetail"  name="service">
                                <option value="0" >-- Service Selection --</option>
                                <c:forEach items="${listServices}" var="service">
                                    <option value="${service.service_id}">${service.service_name}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                        <c:if test="${param.errorService !=null}">
                            <p style="color: red;font-size: 13px">${param.errorService}</p>
                        </c:if>
                        <hr>
                            <div class="col-md-12" style="padding: 0">
                                <div class="row">
<%--                                    <div class="col-md-6">--%>
<%--                                        <c:if test="${listCart.size()>0}">--%>
<%--                                            <a class="btn btn-success " style="color: #0a0a0a" href="${pageContext.request.contextPath}/food/checkout" >Checkout No Service</a>--%>
<%--                                        </c:if>--%>
<%--                                    </div>--%>
                                        <c:if test="${listCart.size()>0}">
                                            <div class="col-md-6 " id="buttonService"  >
                                                    <a class="btn btn-warning " style="color: #0a0a0a" href="${pageContext.request.contextPath}/food/service?service=0" >Checkout</a>
                                            </div>
                                        </c:if>
                                </div>
                            </div>
                    </div>
                    <div class="col-md-6" >
                        <div id="contentServiceInfo">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Modal -->
<%--<div class="modal fade" id="exampleService" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
<%--    <div class="modal-dialog" role="document">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="exampleModalLabel">Service</h5>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                <c:if test="${listServices.size()>0}">--%>
<%--                    <form action="${pageContext.request.contextPath}/food/service" method="GET" >--%>
<%--                        <div class="clearfix space20"></div>--%>
<%--                        <div class="col-12">--%>
<%--                            <label>Service:</label>--%>
<%--                            <select class="form-control" name="service">--%>
<%--                                <c:forEach items="${listServices}" var="service">--%>
<%--                                    <option value="${service.service_id}">${service.service_name}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </div>--%>
<%--                        <div class="text-left top-space-lg">--%>
<%--                            <button type="submit" class="btn btn-success btn-sm">Play Now</button>--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </c:if>--%>
<%--            </div>--%>
<%--            <div class="modal-footer">--%>
<%--                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
<script>
    $(function (){
       $("select").change(getServiceInfo);
       getServiceInfo();
        getButtonService();
    });
    function deleteToCart(_product_id) {
        $.ajax({
            method:'GET',
            url:'/food/cart/deleteCart?product_id='+_product_id,
            success:function (res) {
                $("body").html(res);
            }
        })
    }

    function getServiceInfo()
    {
        var _serviceId = $("#selectServiceDetail").val();
        $.ajax({
            method: 'GET',
            url: '/food/service/serviceInfo?service_id='+_serviceId,
            success:function (res)
            {
                $("#contentServiceInfo").html(res);
            }
        })
        $.ajax({
            method: 'GET',
            url: '/food/service/buttonRegisterService?service_id='+_serviceId,
            success:function (res)
            {
                $("#buttonService").html(res);
            }
        })

    }

    function getButtonService()
    {
        var _serviceId = $("#selectServiceDetail").val();
        $.ajax({
            method: 'GET',
            url: '/food/service/buttonRegisterService?service_id='+_serviceId,
            success:function (res)
            {
                $("#buttonService").html(res);
            }
        })
    }
</script>
<%@include file="/WEB-INF/views/layout/frontend/ajaxScript.jsp" %>
</body>
</html>
