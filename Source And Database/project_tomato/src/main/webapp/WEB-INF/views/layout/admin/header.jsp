<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle}</title>
 <!-- Font Awesome -->
  <link rel="stylesheet" href="<c:url value="/assets/plugins/fontawesome-free/css/all.min.css" />">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- overlayScrollbars -->
    <link rel="stylesheet" href="/assets/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  <link rel="stylesheet" href="/assets/plugins/fontawesome-free/css/all.min.css">
  <link rel="stylesheet" href="/assets/editor/richtext.min.css">

    <link rel="stylesheet" href="/assets/plugins/summernote/summernote-bs4.css">
    <style>
        .infoTextSize {
            font-size: 14px;
        }
    </style>

</head>
<body>
	<div class="wrapper">
  <!-- Navbar -->
  	<%@include file="navbar.jsp" %>

  <!-- /.navbar -->
	<%@include file="navleft.jsp" %>
  <!-- Main Sidebar Container -->
  