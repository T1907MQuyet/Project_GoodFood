<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<c:forEach items="${listProMD}" var="proMD" varStatus="itr">
    <tr>
        <td>${offset+itr.index+1}
        </td>
        <td>${proMD.product.product_name}</td>
        <td>${proMD.product.category_detail.cate_detail_name}</td>
        <td><fmt:formatNumber>${proMD.product.price}</fmt:formatNumber> $</td>
        <td><img src="${proMD.product.image}" width="50"></td>
        <td class="project-actions ">
            <a href="${pageContext.request.contextPath}/admin/menuDetail/removeProMD?id=${proMD.product_menu_detail_id}&&menuD_id=${menu_detail.menu_detail_id}" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> Remove</a>
        </td>
    </tr>
</c:forEach>
