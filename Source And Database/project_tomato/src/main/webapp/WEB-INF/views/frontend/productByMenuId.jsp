<%@ page import="tomato_th.project_tomato.controller.frontend.FrontendController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@include file="/WEB-INF/views/layout/frontend/header.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/navbar.jsp" %>

<section class="page_header" style="background: url('/assets/frontend/img/bg3-1.png')">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="text-uppercase">Order Now</h2>
            </div>
        </div>
    </div>
</section>

<div class="shop-content">
    <div class="container">
        <div class="row">
            <aside class="col-md-3">
                <div class="side-widget">
                    <c:forEach var="menu" items="${listMenu}">
                        <h5 style="margin-top: 20px">${menu.menu_name}</h5>
                        <ul class="shop-cat">
                            <c:forEach items="${listMenuDetail}" var="menuDetail">
                                <c:if test="${menuDetail.menu.menu_id == menu.menu_id}">
                                    <c:if test="${param.menuDetailId==menuDetail.menu_detail_id}">
                                        <li><a href="${pageContext.request.contextPath}/food/menuProInMenu?menuDetailId=${menuDetail.menu_detail_id}" style="color: orange">${menuDetail.menu_detail_name} <i class="fa fa-caret-right"></i></a></li>
                                    </c:if>
                                    <c:if test="${param.menuDetailId!=menuDetail.menu_detail_id}">
                                    <li><a href="${pageContext.request.contextPath}/food/menuProInMenu?menuDetailId=${menuDetail.menu_detail_id}" >${menuDetail.menu_detail_name} <i class="fa fa-caret-right"></i></a></li>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </c:forEach>

                </div>

            </aside>
            <div class="col-md-9">
                <div class="shop-grid">

                    <span>Showing 1-10 of ${listProMenuDetail.size()} Results</span>
                </div>
                <div class="shop-products">
                    <div class="row">
                        <c:forEach var="pro" items="${listProMenuDetail}">
                            <div class="col-md-4 col-sm-6">
                                <div class="product-info">
                                    <div class="product-img">
                                        <img src="${pro.product.image}" style="width: 100%;height: 100%" alt="${pro.product.image}">
                                    </div>
                                    <h4><a href="${pageContext.request.contextPath}/food/productDetail?proId=${pro.product.product_id}">${pro.product.product_name}<c:if test="${pro.product.discount > 0}"><span style="font-size: 14px">(-${pro.product.discount}%)</span> </c:if></a></h4>
                                    <div class="rc-ratings">
                                        <span class="fa fa-star active"></span>
                                        <span class="fa fa-star active"></span>
                                        <span class="fa fa-star active"></span>
                                        <span class="fa fa-star active"></span>
                                        <span class="fa fa-star"></span>
                                    </div>
                                   
                                    <div class="product-price">$${(pro.product.price - (pro.product.price * pro.product.discount)/100)}</div>
                                    <div class="shop-meta">
<%--                                        <a href="${pageContext.request.contextPath}/food/cart/addCart?productId=${pro.product.product_id}" class="pull-left"><i class="fa fa-shopping-cart"></i> Add to cart</a>--%>
<%--    <a  href="##" class="pull-left pointer"><i class="fa fa-shopping-cart"></i> Add to cart</a>--%>
                                        <a onclick="addToCart(${pro.product.product_id})" href="javascript:void(0);" class="pull-left pointer"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="card-footer clearfix">
                    <ul class="pagination pagination-sm m-0 float-right">
                        <c:if test="${totalPages > 1}">
                            <c:if test="${currentPage>1}">
                                <li class="page-item"><a class="page-link" href="/food/menuProInMenu/page/${currentPage-1}?menuDetailId=${menuDetailId}">«</a></li>
                            </c:if>
                            <c:forEach  end="${totalPages}" begin="1" varStatus="loop">
                                <c:if test="${currentPage != loop.index}">
                                    <li class="page-item "><a class="page-link" href="/food/menuProInMenu/page/${loop.index}?menuDetailId=${menuDetailId}">${loop.index}</a></li>
                                </c:if>
                                <c:if test="${currentPage == loop.index}">
                                    <li class="page-item active"><a class="page-link" href="/food/menuProInMenu/page/${loop.index}?menuDetailId=${menuDetailId}">${loop.index}</a></li>
                                </c:if>
                            </c:forEach>
                            <c:if test="${currentPage<totalPages}">
                                <li class="page-item"><a class="page-link" href="/food/menuProInMenu/page/${currentPage+1}?menuDetailId=${menuDetailId}">»</a></li>
                            </c:if>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>



<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/ajaxScript.jsp" %>