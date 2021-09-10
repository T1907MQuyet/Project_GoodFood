<%--
  Created by IntelliJ IDEA.
  User: asuspc
  Date: 4/28/2021
  Time: 5:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function () {

        <c:if test="${param.success != null}">
        Swal.fire({
            title: '${param.success} ',
            text: "Please confirm to continue!",
            icon: 'success',
        });
        </c:if>
        <c:if test="${param.error != null}">
        Swal.fire({
            title: '${param.error} ',
            text: "Please confirm to continue!",
            icon: 'error',
        });
        </c:if>
        <c:if test="${param.errorService != null}">
        Swal.fire({
            title: '${param.errorService} ',
            text: "Please confirm to continue!",
            icon: 'error',
        });
        </c:if>
    })
    function addToCart(_product_id) {
        $.ajax({
            method:'GET',
            url:'/food/cart/addCart?productId='+_product_id,
            success:function (res) {
                $("#cartItemNav").html(res);
                Toast.fire({
                    icon: 'success',
                    title: 'Add To Cart successfully'
                })
            }
        })
    }

    function  deleteToCart(_product_id) {
        $.ajax({
            method:'GET',
            url:'/food/cart/deleteCartAjax?product_id='+_product_id,
            success:function (res) {
                $("#cartItemNav").html(res);
                Toast.fire({
                    icon: 'success',
                    title: 'remove successfully'
                })
            }
        })
    }
</script>
