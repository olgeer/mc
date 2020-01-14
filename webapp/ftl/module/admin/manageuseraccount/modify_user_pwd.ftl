
<div class="row">
	<div class="col-md-12">
		<#-- BEGIN FORM-->
		<form action="javascript:void(0)" class="form-horizontal" id="modifyUserPwd">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-user"></i>修改密码
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
							<div class="row">								
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	原密码
										 	<span class="required">*</span>
										</label>
										<div class="col-md-3"> 
											<div class="input-icon right">
												<i class="fa"></i>
												<input class="form-control input-sm" type="password" name="oldPwd" id="oldPwd" autocomplete="off">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	新密码
										 	<span class="required">*</span>
										</label>
										<div class="col-md-3"> 
											<div class="input-icon right">
												<i class="fa"></i>
												<input class="form-control input-sm" type="password" name="newPwd" id="newPwd" autocomplete="off">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	确认新密码
										 	<span class="required">*</span>
										</label>
										<div class="col-md-3"> 
											<div class="input-icon right">
												<i class="fa"></i>
												<input class="form-control input-sm" type="password" name="confirmNewPwd" autocomplete="off">
											</div>
										</div>
									</div>
								</div>
								
							</div>
							
							<input type="hidden" name="usbaId" id="usbaId" value="${userDetails.usbaId}">
						</div>
						
						<div class="form-actions fluid">
							<div class="row text-center">
								<div class=" col-md-12">
									<button class="btn green" data-target="#static" id="updateUserPasswordSubmit">
										<i class="fa fa-check"></i> 提交
									</button>
									<!-- 
									<a class="btn default ajaxify" href="admin/manageUserAccount/toUserAccountList">
										<i class="fa fa-mail-reply"></i> 返回
									</a>
									-->
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
    require(["jquery", "${base}/assets/custom/scripts/admin/manageuseraccount/modify_user_pwd_init.js"], function($, modifyUserPwdInit) {
        $(function() {
            Metronic.init();
            modifyUserPwdInit.init();
        });
    });
</script>
<#--END PAGE JAVASCRIPT-->

