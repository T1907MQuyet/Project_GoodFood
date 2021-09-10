<div class="col-md-12">
    <div class="card card-info collapsed-card">
        <div class="card-header">
            <h3 class="card-title">Look Up Orders</h3>
            <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-plus"></i></button>
                <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
            </div>
        </div>
        <!-- /.card-header -->
        <div class="card-body" style="display: none; color: #FFF">
            <div class="col-md-12" style="margin-top: 10px;margin-bottom: 10px;">
                <div class="row">
                    <div class="col-md-3 col-sm-6 col-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-primary"><i class="far fa-envelope"></i></span>

                            <div class="info-box-content">
                                <a href="${pageContext.request.contextPath}/admin/order/page/1/1">
                                    <span class="info-box-text">Waiting</span>
                                    <span class="info-box-number">${coutWatting}</span>
                                </a>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <!-- /.col -->
                    <div class="col-md-3 col-sm-6 col-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-info"><i class="far fa-flag"></i></span>
                            <div class="info-box-content">
                                <a class="text-info" href="${pageContext.request.contextPath}/admin/order/page/1/2">
                                    <span class="info-box-text">Confirmed</span>
                                    <span class="info-box-number">${coutConfirmed}</span>
                                </a>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <!-- /.col -->
                    <div class="col-md-3 col-sm-6 col-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-warning"><i class="fas fa-shipping-fast"></i></span>
                            <div class="info-box-content">
                                <a class="text-success" href="${pageContext.request.contextPath}/admin/order/page/1/3">
                                    <span class="info-box-text">Shipping</span>
                                    <span class="info-box-number">${coutShipping}</span>
                                </a>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <div class="col-md-3 col-sm-6 col-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-success"><i class="fas fa-thumbs-up"></i></span>
                            <div class="info-box-content">
                                <a class="text-warning" href="${pageContext.request.contextPath}/admin/order/page/1/4">
                                    <span class="info-box-text">Complete</span>
                                    <span class="info-box-number">${coutComplete}</span>
                                </a>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <div class="col-md-3 col-sm-6 col-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-danger"><i class="far fa-window-close"></i></span>
                            <div class="info-box-content">
                                <a class="text-danger" href="${pageContext.request.contextPath}/admin/order/page/1/5">
                                    <span class="info-box-text">Cancelled</span>
                                    <span class="info-box-number">${coutCancelled}</span>
                                </a>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                    <div class="col-md-3 col-sm-6 col-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-fuchsia"><i class="fas fa-clipboard-list"></i></span>
                            <div class="info-box-content">
                                <a class="text-fuchsia" href="${pageContext.request.contextPath}/admin/order">
                                    <span class="info-box-text">All Order</span>
                                    <span class="info-box-number"></span>
                                </a>
                            </div>
                            <!-- /.info-box-content -->
                        </div>
                        <!-- /.info-box -->
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-12">
                        <label class="text-bold text-black-50">Search by email:</label>
                        <form class="row" method="GET" action="${pageContext.request.contextPath}/admin/order/searchOrderEmail" role="form">
                            <div class="form-group col-3">
                                <input type="text" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email ">
                            </div>
                            <div class="col-3">
                                <button type="submit" class="btn btn-outline-primary">Search</button>
                            </div>
                        </form>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-12">
                        <a href="javascript:void(0);"  data-toggle="modal" data-target="#exampleModal5" class="btn  btn-outline-info btn-sm"><i class="fas fa-search"></i> Search Order Service</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.card-body -->
        <!-- /.card-footer-->
    </div>
</div>