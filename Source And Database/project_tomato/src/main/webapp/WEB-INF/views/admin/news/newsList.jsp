<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="News management"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>News management</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">News management</li>
                    </ol>
                </div>
            </div>
<%--            <div class="row">--%>
<%--                <div class="col-md-12">--%>
<%--                    <div class="card card-warning collapsed-card">--%>
<%--                        <div class="card-header">--%>
<%--                            <h3 class="card-title">Look Up Products</h3>--%>

<%--                            <div class="card-tools">--%>

<%--                                <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-plus"></i>--%>
<%--                                </button>--%>

<%--                                <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i>--%>
<%--                                </button>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <!-- /.card-header -->--%>
<%--                        <div class="card-body" style="display: none; color: #FFF">--%>
<%--                            <div class="col-md-12" style="margin-top: 10px;margin-bottom: 10px;">--%>
<%--                                <div class="row">--%>
<%--                                    <div class="col-md-3 col-sm-6 col-12">--%>
<%--                                        <a href="${pageContext.request.contextPath}/admin/product" class="btn btn-block btn-primary">All</a>--%>
<%--                                    </div>--%>
<%--                                    <div class="col-md-3 col-sm-6 col-12">--%>
<%--                                        <a href="${pageContext.request.contextPath}/admin/product/show" class="btn btn-block btn-success">Show</a>--%>
<%--                                    </div>--%>
<%--                                    <div class="col-md-3 col-sm-6 col-12">--%>
<%--                                        <a  href="${pageContext.request.contextPath}/admin/product/hidden" class="btn btn-block btn-danger">Hidden</a>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>

<%--                        <!-- /.card-footer-->--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="col-12">
                    <div class="row">
                        <div class="col-2">
                            <a class="btn btn-block btn-info btn-sm" href="${pageContext.request.contextPath}/admin/news/insertNews">Insert News</a>
                        </div>
                    </div>
                </div>

        </div><!-- /.container-fluid -->
    </section>
    <div class="container-fluid">
        <div class="row">

            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">News List</h3>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body p-0">
                        <table class="table table-striped projects">
                            <thead>
                            <tr>
                                <th style="width: 10px">STT</th>
                                <th>News</th>
                                <th>Type</th>
                                <th>Image</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="news" varStatus="itr">
                                <tr>
                                    <td>${offset+itr.index+1}
                                    </td>
                                    <td width="50%">${news.title_name}
                                        <c:if test="${news.status ==1}">
                                            <span class="text-bold">status:</span><span class="badge badge-success">Show</span>
                                        </c:if>
                                        <c:if test="${news.status !=1}">
                                            <span class="text-bold">status:</span><span class="badge badge-danger">Hidden</span>
                                        </c:if>
                                        <br><span class="text-bold">created:</span> ${news.created}
                                    </td>
                                    <td>${news.type_news.type_name}</td>
                                    <td><img src="${news.imageLink}" width="100"></td>
                                    <td class="project-actions ">
                                        <a data-toggle="modal" data-target="#exampleModal1"  onclick="detailNews(${news.news_id})" class="btn btn-primary btn-sm text-white"><i class="fas fa-folder"> </i></a>
                                        <a href="${pageContext.request.contextPath}/admin/news/editNews?id=${news.news_id}" class="btn btn-info btn-sm"><i class="fas fa-pencil-alt"> </i></a>
                                        <a href="${pageContext.request.contextPath}/admin/news/deleteNews?id=${news.news_id}" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.card-body -->
                    <div class="card-footer clearfix">
                        <ul class="pagination pagination-sm m-0 float-right">
                            <c:if test="${totalPages > 1}">
                                <c:if test="${currentPage>1}">
                                    <li class="page-item"><a class="page-link" href="/admin/news/page/${currentPage-1}">«</a></li>
                                </c:if>
                                <c:forEach  end="${totalPages}" begin="1" varStatus="loop">
                                    <c:if test="${currentPage != loop.index}">
                                        <li class="page-item "><a class="page-link" href="/admin/news/page/${loop.index}">${loop.index}</a></li>
                                    </c:if>
                                    <c:if test="${currentPage == loop.index}">
                                        <li class="page-item active"><a class="page-link" href="/admin/news/page/${loop.index}">${loop.index}</a></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${currentPage<totalPages}">
                                    <li class="page-item"><a class="page-link" href="/admin/news/page/${currentPage+1}">»</a></li>
                                </c:if>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <!-- /.card -->
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add Product for menu</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <label>Category:</label>

                <table class="table table-striped projects">
                    <thead>
                    <tr>
                        <th style="width: 10px">STT</th>
                        <th>Product Name</th>
                        <th>Image</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody id="allProduct">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>
<script>

    function detailNews(_id) {
        //alert(_id);
        $.ajax({
            method:'GET',
            url:'${pageContext.request.contextPath}/admin/news/detailNews?id='+_id,
            success:function (res) {
                $("#exampleModal1").html(res);
            }
        })
    }

</script>
</body>
</html>



