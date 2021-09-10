<%--
  Created by IntelliJ IDEA.
  User: asuspc
  Date: 4/27/2021
  Time: 2:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@include file="/WEB-INF/views/layout/frontend/header.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/navbar.jsp" %>

<section class="page_header" style="background: url('/assets/frontend/img/bg4-1.png')">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="text-uppercase">Detail</h2>
            </div>
        </div>
    </div>
</section>

<section class="recipie-single single-recipe">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="single-recipe-image">
                    <img class="img-responsive" src="${product.image}" alt="">
                </div>
            </div>
            <div class="col-md-6">
                <h3>${product.product_name}</h3>
<%--                <div class="ingredients" style="height: auto !important;">--%>

<%--                </div>--%>
                <div class="rc-ratings" style="float: left !important;">
                    <span class="fa fa-star active"></span>
                    <span class="fa fa-star active"></span>
                    <span class="fa fa-star active"></span>
                    <span class="fa fa-star active"></span>
                    <span class="fa fa-star"></span>
                </div>
                <br>
                <h4 class="title " style="margin-top: 25px;">Price: ${product.price}$</h4>
                <div class="text-left top-space-sm">
                    <a href="${pageContext.request.contextPath}/food/menu" class="btn btn-default btn-sm">menu today</a>
                </div>
            </div>
            <div class="col-md-12">
                ${product.descriptions}
            </div>
        </div>
    </div>
</section>

<section class="featured-recipie">
    <div class="container">
        <hr>
        <h3>Featured Recipe</h3>
        <div class="row">
            <div class="featured-recipies">
                <c:forEach var="pro" items="${listProPriority}">
                <div class="fp-content">
                    <a href="${pageContext.request.contextPath}/food/productDetail?proId=${pro.product_id}"><img src="${pro.image}" class="img-responsive" alt="" style="height: 150px;" /></a>
                    <h4><a href="${pageContext.request.contextPath}/food/productDetail?proId=${pro.product_id}">${pro.product_name}</a></h4>
                    <div class="rc-ratings">
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star active"></span>
                        <span class="fa fa-star active"></span>
                        <span class="fa fa-star active"></span>
                        <span class="fa fa-star active"></span>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>





<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
</body>
</html>
