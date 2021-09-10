<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="../../layout/admin/header.jsp" %>
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
    </div><!-- /.container-fluid -->
</section>
<%--@elvariable id="newEdit" type=""--%>
<f:form action="${pageContext.request.contextPath}/admin/news/updateNews" method="POST" enctype="multipart/form-data" modelAttribute="newEdit">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-10">
                <div class="card card-info">
                    <div class="card-header">
                        <h3 class="card-title">Update News!</h3>
                    </div>
                    <div class="card-body">
                        <div class="col-md-12">
                            <div class="row">
                                <f:input path="news_id" type="hidden"/>
                                <f:input path="created" type="hidden"/>
                                <f:input path="updated" type="hidden"/>
                                    <div class="form-group col-md-6">
                                        <label for="exampleInputEmail1">Title Name:</label>
                                        <f:textarea path="title_name" style="height:500px" type="text" class="form-control" id="titleName" placeholder="Enter Open Time"/>
                                        <f:errors path="title_name" class="text-danger"  ></f:errors>
                                        <p class="text-danger">${param.errorcatename}</p>
                                    </div>
                                <div class="form-group col-md-6">
                                    <label>Type:</label>
                                    <f:select class="custom-select" path="type_news.type_new_id">
                                        <f:options items="${listType}"  itemLabel="type_name" itemValue="type_new_id" />
                                    </f:select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label>Home Page Active:</label>
                                    <f:select path="home_active" class="custom-select">
                                        <f:option value="1">No</f:option>
                                        <f:option value="2">Active</f:option>
                                    </f:select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label>Service:</label>
                                    <select name="service_id" class="custom-select">
                                        <option value="0">---  service selection  ---</option>
                                        <c:forEach items="${servicesList}" var="service">
                                        <option value="${service.service_id}" <c:if test="${service_news.services.service_id==service.service_id}">selected</c:if> >${service.service_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label >Title Content:</label>
                                    <f:textarea path="title_content" style="height:500px" type="text" class="form-control" id="titleContent" placeholder="Enter Open Time"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label >Content:</label>
                                    <f:textarea path="content" style="height:500px" type="text" class="form-control" id="content" placeholder="Enter Open Time"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label >File Image:</label>
                                    <input name="file_avatar" id="fileImage" type="file" class="form-control" placeholder="Nhập Giá Bán Sản Phẩm"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label for="exampleInputEmail1">Status:</label>
                                    <div class="form-check">
                                        <f:radiobutton path="status" value="1"  class="form-check-input" checked="true" id="exampleInputEmail1"/>
                                        <label class="form-check-label" >Show</label>
                                        <br>
                                        <f:radiobutton path="status" value="2"  class="form-check-input" id="exampleInputEmail1"/>
                                        <label class="form-check-label" >Hidden</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <button type="submit" class="btn btn-info">Update</button>
                    <a href="${pageContext.request.contextPath}/admin/news" class="btn btn-warning text-white"><i class="fas fa-arrow-left" ></i> Back</a>
                </div>
            </div>
        </div>
    </div>
    </div>
</f:form>
</div>
<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>
<script type="text/javascript">
    $(function () {
        // Summernote
        $('#titleName').summernote();
        $('#titleContent').summernote();
        $('#content').summernote();
        click();
    })
</script>
</body>
</html>