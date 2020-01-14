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
						<i class="fa fa-edit"></i>安全测试信息详情
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
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-2 control-label">
									标题<span class="required">*</span>
								</label>
								<div class="col-md-10">
									<label class="col-md-12 form-control">${cmdbPenetrationTest.cpteTitle!}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label">
									平台联系人<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<label class="col-md-12 form-control">${cmdbPenetrationTest.cptePlatformContacts!}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label">
									联系人电话<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<label class="col-md-12 form-control">${cmdbPenetrationTest.cptePhone!}</label>
								</div>
							</div>
						</div>
						
						
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label">
									系统链接<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<label class="col-md-12 form-control">${cmdbPenetrationTest.cpteForegroundAddress!}</label>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label">
									后台链接<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<label class="col-md-12 form-control">${cmdbPenetrationTest.cpteBackgroundAddress!}</label>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-4 control-label">
									短信查询链接<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<label class="col-md-12 form-control">${cmdbPenetrationTest.cpteMessagesAddress!}</label>
								</div>
							</div>
						</div>
						
						
						
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-2 control-label">
									测试账号
								</label>
								<div class="col-md-10">
									<pre class="col-md-12 form-control">${cmdbPenetrationTest.cpteTestAccount!}&nbsp;</pre>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-2 control-label">
									平台介绍
								</label>
								<div class="col-md-10">
									<pre class="col-md-12 form-control">${cmdbPenetrationTest.cptePlatformIntroduce!}&nbsp;</pre>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-2 control-label">
									注意事项
								</label>
								<div class="col-md-10">
									<pre class="col-md-12 form-control">${cmdbPenetrationTest.cpteAttentions!}&nbsp;</pre>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-2 control-label">
									备注
								</label>
								<div class="col-md-10">
									<pre class="col-md-12 form-control">${cmdbPenetrationTest.cpteRemark!}&nbsp;</pre>
								</div>
							</div>
						</div>
						
					</div>
					
				</div>
				
				
				
				 <#if taskId?? || (cmdbPenetrationTestAuditList?? && (cmdbPenetrationTestAuditList?size > 0))>
						<h3 class="form-section">审核意见</h3>
					</#if>
					<#if cmdbPenetrationTestAuditList?? && (cmdbPenetrationTestAuditList?size > 0)>
					<div class="row">
						<#list cmdbPenetrationTestAuditList as cmdbPenetrationTestAudit>
							<#if cmdbPenetrationTestAudit_index!=0>
							<div class="col-md-12">
								<div class="form-line"></div>
							</div>
							</#if>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">
									 	${cmdbPenetrationTestAudit.usba_name!}(${cmdbPenetrationTestAudit.role_name!})
									</label>
									<div class="col-md-8">
										<div class="input-icon right">
											<@dictDisplay value=(cmdbPenetrationTestAudit["result"]!) type="auditStatus" />
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">
									 	审核时间
									</label>
									<div class="col-md-8">
										<label class="col-md-12 form-control">${cmdbPenetrationTestAudit["date"]!}</label>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">
									 	审核意见
									</label>
									<div class="col-md-10">
										<div class="input-icon right">
											<pre class="col-md-12 form-control">${cmdbPenetrationTestAudit.opinion!"无"}&nbsp;</pre>
										</div>
									</div>
								</div>
							</div>
						</#list>
					</div>
					</#if>
					
					<#if taskId??>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label col-md-2">
									 		${Session.SPRING_SECURITY_CONTEXT.authentication.principal.usbaName} 审核
									</label>
									<div class="col-md-10">
										<div class="input-icon right">
											<i class="fa"></i>
											<textarea class="col-md-12 form-control" name="cpteOpinion" rows="5" placeholder=""></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
					</#if>
					 <input type="hidden" name="taskId" value="${taskId!}"/>
					 <input type="hidden" name="processInstanceId" value="${processInstanceId!}"/>
					 <input type="hidden" name="cpteId" value="${cmdbPenetrationTest["cpteId"]!}"/>
					 
					 
					<div class="form-actions fluid">
						<div class="row text-center">
							<div class=" col-md-12">
							
								<#if taskId??>
									<button class="btn blue button-pass"  id="passCmdbPenetrationTest" href="module/cmdb/cmdbPenetrationTestCommon/passCmdbPenetrationTestAudit">
										<i class="fa fa-check"></i> 通过
									</button>
									<button class="btn blue button-rollback"  id="rollbackCmdbPenetrationTest" href="module/cmdb/cmdbPenetrationTestCommon/rollbackCmdbPenetrationTestAudit">
										<i class="fa fa-times"></i> 驳回
									</button>
								</#if>
								<a class="btn default ajaxify button-return" href="${returnUrl!}">
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
<script src="${base}/assets/custom/scripts/cmdb/common/common_penetration_test_view_main.js" type="text/javascript"></script>

