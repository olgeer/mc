
<div class="row">
	<div class="col-md-12">
		<#-- BEGIN FORM-->
		<form action="javascript:void(0)" class="form-horizontal" id="editUserAccountForm">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>编辑用户账号
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
							<h3 class="form-section">用户账号管理</h3>
							<div class="row">								
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	用户账号
											<span class="required">*</span>
										</label>
										<div class="col-md-10"> 
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" name="usbaAccount" class="form-control " placeholder="" value="${userDetails.usba_account!}" disabled>	
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	姓名
											<span class="required">*</span>
										</label>
										<div class="col-md-10"> 
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" name="usbaName" class="form-control " placeholder="" value="${userDetails.usba_name!}" >	
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	密码
											<span class="required">*</span>
										</label>
										<div class="col-md-10"> 
											<div class="input-icon right">
												<i class="fa"></i>
												<button class="btn blue" href="admin/manageUserAccount/resetUserAccountPassword" id="resetUserPasswordSubmit">
													重置
												</button> 
												<span class="required">*</span>重置后密码为666666
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
											是否禁用
											<span class="required">*</span>
										</label>
										<div class="col-md-10">
											<div class="input-icon right">
												<i class="fa"></i>
												<select class="bs-select form-control" name="usbaAccountEnable">
													<option value="0" 
													<#if userDetails.usba_account_enable?? && userDetails.usba_account_enable==0>selected</#if>>是</option>
													<option value="1" 
													<#if userDetails.usba_account_enable?? && userDetails.usba_account_enable==1>selected</#if>>否 </option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	账号锁定
										 	<span class="required">*</span>
										</label>
										<div class="col-md-10"> 
											<div class="input-icon right">
												<i class="fa"></i>
												<select class="bs-select form-control" name="usbaAccountLocked">
													<option value="0" 
													<#if userDetails.usba_account_locked?? && userDetails.usba_account_locked==0>selected</#if>>是</option>
													<option value="1" 
													<#if userDetails.usba_account_locked?? && userDetails.usba_account_locked==1>selected</#if>>否</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
											所属部门
											<span class="required">*</span>
										</label>
										<div class="col-md-10">
											<div class="input-icon right">
												<i class="fa"></i>
												<select class="bs-select form-control" name="depaId" id="depaId">
													<#if departmentTrees??>
														<#list departmentTrees as departmentTree>
															<option value="${departmentTree.depaId!}" <#if userDetails.depa_id??&&departmentTree.depaId == userDetails.depa_id>selected</#if>>${departmentTree.depaName!}</option>
															<#list departmentTree.childList as child>
																<option value="${child.depaId!}" <#if userDetails.depa_id??&&child.depaId == userDetails.depa_id>selected</#if>>&nbsp;&nbsp;&nbsp;${child.depaName!}</option>
																<#list child.childList as child2>
																	<option value="${child2.depaId!}" <#if userDetails.depa_id??&&child2.depaId == userDetails.depa_id>selected</#if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${child2.depaName!}</option>
																	<#list child2.childList as child3>
																		<option value="${child3.depaId!}" <#if userDetails.depa_id??&&child3.depaId == userDetails.depa_id>selected</#if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${child3.depaName!}</option>
																		<#list child3.childList as child4>
																			<option value="${child4.depaId!}" <#if userDetails.depa_id??&&child4.depaId == userDetails.depa_id>selected</#if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${child4.depaName!}</option>
																		</#list>
																	</#list>
																</#list>
															</#list>
														</#list>
													</#if>
												</select>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										直属上级
										</label>
										<div class="col-md-8">
											<label id="usba_superior_name"  class="form-control"><#if userDetails.superior_name??>${userDetails.superior_name!}[${userDetails.superior_account!}]</#if></label>
										</div>
										<div class="col-md-2">
											<a class="btn blue" id="usba_superior_name_btn" >选择直属上级</a>
										</div>
										<input id="usbaSuperiorId" name="usbaSuperiorId" type="hidden" value="${userDetails.usba_superior_id!}"/>
								   </div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	角色
										</label>
										<div class="col-md-10"> 
											<select class="form-control select2" data-value="${userRoleIds}"
												name="userRoleId" id="user-role-ids" multiple>
												<#list roleList as role>
										       		<option value="${role.role_id}">${role.role_name}</option>
										        </#list>
											</select>
										</div>
									</div>
								</div>
								
								
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	岗位
										</label>
										<div class="col-md-10"> 
											<select class="form-control select2" data-value="${userPositionIds}"
												name="userPositionId" id="user-position-ids" multiple>
												<#list positionList as position>
										       		<option value="${position.bppoId}">${position.bppoName}</option>
										        </#list>
											</select>
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
												<label class="col-md-12 form-control">${userDetails.usba_create_date!}</label>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	账号过期
										</label>
										<div class="col-md-10"> 
											<div class="input-icon right">
												<label class="col-md-12 form-control">
													<#if userDetails.usba_account_expired?? && userDetails.usba_account_expired==0>
														是
													<#elseif userDetails.usba_account_expired?? && userDetails.usba_account_expired==1>
														否
													</#if>
												</label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	证书过期
										</label>
										<div class="col-md-10"> 
											<div class="input-icon right">
												<label class="col-md-12 form-control">
													<#if userDetails.usba_credential_expired?? && userDetails.usba_credential_expired==0>
														是
													<#elseif userDetails.usba_credential_expired?? && userDetails.usba_credential_expired==1>
														否
													</#if>
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" name="usbaId" id="usbaId" value="${userDetails.usba_id}">
						</div>
						
						<div class="form-actions fluid">
							<div class="row text-center">
								<div class=" col-md-12">
									<button class="btn green" data-target="#static" href="admin/manageUserAccount/updateUserAccount" id="editUserAccountSubmit">
										<i class="fa fa-check"></i> 提交
									</button>
									<a class="btn default ajaxify" href="admin/manageUserAccount/toUserAccountList">
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
<#include "../../common/find_user/find_user_model.ftl">

<#--BEGIN PAGE JAVASCRIPT -->
<script>
    require(["jquery", "${base}/assets/custom/scripts/admin/manageuseraccount/edit_user_account_init.js","${base}/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js"], function($, editUserAccountInit) {
        $(function() {
            Metronic.init();
            editUserAccountInit.init();
        });
    });
</script>
<#--END PAGE JAVASCRIPT-->
