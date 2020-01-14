<#--BEGIN PAGE LEVEL STYLES-->
<link rel="stylesheet" href="${base}/assets/global/plugins/data-tables/DT_bootstrap.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${base}/assets/global/plugins/bootstrap-datepicker/css/datepicker.css" />
<link href="${base}/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<#--END PAGE LEVEL STYLES-->

<#-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<#-- BEGIN VALIDATION STATES-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<#if cmdbPenetrationTest.cpteId ??>
						<i class="fa fa-edit"></i>编辑安全测试信息
					<#else>
						<i class="fa fa-edit"></i>新增安全测试信息
					</#if>
				</div>
			</div>
			<div class="portlet-body form">
				<#-- BEGIN FORM-->
				<form action="javascript:void(0)" role="form" class="form-horizontal"  id="cmdbPenetrationTestForm">
					<div class="form-body">
					<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					填写信息不完整或有误，请重新检查！
					</div>
					<div class="alert alert-success display-hide">
						<button class="close" data-close="alert"></button>
						操作成功!
					</div>
					<div class="form-group display-hide">
						<label class="col-md-3 control-label">
						</label>
						<div class="col-md-9">
							<input type="text" name="cpteId"  id="cpteId" class="form-control" placeholder="编号" value="<#if cmdbPenetrationTest.cpteId??>${cmdbPenetrationTest.cpteId}</#if>">
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-2 control-label">
									标题<span class="required">*</span>
								</label>
								<div class="col-md-10">
									<input type="text"  name="cpteTitle"  id="cpteTitle" class="form-control" placeholder="申请标题" value="<#if cmdbPenetrationTest.cpteTitle??>${cmdbPenetrationTest.cpteTitle}</#if>">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label">
									平台联系人<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<input type="text"  name="cptePlatformContacts"  id="cptePlatformContacts" class="form-control" placeholder="平台联系人" value="<#if cmdbPenetrationTest.cptePlatformContacts??>${cmdbPenetrationTest.cptePlatformContacts}</#if>">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label">
									联系人电话<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<input type="text"  name="cptePhone"  id="cptePhone" class="form-control" placeholder="联系人电话" value="<#if cmdbPenetrationTest.cptePhone??>${cmdbPenetrationTest.cptePhone}</#if>">
								</div>
							</div>
						</div>
						
						
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label">
									系统链接<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<input type="text"  name="cpteForegroundAddress"  id="cpteForegroundAddress" class="form-control" placeholder="系统链接" value="<#if cmdbPenetrationTest.cpteForegroundAddress??>${cmdbPenetrationTest.cpteForegroundAddress}</#if>">
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label">
									后台链接<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<input type="text"  name="cpteBackgroundAddress"  id="cpteBackgroundAddress" class="form-control" placeholder="后台链接" value="<#if cmdbPenetrationTest.cpteBackgroundAddress??>${cmdbPenetrationTest.cpteBackgroundAddress}</#if>">
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label">
									短信查询链接<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<input type="text"  name="cpteMessagesAddress"  id="cpteMessagesAddress" class="form-control" placeholder="短信查询链接" value="<#if cmdbPenetrationTest.cpteMessagesAddress??>${cmdbPenetrationTest.cpteMessagesAddress}</#if>">
								</div>
							</div>
						</div>
						
						
						
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-2 control-label">
									测试账号
								</label>
								<div class="col-md-10">
									<textarea   name="cpteTestAccount"  id="cpteTestAccount" class="form-control" placeholder="测试账号" >${cmdbPenetrationTest.cpteTestAccount!}</textarea>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-2 control-label">
									平台介绍
								</label>
								<div class="col-md-10">
									<textarea  name="cptePlatformIntroduce"  id="cptePlatformIntroduce" class="form-control" placeholder="平台介绍" >${cmdbPenetrationTest.cptePlatformIntroduce!}</textarea>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-2 control-label">
									注意事项
								</label>
								<div class="col-md-10">
									<textarea   name="cpteAttentions"  id="cpteAttentions" class="form-control" placeholder="注意事项" >${cmdbPenetrationTest.cpteAttentions!}</textarea>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-2 control-label">
									备注
								</label>
								<div class="col-md-10">
									<textarea  name="cpteRemark"  id="cpteRemark" class="form-control" placeholder="备注" >${cmdbPenetrationTest.cpteRemark!}</textarea>
								</div>
							</div>
						</div>
						
					</div>
					
				</div>
				<div class="form-actions fluid">
					<div class="row text-center">
						<div class=" col-md-12">
							<button class="btn green" href="module/cmdb/penetrationTest/saveCmdbPenetrationTest?cpteAuditStatus=0" id="cmdbPenetrationTestSave">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn green" href="module/cmdb/penetrationTest/submitCmdbPenetrationTest" id="cmdbPenetrationTestSubmit">
								<i class="fa fa-check"></i> 提交
							</button>
							<a class="btn default ajaxify" href="module/cmdb/penetrationTest/toPenetrationTestList">
								<i class="fa fa-mail-reply"></i> 返回
							</a>
						</div>
					</div>
				</div>
			</form>
				<#-- END FORM-->
			</div>
		</div>
		<#-- END VALIDATION STATES-->
	</div>
</div>
<#-- END PAGE CONTENT-->


<script src="${base}/assets/custom/scripts/cmdb/penetration_test/penetration_test_add_edit_main.js" type="text/javascript"></script>
