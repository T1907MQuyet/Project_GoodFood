
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
        <div class="row">
            <div class="col-md-12">
                <article>
                    ${news.title_name}
                    <div class="post-img "  style="width: 100%;">
                        <img src="${news.imageLink}" class="img-responsive " style="display: block;margin-left: auto;margin-right: auto;width: auto"  alt="${news.imageLink}">
                    </div>
                    ${news.title_content}
                    ${news.content}
                </article>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/ajaxScript.jsp" %>

