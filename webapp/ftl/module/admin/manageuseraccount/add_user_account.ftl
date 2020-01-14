<#--BEGIN PAGE LEVEL STYLES-->

<#--END PAGE LEVEL STYLES-->

<div class="note note-danger">
	添加用户的规则说明：
	<ol>
		<li>用户账号长度为6~16位字符</li>
		<li>密码默认为666666</li>
		<li>创建用户必须选择所属部门</li>
	</ol>
</div>
<div class="row">
	<div class="col-md-12">
		<#-- BEGIN VALIDATION STATES-->
			<form action="javascript:void(0)" class="form-horizontal" id="addNewUserAccountForm">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-edit"></i>添加用户账号
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
							<h3 class="form-section">添加账号管理</h3>
							
							
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
											用户账号
											<span class="required">*</span>
										</label>
										<div class="col-md-10"> 
											<input type="text" name="usbaAccount" class="form-control" placeholder="请输入用户账号" value="">	
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
											用户姓名<span class="required">*</span>
										</label>
										<div class="col-md-10"> 
											<input type="text" name="usbaName" class="form-control " placeholder="请输入用户姓名" value="">	
										</div>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										性别
										<span class="required">*</span>
										</label>
										<div class="col-md-10">
											<div class="input-icon right">
												<i class="fa"></i>
												<select class="bs-select form-control" name="usbaSex">
													<option value="0" selected>男</option>
													<option value="1">女 </option>
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
												<select class="bs-select form-control" name="depaId">
													<option value="" selected>请选择...</option>
													<#if departmentTree??>
														<option value="${departmentTree.depaId!}">${departmentTree.depaName!}</option>
														<#list departmentTree.childList as child>
															<option value="${child.depaId!}">&nbsp;&nbsp;&nbsp;${child.depaName!}</option>
															<#list child.childList as child2>
																<option value="${child2.depaId!}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${child2.depaName!}</option>
																<#list child2.childList as child3>
																	<option value="${child3.depaId!}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${child3.depaName!}</option>
																	<#list child3.childList as child4>
																		<option value="${child4.depaId!}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${child4.depaName!}</option>
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
											<label id="usba_superior_name"  class="form-control"></label>
										</div>
										<div class="col-md-2">
											<a class="btn blue" id="usba_superior_name_btn" >选择直属上级</a>
										</div>
										<input id="usbaSuperiorId" name="usbaSuperiorId" type="hidden" value=""/>
								   </div>
								</div>
								
								
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
										 	角色
										</label>
										<div class="col-md-10"> 
											<select class="form-control select2" name="userRoleId" id="user-add-role-ids" multiple>
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
											<select class="form-control select2" name="userPositionId" id="user-add-position-ids" multiple>
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
											是否禁用
											<span class="required">*</span>
										</label>
										<div class="col-md-10">
											<div class="input-icon right">
												<i class="fa"></i>
												<select class="bs-select form-control" name="usbaAccountEnable">
													<option value="0">是</option>
													<option value="1" selected>否 </option>
												</select>
											</div>
										</div>
									</div>
								</div>
								
							</div>
							<!--/row-->
						</div>
						
						<div class="form-actions fluid">
							<div class="row text-center">
								<div class=" col-md-12">
									<button class="btn green" data-target="#static" href="admin/manageUserAccount/addNewUserAccount" id="addNewUserAccountSubmit">
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
		<#-- END VALIDATION STATES-->
	</div>
</div>
<#-- END PAGE CONTENT-->
<#include "../../common/find_user/find_user_model.ftl">

<#--BEGIN PAGE JAVASCRIPT -->
<script>
    require(["jquery", "${base}/assets/custom/scripts/admin/manageuseraccount/add_user_account_init.js","${base}/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js"], function($, addUserAccountInit) {
        $(function() {
            Metronic.init();
            addUserAccountInit.init();
        });
    });
</script>
<#--END PAGE JAVASCRIPT-->
