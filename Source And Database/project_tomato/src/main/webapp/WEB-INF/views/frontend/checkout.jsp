<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@include file="/WEB-INF/views/layout/frontend/header.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/navbar.jsp" %>
<section class="page_header" style="background: url('/assets/frontend/img/bg3-1.png')">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="text-uppercase">Checkout</h2>
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
                            <h3 class="text-left">Billing Details</h3>
                            <br>
                            <%--@elvariable id="orderNew" type=""--%>
                            <f:form modelAttribute="orderNew" action="" method="post">
                                <f:input path="total_price"  type="hidden" value="${tongtien}"/>
                                <f:input path="customer.customer_id"  type="hidden" value="${loginCustomer.customer_id}"/>
                                <div class="clearfix space20"></div>
                                <div class="row">
                                    <spring:bind path="fullname">
                                    <div class="col-md-12">
                                        <label>Full Name: </label>
                                        <f:input path="fullname" class="form-control ${status.error ?'border border-danger':''}" placeholder="Recipient's name" value="${loginCustomer.username}" type="text"/>
                                        <f:errors path="fullname" class="text-danger"  ></f:errors>
                                    </div>
                                    </spring:bind>
                                </div>
                                <div class="clearfix space20"></div>
                                <spring:bind path="order_email">
                                        <label>Email:</label>
                                        <f:input class="form-control ${status.error ?'border border-danger':''}"  path="order_email" placeholder="Recipient's email" value="${loginCustomer.email}" type="text"/>
                                        <f:errors path="order_email" class="text-danger"  ></f:errors>
                                </spring:bind>
                                <div class="clearfix space20"></div>
                                <spring:bind path="order_address">
                                        <label>Address: </label>
                                        <f:input class="form-control ${status.error ?'border border-danger':''}" path="order_address" placeholder="Street address" value="" type="text"/>
                                        <f:errors path="order_address" class="text-danger"  ></f:errors>
                                </spring:bind>
                                <div class="clearfix space20"></div>
                                    <label>Received date:</label>
<%--                                    <f:input type="date" path="received_date" required="required" class="form-control inputDate"/>--%>
                                    <input type="date" name="date_received" required="required" class="form-control inputDate">

                                <div class="clearfix space20"></div>
                                <spring:bind path="phone_number">
                                        <label>Phone: </label>
                                        <f:input class="form-control ${status.error ?'border border-danger':''}" path="phone_number" id="billing_phone" placeholder="Recipient's phone" type="text"/>
                                        <f:errors path="phone_number" class="text-danger"  ></f:errors>
                                </spring:bind>
                                <div class="clearfix space20"></div>
                                <label>Payment: </label>
                                <f:select path="payment" class="form-control">
                                    <f:option value="payment delivery">payment delivery</f:option>
                                    <f:option value="online payment">online payment</f:option>
                                </f:select>
                                 <div class="clearfix space20"></div>
                                <spring:bind path="order_note">
                                    <label>Note: </label>
                                    <f:textarea class="form-control" path="order_note" placeholder="Notes about your order, e.g. special notes for delivery." rows="4" cols="5"></f:textarea>
                                    <f:errors path="order_note" class="text-danger"  ></f:errors>
                                </spring:bind>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h4 class="text-left">Your order</h4>
                        <br>
                        <table class="table table-bordered extra-padding">
                            <thead>
                            <tr>
                                <th>##</th>
                                <th>Product</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listCart}" var="cart" varStatus="itr">
                                <tr>
                                    <th>${offset+itr.index+1}</th>
                                    <td>
                                        <a style="color: #000" href="${pageContext.request.contextPath}/food/productDetail?proId=${cart.product.product_id}">${cart.product.product_name}</a>
                                    </td>
                                    <td>
                                        <div class="quantity">${cart.quantity}</div>
                                    </td>
                                    <td>
                                        <span class="amount">$<fmt:formatNumber>${(cart.product.price*((100-cart.product.discount)/100))*cart.quantity}</fmt:formatNumber></span>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <table class="table table-bordered extra-padding">
                            <tbody>
                            <tr>
                                <th>Cart Subtotal</th>
                                <td><span class="amount">${tongtien} $</span></td>
                            </tr>
                            <tr>
                                <th>Shipping and Handling</th>
                                <td>
                                    Free Shipping
                                </td>
                            </tr>
                            <tr>
                                <th>Order Total</th>
                                <td><strong><span class="amount">${tongtien} $</span></strong> </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                    <div class="text-left top-space-lg"><button type="submit"  class="btn btn-default btn-lg">Pay Now</button>
                        <a class="btn btn-danger btn-lg" href="${pageContext.request.contextPath}/food/cart/listCart">Cancel</a></div>
                </div>
            </f:form>
            </div>
    </c:if>
    <c:if test="${listCart.size()==0 }">
        <p class="text-center">The cart is empty</p>
    </c:if>
    <c:if test="${ loginCustomer==null}">
        <p class="text-center">Please login to purchase</p>
    </c:if>
    </div>
</section>
<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/ajaxScript.jsp" %>
