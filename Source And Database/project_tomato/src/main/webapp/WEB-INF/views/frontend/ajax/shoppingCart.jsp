<%--
  Created by IntelliJ IDEA.
  User: asuspc
  Date: 4/28/2021
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" scope="request" value="Home"/>

<a class="css-pointer dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-shopping-cart fsc pull-left"></i><span class="cart-number">${listCart.size()}</span><span class="caret"></span></a>
<div class="cart-content dropdown-menu" style="display: none;">
    <div class="cart-title">
        <h4>Shopping Cart</h4>
    </div>
    <div class="cart-items" >
        <c:forEach items="${listCart}" var="cart">
            <div class="cart-item clearfix">
                <div class="cart-item-image">
                    <a href="shop_single_full.html"><img style="width: 50px" src="${cart.product.image}" alt="Breakfast with coffee"></a>
                </div>
                <div class="cart-item-desc">
                    <a href="shop_single_full.html">${cart.product.product_name}</a>
                    <span class="cart-item-price">$${(cart.product.price - (cart.product.price * cart.product.discount)/100)}</span>
                    <span class="cart-item-quantity">x ${cart.quantity}</span>
                    <i class="fa fa-times ci-close"  onclick="deleteToCart(${cart.product.product_id})"></i>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="cart-action clearfix">
        <span class="pull-left checkout-price">$ ${tongtien}</span>
        <a class="btn btn-default pull-right" href="${pageContext.request.contextPath}/food/cart/listCart">View Cart</a>
    </div>
</div>
