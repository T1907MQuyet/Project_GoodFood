<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body data-scroll-animation="true">
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<div class="preloder animated">
    <div class="scoket">
        <img src="/assets/frontend/img/preloader.svg" alt="" />
    </div>
</div>
<div class="body">
    <div class="main-wrapper">
        <nav class="navbar navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                        <img src="/assets/frontend/img/nav-logo2.png" alt="nav-logo">
                    </a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="${pageContext.request.contextPath}/"  role="button" aria-haspopup="true" aria-expanded="false">Home</a>
                        </li>
                        <li class="dropdown">
                            <a href="${pageContext.request.contextPath}/food/menu"  role="button" aria-haspopup="true" aria-expanded="false">ORDER NOWS</a>
                        </li>
                        <li class="dropdown">
                            <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Products<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <c:forEach var="c" items="${listCate}">
                                <li ><a href="javascript:void(0)">${c.cate_name}<span class="caret-right"></span></a>
                                    <ul class="dropdown-menu">
                                        <c:forEach var="cd" items="${listCateDetail}">
                                            <c:if test="${c.cate_id == cd.category.cate_id}">
                                                <li><a href="${pageContext.request.contextPath}/food/getProByCate?cateDetail_id=${cd.cate_detail_id}">${cd.cate_detail_name}</a></li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </li>
                                </c:forEach>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="${pageContext.request.contextPath}/food/news"  role="button" aria-haspopup="true" aria-expanded="false">News</a>
                        </li>
                        <li class="dropdown">
                            <a href="${pageContext.request.contextPath}/food/feedback"  role="button" aria-haspopup="true" aria-expanded="false">Feedback</a>
                        </li>
                        <li class="dropdown">
                            <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Account<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <c:if test="${loginCustomer==null}">
                                    <li><a href="${pageContext.request.contextPath}/food/login">Login</a></li>
                                    <li><a href="${pageContext.request.contextPath}/food/register">Register</a></li>
                                </c:if>
                                <c:if test="${loginCustomer!=null}">
                                    <li><a href="${pageContext.request.contextPath}/food/account?customer_id=${loginCustomer.customer_id}">${loginCustomer.email}</a></li>
                                    <li><a href="${pageContext.request.contextPath}/food/logout">Logout</a></li>

                                </c:if>

                            </ul>
                        </li>
                        <li class="dropdown" id="cartItemNav">
                            <a class="css-pointer dropdown-toggle" href="javascript:void(0);" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-shopping-cart fsc pull-left"></i>
                                <span class="cart-number">${listCart.size()} <c:if test="${listCart.size()==null}">0</c:if></span><span class="caret"></span></a>
                            <div class="cart-content dropdown-menu" style="display: none;">
                                <div class="cart-title">
                                    <h4>Shopping Cart</h4>
                                </div>
                                <div class="cart-items" >
                                    <c:forEach items="${listCart}" var="cart">
                                    <div class="cart-item clearfix">
                                        <div class="cart-item-image">
                                            <a href="javascript:void(0);"><img style="width: 50px" src="${cart.product.image}" alt="Breakfast with coffee"></a>
                                        </div>
                                        <div class="cart-item-desc">
                                            <a href="${pageContext.request.contextPath}/food/productDetail?proId=${cart.product.product_id}">${cart.product.product_name}</a>
                                            <span class="cart-item-price">$ ${(cart.product.price - (cart.product.price * cart.product.discount)/100)}</span>
                                            <span class="cart-item-quantity">x ${cart.quantity}</span>
                                            <i class="fa fa-times ci-close" onclick="deleteToCart(${cart.product.product_id})"></i>
                                        </div>
                                    </div>
                                    </c:forEach>
                                </div>
                                <div class="cart-action clearfix">
                                    <span class="pull-left checkout-price">$ ${tongtien}</span>
                                    <c:if test="${listCart.size()>0}">
                                        <a class="btn btn-default pull-right" href="${pageContext.request.contextPath}/food/cart/listCart">View Cart</a>
                                    </c:if>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>


