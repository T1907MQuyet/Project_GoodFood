<%--
  Created by IntelliJ IDEA.
  User: asuspc
  Date: 4/28/2021
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<script data-cfasync="false" src="/assets/frontend/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
<script src="/assets/frontend/js/vendor/jquery-1.11.2.min.js"></script>
<script src="/assets/frontend/js/vendor/bootstrap.min.js"></script>
<script src="/assets/frontend/js/vendor/jquery.flexslider-min.js"></script>
<script src="/assets/frontend/js/vendor/spectragram.js"></script>
<script src="/assets/frontend/js/vendor/owl.carousel.min.js"></script>
<script src="/assets/frontend/js/vendor/velocity.min.js"></script>
<script src="/assets/frontend/js/vendor/velocity.ui.min.js"></script>
<script src="/assets/frontend/js/vendor/bootstrap-datepicker.min.js"></script>
<script src="/assets/frontend/js/vendor/bootstrap-clockpicker.min.js"></script>
<script src="/assets/frontend/js/vendor/jquery.magnific-popup.min.js"></script>
<script src="/assets/frontend/js/vendor/isotope.pkgd.min.js"></script>
<script src="/assets/frontend/js/vendor/slick.min.js"></script>
<script src="/assets/frontend/js/vendor/wow.min.js"></script>
<script src="/assets/frontend/js/animation.js"></script>
<script src="/assets/frontend/js/vendor/vegas/vegas.min.js"></script>
<script src="/assets/frontend/js/vendor/jquery.mb.YTPlayer.js"></script>
<script src="/assets/frontend/js/vendor/jquery.stellar.js"></script>
<script src="/assets/frontend/js/main.js"></script>
<script src="/assets/frontend/js/vendor/mc/jquery.ketchup.all.min.js"></script>
<script src="/assets/frontend/js/vendor/mc/main.js"></script>
<script src="${pageContext.request.contextPath}/assets/dist/js/sweetalert2.all.min.js"></script>
<script>
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer),
        toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
    });
</script>
