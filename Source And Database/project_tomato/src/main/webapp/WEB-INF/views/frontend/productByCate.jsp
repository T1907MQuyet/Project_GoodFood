<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@include file="/WEB-INF/views/layout/frontend/header.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/navbar.jsp" %>
<section class="page_header" style="background: url('/assets/frontend/img/1.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="text-uppercase">Product Listing</h2>

            </div>
        </div>
    </div>
</section>
<section class="shop-content">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="shop-grid">
                    <div class="sg-list">
                        <p>List of names: ${category_detail.category.cate_name} - ${category_detail.cate_detail_name}</p>
                    </div>

                    <span>Showing 1-10 of ${listPro.size()} Results</span>
                </div>
                <div class="shop-products">
                    <div class="row">
                        <c:forEach items="${listPro}" var="pro">
                        <div class="col-md-4 col-sm-6">
                            <div class="product-info">
                                <div class="product-img">
                                    <img src="${pro.image}" alt="${pro.image}" style="width: 100%;height: 100%">
                                </div>
                                <h4><a href="${pageContext.request.contextPath}/food/productDetail?proId=${pro.product_id}">${pro.product_name}</a></h4>
                               <%

                               %>
                                <div class="product-price">$ ${(pro.price - (pro.price * pro.discount)/100)}</div>
                                <div class="shop-meta text-center">
                                    <span class="fa fa-star active"></span><a href="${pageContext.request.contextPath}/food/productDetail?proId=${pro.product_id}" class=" text-center">See details</a><span class="fa fa-star active"></span>
                                </div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>



<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/ajaxScript.jsp" %>
