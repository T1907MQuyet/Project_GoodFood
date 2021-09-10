<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>



<c:forEach items="${listProduct}" var="product" varStatus="itr">
    <tr>
        <td>${offset+itr.index+1}
        </td>
        <td><span class="text-bold">name:</span> ${product.product_name}
            <br>
            <span class="text-bold">Price:</span> <fmt:formatNumber>${product.price}</fmt:formatNumber> $
            <br>
            <span class="text-bold">Category:</span> ${product.category_detail.cate_detail_name}
        </td>
        <td><img src="${product.image}" width="50"></td>
        <td class="project-actions ">
            <a href="${pageContext.request.contextPath}/admin/menuDetail/insertProMenuDetail?product_id=${product.product_id}&&menuD_id=${proMNId}" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>Move to menu</a>
<%--            <a href="##" onclick="addProductMenuDetail(${product.product_id},${proMNId})" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>Move to menu</a>--%>
        </td>
    </tr>
</c:forEach>
