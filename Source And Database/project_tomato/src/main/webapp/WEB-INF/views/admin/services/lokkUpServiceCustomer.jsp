<div class="row">
    <div class="col-md-12">
        <div class="card card-info collapsed-card">
            <div class="card-header">
                <h3 class="card-title">Look Up Service Customer</h3>
                <div class="card-tools">
                    <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-plus"></i></button>
                    <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
                </div>
            </div>
            <!-- /.card-header -->
            <div class="card-body" style="display: none; color: #FFF">
                <div class="col-md-12" style="margin-top: 10px;margin-bottom: 10px;">
                    <div class="row">
                        <div class="col-12">
                            <form class="row" method="GET" action="${pageContext.request.contextPath}/admin/service/searchServiceCust" role="form">
                                <div class="form-group col-3">
                                    <input type="text" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email ">
                                </div>
                                <div class="form-group col-2">
                                    <select class="form-control" name="service_id">
                                        <option value="0">Service Selection</option>
                                        <c:forEach items="${listService}" var="service">
                                            <option value="${service.service_id}">${service.service_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-2">
                                    <select class="form-control" name="status">
                                        <option value="1">Active</option>
                                        <option value="2">None Active</option>
                                    </select>
                                </div>
                                <div class="col-3">
                                    <button type="submit" class="btn btn-outline-primary">Search</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-3 col-sm-6 col-12">
                            <a href="${pageContext.request.contextPath}/admin/service/customerService" class="btn btn-block btn-info">All <span class="text-bold " style="font-size: 14px" >(${countAll})</span></a>
                        </div>
                        <div class="col-md-3 col-sm-6 col-12">
                            <a href="${pageContext.request.contextPath}/admin/service/customerSstatus?status=1" class="btn btn-block btn-success">Active <span class="text-bold " style="font-size: 14px" >(${countActive})</span></a>
                        </div>
                        <div class="col-md-3 col-sm-6 col-12">
                            <a  href="${pageContext.request.contextPath}/admin/service/customerSstatus?status=2" class="btn btn-block btn-danger">None Active <span class="text-bold " style="font-size: 14px" >(${countNoneActive})</span></a>
                        </div>
                        <div class="col-md-3 col-sm-6 col-12">
                            <a  href="${pageContext.request.contextPath}/admin/service/customerSstatus?status=0" class="btn btn-block btn-primary">Waiting <span class="text-bold " style="font-size: 14px" >(${countWaiting})</span></a>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-12">
                            <a href="javascript:void(0);"  data-toggle="modal" data-target="#exampleModal5" class="btn  btn-outline-info btn-sm"><i class="fas fa-search"></i> Advanced Service Search</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.card-footer-->
        </div>
    </div>
    <div class="col-md-12">
        <a href="javascript:void(0);" onclick="getCustSPOrder()" data-toggle="modal" data-target="#exampleModal3" class="btn  btn-success btn-sm"><i class="fas fa-plus"> </i> Check out and make an order today</a>
        <a href="${pageContext.request.contextPath}/admin/service/updateStatusCS"  class="btn  btn-info btn-sm"><i class="fas fa-plus"> </i> Update All Service Status</a>
    </div>
    <div class="col-md-3">
    </div>
</div>