<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${base}/assets/global/plugins/jstree/dist/themes/default/style.min.css"/>
<!-- END PAGE LEVEL STYLES -->

<!-- BEGIN PAGE CONTENT-->
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption">
			<#if editRoleId??>
				<i class="fa fa-pencil"></i>编辑角色
			<#else>
				<i class="fa fa-pencil"></i>新建角色
			</#if>
		</div>
	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form action="javascript:void(0)" class="form-horizontal" id="roleMangeForm">
			
			<#if editRoleId??>
				<input type="hidden" id="roleId" name="roleId" value="${editRoleId}"	> 
				<input type="hidden" name="roleEditStatus" value="${role.roleEditStatus}">
			<#else>
				<input type="hidden" name="roleEditStatus" value="1">
			</#if>
			
			
			
			<div class="form-body">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					填写信息不完整或有误，请重新检查！
				</div>
				<div class="alert alert-success display-hide">
					<button class="close" data-close="alert"></button>
					操作成功!
				</div>
				<p style="margin:10px 0px 0px 0px  ;">
					<font size="4" face="Verdana">
						角色基本信息
					</font>
				</p>
				<hr style="FILTER: alpha(opacity=100,finishopacity=0,style=2);margin: 0px 0px 10px 0px  ;padding:0;"  color=#987cb9 SIZE=3/>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								角色名称
								<span class="required">*</span>
							</label>
							<div class="col-md-8"> 
								<input type="text" name="roleName" class="form-control" <#if role??> value="${role.roleName}" </#if>
									placeholder="角色名称" >
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-4 control-label">级别
								<span class="required">*</span>
							</label>
							<div class="col-md-8">
								<select class="bs-select form-control"  name="gradId">
									<#list gradeList as grade  >
										<option value="${grade.gradId}" <#if role??&&role.gradId??&&role.gradId==grade.gradId> selected </#if>>${grade.gradGradeName}</option>
									</#list>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								启用状态
								<span class="required">*</span>
							</label>
							<div class="col-md-8"> 
								<div class="input-icon right">
									<i class="fa"></i> 
									<select class="bs-select form-control" name="roleUseStatus" <#if role??&&role.roleEditStatus==0>disabled</#if>>
										<option value="1" <#if role??&&role.roleUseStatus==1> selected </#if> >启用</option>
										<option value="0" <#if role??&&role.roleUseStatus==0> selected </#if> >关闭</option> 
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								角色类型
								<span class="required">*</span>
							</label>
							<div class="col-md-8"> 
								
								<div class="input-icon right">
									<i class="fa"></i> 
									<#if role??&&role.roleEditStatus==0>
										<@dictDisplay name="roleType" value=(role.roleType)!'0' type="roleType" /> 
									<#else>
										<@dictSelect name="roleType" value=(role.roleType)!'0' type="roleType" showSelect=ture /> 
									</#if>
						    		
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-2">
								角色描述
							</label>
							<div class="col-md-10"> 
								<input type="text" name="roleDescribe" class="form-control" <#if role??> value="<#if role.roleDescribe??>${role.roleDescribe}</#if>" </#if>
									placeholder="角色描述" >
							</div>
						</div>
					</div>
				</div>
				
				<p style="margin:10px 0px 0px 0px  ;">
					<font size="4" face="Verdana">
						权限范围
					</font>
				</p>
				<hr style="FILTER: alpha(opacity=100,finishopacity=0,style=2);margin: 0px 0px 10px 0px  ;padding:0;"  color=#987cb9 SIZE=3/>
					
				<#--树结构-->	
				<input id="treeIds" name="permIdsStr" type="hidden" />
				<div class="row" style="padding-left:15px;padding-right:15px;">
					<div id="tree_2" class="tree-demo" style="padding-left:30px;">
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="row">
					<div class="col-md-12 text-center">
						<#if editRoleId??>
							<a id="saveRoleMange" href="admin/roleManage/updateRoleMange" class="btn green">
								<i class="fa fa-save"></i> 保存
							</a>
						<#else>
							<a id="saveRoleMange" href="admin/roleManage/saveRoleMange" class="btn green">
								<i class="fa fa-save"></i> 保存
							</a>
						</#if>
	
						<a href="admin/roleManage/toRoleManageList" class="btn default ajaxify">
							<i class="fa fa-mail-reply"></i> 返回
						</a>
					</div>
				</div>
			</div>
		</form>
		<!-- END FORM-->
	</div>
</div>
<!-- END PAGE CONTENT-->

<#--BEGIN PAGE JAVASCRIPT -->
<script>
    require(["jquery", "${base}/assets/custom/scripts/admin/rolemanage/role_manage_add_edit_init.js","${base}/assets/global/plugins/jstree/dist/jstree.min.js"], function($, roleManageAddEditInit,jstree) {
        $(function() {
            Metronic.init();
            roleManageAddEditInit.init();
        });
    });
</script>
<#--END PAGE JAVASCRIPT-->

