<script>
    function getCalender(_service_id) {
        $.ajax({
            method:'GET',
            url:'/admin/service/checkCalenderService?id='+_service_id,
            success:function (res) {
                $("#calender").html(res);
            }})
    }
    function getCustomerServiceProduct(_customerServiceProduct_id) {
        $.ajax({
            method: 'GET',
            url: '/admin/service/checkCustPro?custService_id='+_customerServiceProduct_id,
            success:function (res) {
                $("#customerServiceProduct").html(res);
            }})
    }
    function getCustSPOrder() {
        $.ajax({
            method:'GET',
            url:'/admin/service/checkOrderToday',
            success:function (res) {
                $("#customerServiceOrder").html(res);
            }})
    }
    function getAllCustServiceOrder(_customer_service_id) {
        $.ajax({
            method:'GET',
            url:'/admin/service/checkOrderCS?id='+_customer_service_id,
            success:function (res) {
                $("#customerSO").html(res);
            }})
    }
</script>