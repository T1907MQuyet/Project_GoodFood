<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="${pageContext.request.contextPath}/assets/dist/js/sweetalert2.all.min.js"></script>
<script type="text/javascript">
    $(function () {

        <c:if test="${param.success != null}">
        Swal.fire({
            title: '${param.success} success',
            text: "Please confirm to continue!",
            icon: 'success',
        });
        </c:if>
        <c:if test="${param.error != null}">
        Swal.fire({
            title: '${param.error} error',
            text: "Please confirm to continue!",
            icon: 'error',
        });
        </c:if>
    })
</script>