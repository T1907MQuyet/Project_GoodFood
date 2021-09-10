
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@include file="/WEB-INF/views/layout/frontend/header.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/navbar.jsp" %>
<section class="page_header" style="background: url('/assets/frontend/img/bg3-1.png')">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="text-uppercase wow fadeInDown">Information Page</h2>
            </div>
        </div>
    </div>
</section>

<div class="blog-content">
    <div class="container">
        <div class="row wow fadeInUp">
            <aside class="col-md-3">
                <div class="side-widget">

                </div>
                <div class="side-widget">
                    <h5>Categories</h5>
                    <ul class="side-cat">
                        <li><i class="fa fa-chevron-right"></i><a href="${pageContext.request.contextPath}/food/news" >All</a></li>
                        <c:forEach items="${listType}" var="type">
                            <li><i class="fa fa-chevron-right"></i> <a href="${pageContext.request.contextPath}/food/news/type?type_id=${type.type_new_id}" <c:if test="${param.type_id==type.type_new_id}">style="color:#dead5a"</c:if> >${type.type_name}</a></li>

                        </c:forEach>
                    </ul>
                </div>

            </aside>
            <div class="col-md-9">
                <div class="shop-grid">
                    <span>Showing 1-10 of ${list.size()} Results</span>
                </div>
                <c:forEach items="${list}" var="news">
                    <article>
                        <div class="post-img">
                            <div class="blog-slider slick-initialized slick-slider" role="toolbar">
                                <div aria-live="polite" class="slick-list draggable">
                                    <img src="${news.imageLink}" style=" width: 100%;height: 400px" class="img-responsive" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sm-12 ">
                                <h4><a href="${pageContext.request.contextPath}/food/news/detail?id=${news.news_id}" style="font-size: 15px;padding: 0;margin: 0">${news.title_name}</a></h4>
                            </div>
                            <div class="col-md-3 col-sm-3">
                                <div class="post-date" style="float: left;">${news.type_news.type_name}</div>
                            </div>
                        </div>
                        <hr>
                        <span>${news.title_content}</span>
                        <a href="${pageContext.request.contextPath}/food/news/detail?id=${news.news_id}" class="btn btn-default">Read More</a>
                    </article>
                </c:forEach>

                <div class="clearfix"></div>
                <ul class="pagi_nation">
                    <c:if test="${totalPages > 1}">
                        <c:if test="${currentPage>1}">
                            <li class="page-item"><a class="page-link" href="/food/news/type/page/${currentPage-1}?type_id=${type_id}">«</a></li>
                        </c:if>
                        <c:forEach  end="${totalPages}" begin="1" varStatus="loop">
                            <c:if test="${currentPage != loop.index}">
                                <li class="page-item "><a class="page-link" href="/food/news/type/page/${loop.index}?type_id=${type_id}">${loop.index}</a></li>
                            </c:if>
                            <c:if test="${currentPage == loop.index}">
                                <li class="page-item active"><a class="page-link" href="/food/news/type/page/${loop.index}?type_id=${type_id}">${loop.index}</a></li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${currentPage<totalPages}">
                            <li class="page-item"><a class="page-link" href="/food/news/type/page/${currentPage+1}?type_id=${type_id}">»</a></li>
                        </c:if>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/ajaxScript.jsp" %>

