
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@include file="/WEB-INF/views/layout/frontend/header.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/navbar.jsp" %>
<%--        <section class="home" style="background: url('/assets/frontend/img/1.jpg')">--%>
        <section class="home" style="background: url('${BannerTop.image_links}');background-size: cover;">
            <div class="tittle-block">
                <div class="logo">
                    <a href="javascript:void(0)">
                        <img src="/assets/frontend/img/logo1.png" alt="logo">
                    </a>
                </div>
                <h1>worship items </h1>
                <h2>Provide worship items for everyone</h2>
            </div>
            <div class="scroll-down hidden-xs">
                <a href="javascript:void(0)">
                    <img src="/assets/frontend/img/arrow-down.png" alt="down-arrow">
                </a>
            </div>
        </section>
        <section class="about" id="about">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="page-header wow fadeInDown">
                            <h1>VIETNAMESE TRAY<small>A little about us and a breif history of how we started.</small></h1>
                        </div>
                    </div>
                </div>
                <div class="row wow fadeInUp">
                    <div class="col-md-4">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xs-12 hidden-sm about-photo">
                                    <div class="image-thumb">
                                        <img src="/assets/frontend/img/bg3-1.png" data-mfp-src="/assets/frontend/img/fullImages/pic1.jpg" class="img-responsive" alt="logo">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6 about-photo hidden-xs">
                                    <img src="/assets/frontend/img/thumb2.png" data-mfp-src="/assets/frontend/img/fullImages/pic2.jpg" class="img-responsive" alt="logo">
                                </div>
                                <div class="col-sm-6 about-photo hidden-xs">
                                    <img src="/assets/frontend/img/thumb1-1.jpg" data-mfp-src="/assets/frontend/img/fullImages/pic3.jpg" class="img-responsive" alt="logo">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <p>Vietnam is a country rich in history and traditions, dating back thousands of years and
                            instilled with a deep respect for the land, the sea and their ancestors. With a proundly
                            history and spririt of "Uống nước nhớ nguồn", every holiday and festival is a special for
                            the following generation to show the their grateful and pious for the ancestor.</p>
                        <br>
                        <p>We providing the tradition goods and worship foods for every family to celebrate the
                            sepcial events in years a very traditional and meaningful way.</p>
                        <img src="/assets/frontend/img/signature.png" alt="signature">
                    </div>
                </div>
            </div>
        </section>
        <section class="special" style="background: url('/assets/frontend/img/bg4-4.png'); background-size: cover;" >
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="page-header wow fadeInDown">
                            <h1 class="white">Service<small>Monthly order service.</small></h1>
                        </div>
                    </div>
                </div>
                <div class="row wow fadeInUp">
                    <div class="col-md-offset-1 col-md-10">
                        <div class="flexslider special-slider">

                            <div class="direction-nav hidden-sm">
                                <div class="next">
                                    <a><img src="/assets/frontend/img/right-arrow.png" alt="" draggable="false"></a>
                                </div>
                                <div class="prev">
                                    <a><img src="/assets/frontend/img/left-arrow.png" alt="" draggable="false"></a>
                                </div>
                            </div>
                            <div class="flex-viewport" style="overflow: hidden; position: relative;">
                                <ul class="slides" style="width: 1000%; transition-duration: 0s; transform: translate3d(-945px, 0px, 0px);z-index: 1">
                                    <c:forEach items="${customerServiceList}" var="service">
                                        <li class="clone" aria-hidden="true" style="width: 945px; float: left; display: block;">
                                            <div class="slider-img">
                                                <img src="/assets/frontend/img/bg4-1.png" style="height: 410px !important;" alt="" draggable="false">
                                            </div>
                                            <div class="slider-content">
                                                <div class="page-header">
                                                    <h1>${service.service_name}<small>Dịch vụ đặt lịch hàng tháng!</small></h1>
                                                </div>
                                                <p>${service.description}</p>
                                                <a class="btn btn-default" href="${pageContext.request.contextPath}/food/news/newsService?service_id=${service.service_id}" role="button">Detail</a>
                                                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/food/cart/listCart" role="button">Register Service</a>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="menu">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="page-header wow fadeInDown">
                            <h1>Menu list</h1>
                        </div>
                    </div>
                </div>
                <div class="food-menu wow fadeInUp">
                    <div class="row menu-items" style="position: relative; height: 470px;">
                        <c:forEach items="${listMenu}" var="menu">
                            <div class="menu-item col-sm-6 col-xs-12 starter  desserts" style=" left: 0px; top: 0px;">
                                <div class="clearfix menu-wrapper">
                                    <h4>${menu.menu_name}</h4>
                                    <div class="dotted-bg"></div>
                                </div>
                                <c:forEach items="${listMenuDetail}" var="menu_detail">
                                    <c:if test="${menu_detail.menu.menu_id == menu.menu_id}">
                                        <p>${menu_detail.menu_detail_name}</p>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="menu-btn">
                                <a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/food/menu" role="button">Order Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%--chuong trinh khuyen mai--%>
        <section class="features">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="page-header wow fadeInDown">
                            <h1 class="white">Promotions</h1>
                        </div>
                    </div>
                </div>
                <div class="row wow fadeInUp">
                    <c:forEach var="promotions" items="${listNewsPromotions}">
                        <div class="col-md-4 col-sm-6 mb-2" style="max-height: 540px">
                            <div class="features-tile" style="height: 400px">
                                <div class="features-img">
                                    <img src="${promotions.imageLink}" style="height: 200px" alt="${promotions.imageLink}" />
                                </div>
                                <div class="features-content">
                                    <div class="page-header">
                                        <a href="${pageContext.request.contextPath}/food/news/detail?id=${promotions.news_id}">${promotions.title_name}</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <%--tin tuc--%>
        <section class="trusted">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="page-header wow fadeInDown">
                            <h1 class="">News</h1>
                        </div>
                    </div>
                </div>
                <div class="row wow fadeInUp">
                    <c:forEach var="promotions" items="${listNews}">
                        <div class="col-md-4 col-sm-6 mb-2" style="max-height: 540px">
                            <div class="features-tile" style="height: 500px">
                                <div class="features-img">
                                    <img src="${promotions.imageLink}" style="height: 200px;width: 100%" alt="${promotions.imageLink}" />
                                </div>
                                <div class="features-content">
                                    <a href="${pageContext.request.contextPath}/food/news/detail?id=${promotions.news_id}">${promotions.title_name}</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
<%--        <section class="subscribe mt-3">--%>
<%--            <div class="container">--%>
<%--                <div class="row">--%>
<%--                    <div class="col-md-12">--%>
<%--                        <h1>Subscribe</h1>--%>
<%--                        <p>Get updates about new dishes and upcoming events</p>--%>
<%--                        <form class="form-inline" action="https://demo.web3canvas.com/themeforest/tomato/php/subscribe.php" id="invite" method="POST">--%>
<%--                            <div class="form-group">--%>
<%--                                <input class="e-mail form-control" name="email" id="address" type="email" placeholder="Your Email Address" required>--%>
<%--                            </div>--%>
<%--                            <button type="submit" class="btn btn-default">--%>
<%--                                <i class="fa fa-angle-right"></i>--%>
<%--                            </button>--%>
<%--                        </form>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </section>--%>
<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/ajaxScript.jsp" %>

