<#--BEGIN PAGE LEVEL STYLES-->
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/global/plugins/data-tables/DT_bootstrap.css" type="text/css" rel="stylesheet" />
<#--END PAGE LEVEL STYLES-->

<div class="row">
	<div class="col-md-12">
		<#-- BEGIN FORM-->
		<form action="javascript:void(0)" class="form-horizontal" id="toUpdateMailAndPhone">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-user"></i>个人信息
					</div>
				</div>
				<div class="portlet-body form">
					<#-- BEGIN FORM-->
					<div class="form-horizontal">
						<div class="form-body">
							<div class="alert alert-danger display-hide">
								<button class="close" data-close="alert"></button>
								填写信息不完整或有误，请重新检查！
							</div>
							<div class="alert alert-success display-hide">
								<button class="close" data-close="alert"></button>
								操作成功!
							</div>
							<#--START TASK DEFINITION MESSAGE-->
							<!-- <h3 class="form-section">用户账号管理</h3> -->
							<div class="row">								
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	用户账号
										</label>
										<div class="col-md-10"> 
											<div class="input-icon right">
												<i class="fa"></i>
												<label class="control-label">${userDetails.usbaAccount!}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	姓名
										</label>
										<div class="col-md-10"> 
											<div class="input-icon right">
												<i class="fa"></i>
												<label class="control-label">${userDetails.usbaName!}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
											所属部门
										</label>
										<div class="col-md-10">
											<div class="input-icon right">
												<i class="fa"></i>
												<label class="control-label">${department.depaName!}</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	账号创建时间
										</label>
										<div class="col-md-10"> 
											<div class="input-icon right">
												<label class="control-label">${userDetails.usbaCreateDate?string("yyyy-MM-dd HH:mm:ss")}</label>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	邮箱
										</label>
										<div class="col-md-4"> 
											<div class="input-icon right">
											<input class="form-control input-sm" type="text" name="usbaMail" value="${userDetails.usbaMail!}" id="usbaMail" autocomplete="off">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	电话
										</label>
										<div class="col-md-4"> 
											<div class="input-icon right">
											<input class="form-control input-sm" type="text" name="usbaPhone" value="${userDetails.usbaPhone!}" id="usbaPhone" autocomplete="off">
											</div>
										</div>
									</div>
								</div>
				
							</div>
						</div>
						
						<div class="form-actions fluid">
							<div class="row text-center">
								<div class=" col-md-12">
									
									<button class="btn green" data-target="#static" id="toUpdateMailAndPhoneSubmit">
										<i class="fa fa-check"></i> 修改
									</button>
                                   <a class="btn btn-sm default" onclick="Layout.closeTabAndBack()">
                                        <i class="fa fa-mail-reply"></i> 返回
                                    </a>

								</div>
							</div>
						</div>
					</div>
					
				</div>
			
			</div>
		</form>
		<#-- END FORM -->
		
	</div>
</div>
<#-- END PAGE CONTENT-->
<#--BEGIN PAGE JAVASCRIPT -->
<script>
    require(["jquery", "${base}/assets/custom/scripts/admin/manageuseraccount/view_user_account_init.js"], function($, viewUserAccountInit) {
        $(function() {
            Metronic.init();
            viewUserAccountInit.init();
        });
    });
</script>
<#--END PAGE JAVASCRIPT-->
