<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@include file="/WEB-INF/views/layout/frontend/header.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/navbar.jsp" %>
<section class="page_header">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="text-uppercase">Checkout Service</h2>
            </div>
        </div>
    </div>
</section>
<section class="shop-content">
    <div class="container">
        <c:if test="${listCart.size()>0 && loginCustomer!=null}">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="billing-details">
                                <h3 class="text-left">customer information</h3>
                                <br>
                                    <%--@elvariable id="customerService" type=""--%>
                            <f:form modelAttribute="customerService" action="" method="post">
                                <f:input path="customer.customer_id"  type="hidden" value="${loginCustomer.customer_id}"/>
                                <f:input path="services.service_id" type="hidden" value="${service.service_id}"/>
                                <div class="clearfix space20"></div>
                                    <label>Email:</label>
                                    <input class="form-control "  name="email" readonly="readonly" placeholder="Recipient's email" value="${loginCustomer.email}" type="text"/>
                                <div class="clearfix space20"></div>
                                <spring:bind path="received_address">
                                    <label>Address:</label>
                                    <f:input class="form-control ${status.error ?'border border-danger':''}" path="received_address" placeholder="Street address" value="" type="text"/>
                                    <f:errors path="received_address" class="text-danger"  ></f:errors>
                                </spring:bind>
                                <div class="clearfix space20"></div>
                                <label>Received date:</label>
                                    <f:select class="form-control" path="received_date">
                                        <f:option value="0">Right Day</f:option>
                                        <f:option value="1">Before 1 Day</f:option>
                                        <f:option value="2">Before 2 Day</f:option>
                                        <f:option value="3">Before 3 Day</f:option>
                                    </f:select>
                                <div class="clearfix space20"></div>
                                <spring:bind path="received_phone_number">
                                    <label>Phone:</label>
                                    <f:input class="form-control inputDate ${status.error ?'border border-danger':''}" path="received_phone_number" id="billing_phone" placeholder="Recipient's phone" type="number"/>
                                    <f:errors path="received_phone_number" class="text-danger"  ></f:errors>
                                </spring:bind>
                                <div class="clearfix space20"></div>
                                <spring:bind path="note">
                                    <label>Note:</label>
                                    <f:textarea class="form-control" path="note" placeholder="Notes about your order, e.g. special notes for delivery." rows="4" cols="5"></f:textarea>
                                    <f:errors path="note" class="text-danger"  ></f:errors>
                                </spring:bind>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <h4 class="text-left">Your Service</h4>
                            <br>
                            <table class="table table-bordered extra-padding">
                                <tbody>
                                <tr>
                                    <th>Type Service</th>
                                    <td><span class="amount">${service.service_name}</span></td>
                                </tr>
                                <tr>
                                    <th>Service product list</th>
                                    <td><span class="amount"><a data-toggle="modal" data-target="#exampleModal1" class=" badge-primary"  href="javascript:void(0);">Car information</a></span></td>
                                </tr>
                                <tr>
                                    <td colspan="2">${service.description}</td>
                                </tr>
                                <tr>
                                    <th>Solar calendar</th>
                                    <th>Lunar calendar</th>
                                </tr>
                                <c:forEach var="calender" items="${listCalender}">
                                    <tr>
                                        <td><fmt:formatDate value="${calender.solar_calendar}" pattern="dd-MM-yyyy" /></td>
                                        <td><fmt:formatDate value="${calender.lunar_calendar}" pattern="dd-MM-yyyy" /></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="text-left top-space-lg"><button type="submit"  class="btn btn-default btn-lg">Register Now</button>
                        <a class="btn btn-danger btn-lg" href="${pageContext.request.contextPath}/food/cart/listCart">Cancel</a>
                    </div>
                </div>
                </f:form>
            </div>
        </c:if>
        <c:if test="${listCart.size()==0 }">
            <div class="text-center " style="margin-bottom: 30px">
                <p style="font-size: 100px"><i class="fas fa-shopping-cart"></i></p>
                <p>Your cart is empty! Make a purchase to take the next step ! <span><a href="${pageContext.request.contextPath}/food/menu" style="text-decoration: underline">Order Now</a></span></p>

            </div>
        </c:if>
        <c:if test="${ loginCustomer==null}">
            <p class="text-center">Please login to purchase</p>
        </c:if>
    </div>
</section>
<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Order information</h5>
            </div>
            <div id="contentOrderDetail" class="modal-body">
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
                            <td><strong><span class="amount">${tongtien}$</span></strong></td>
                        </tr>
                    </tbody>
                </table>
                <table class="cart-table table table-bordered">
                    <thead>
                    <tr>
                        <th>&nbsp;</th>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listCart}" var="cart">
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/food/productDetail?proId=${cart.product.product_id}"><img src="${cart.product.image}" alt="${cart.product.image}" height="90" width="90"></a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/food/productDetail?proId=${cart.product.product_id}">${cart.product.product_name} (x${cart.quantity})</a>
                            </td>
                            <td>
                                <span class="amount">$${cart.product.price}
                                <c:if test="${cart.product.discount > 0}">(-${cart.product.discount}%)</c:if>
                                </span>
                            </td>
                            <td>
                                <span class="amount">$<fmt:formatNumber>${(cart.product.price*((100-cart.product.discount)/100))*cart.quantity}</fmt:formatNumber></span>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/ajaxScript.jsp" %>
