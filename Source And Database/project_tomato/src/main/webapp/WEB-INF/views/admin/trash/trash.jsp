<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Category Manager"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>

<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Trash Manager</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">Trash Manager</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-12 col-sm-6 col-md-3">
                <div class="info-box " style="cursor: pointer" onclick="getCategory()" data-toggle="modal" data-target="#exampleModal1">
                    <span class="info-box-icon bg-info elevation-1"><i class="fas fa-th"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text" >Category</span>
                        <span class="info-box-number">${cateTrash}
                    </span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-12 col-sm-6 col-md-3">
                <div class="info-box mb-3" style="cursor: pointer" onclick="getCategoryDetail()" data-toggle="modal" data-target="#exampleModal1">
                    <span class="info-box-icon bg-danger elevation-1"><i class="far fa-circle"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Category Detail</span>
                        <span class="info-box-number">${cateDetailTrash}</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->

            <!-- fix for small devices only -->
            <div class="clearfix hidden-md-up"></div>

            <div class="col-12 col-sm-6 col-md-3">
                <div class="info-box mb-3" style="cursor: pointer" onclick="getMenu()" data-toggle="modal" data-target="#exampleModal1">
                    <span class="info-box-icon bg-success elevation-1"><i class="fas fa-angle-left"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Menu</span>
                        <span class="info-box-number">${menuTrash}</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-12 col-sm-6 col-md-3">
                <div class="info-box mb-3" style="cursor: pointer" onclick="getMenuDetail()" data-toggle="modal" data-target="#exampleModal1">
                    <span class="info-box-icon bg-warning elevation-1"><i class="far fa-circle"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Menu Detail</span>
                        <span class="info-box-number">${menuDetailTrash}</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->
        </div>
        <div class="row">
            <div class="col-12 col-sm-6 col-md-3">
                <div class="info-box" style="cursor: pointer" onclick="getProduct()" data-toggle="modal" data-target="#exampleModal1">
                    <span class="info-box-icon bg-purple elevation-1"><i class="fas fa-table"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Product</span>
                        <span class="info-box-number">${productTrash}
                </span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <div class="col-12 col-sm-6 col-md-3">
                <div class="info-box mb-3" style="cursor: pointer" onclick="getServices()" data-toggle="modal" data-target="#exampleModal1">
                    <span class="info-box-icon bg-warning elevation-1"><i class="far fa-circle"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Services</span>
                        <span class="info-box-number">${serviceTrash}</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <div class="col-12 col-sm-6 col-md-3">
                <div class="info-box mb-3" style="cursor: pointer" onclick="getCalender()" data-toggle="modal" data-target="#exampleModal1">
                    <span class="info-box-icon bg-info elevation-1"><i class="fas fa-calendar-times"></i></span>

                    <div class="info-box-content">
                        <span class="info-box-text">Calender</span>
                        <span class="info-box-number">${calenderTrash}</span>
                    </div>
                    <!-- /.info-box-content -->
                </div>
                <!-- /.info-box -->
            </div>
            <!-- /.col -->

            <!-- /.col -->

            <!-- fix for small devices only -->
            <div class="clearfix hidden-md-up"></div>


            <!-- /.col -->
        </div>

    </div>
</div>
<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content content-trash" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add Product for menu</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body ">

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


        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>
<script>
    $(function () {
        getCategory();
        getCategoryDetail();
        getMenu();
        getMenuDetail();
        getProduct();
    })

    function getCategory() {
        $.ajax({
            method:'GET',
            url:'/admin/trash/category',
            success:function (res) {
                $(".content-trash").html(res);
            }
        })
    }
    function getCategoryDetail() {
        $.ajax({
            method:'GET',
            url:'/admin/trash/categoryDetail',
            success:function (res) {
                $(".content-trash").html(res);
            }
        })
    }
    function getMenu() {
        $.ajax({
            method:'GET',
            url:'/admin/trash/menu',
            success:function (res) {
                $(".content-trash").html(res);
            }
        })
    }
    function getMenuDetail() {
        $.ajax({
            method:'GET',
            url:'/admin/trash/menuDetail',
            success:function (res) {
                $(".content-trash").html(res);
            }
        })
    }
    function getProduct() {
        $.ajax({
            method:'GET',
            url:'/admin/trash/product',
            success:function (res) {
                $(".content-trash").html(res);
            }
        })
    }
    function getServices() {
        $.ajax({
            method:'GET',
            url:'/admin/trash/services',
            success:function (res) {
                $(".content-trash").html(res);
            }
        })
    }
    function getCalender() {
        $.ajax({
            method:'GET',
            url:'/admin/trash/calender',
            success:function (res) {
                $(".content-trash").html(res);
            }
        })
    }
</script>
</body>
</html>