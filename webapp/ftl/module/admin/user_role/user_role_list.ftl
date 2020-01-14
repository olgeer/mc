<#-- BEGIN PAGE LEVEL STYLES -->
<#-- END PAGE LEVEL STYLES -->


<#-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<#-- Begin: life time stats -->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-shopping-cart"></i>用户角色
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="btn-group">
						<a class="btn yellow" id="addUserRole"> 
							<i class="fa fa-plus-circle"></i> 添加用户角色 
						</a>
					</div>
				</div>
				<div class="table-container">
					<div class="table-actions-wrapper">
						<span> </span> 
						<select class="table-group-action-input form-control input-inline input-sm">
							<option value="">选择事项...</option>
							<option value="open">启用</option>
							<option value="close">禁用</option>
							<option value="delete">删除</option>
						</select> 
						<a class="btn btn-sm yellow table-group-action-submit"data-target="#staticModal"> 
							<i class="fa fa-check"></i> 确定
						</a>
						<#-- static modal-->
						<div id="staticModal" class="modal fade confirm-submit"
							tabindex="-1" data-backdrop="static" data-keyboard="false">
							<div class="modal-body">
								<p>你确定执行此操作？</p>
							</div>
							<div class="modal-footer">
								<button type="button" data-dismiss="modal" class="btn blue sure-submit">确定</button>
								<button type="button" data-dismiss="modal" class="btn btn-default">返回</button>
							</div>
						</div>
					</div>
					<table class="table table-striped table-bordered table-hover"
						id="adminUserRoleList">
						<thead>
							<tr role="row" class="heading">
								<th width="3%"></th>
								<th width="15%"></th>
								<th width="12%"></th>
								<th width="12%"></th>
								<th width="15%"></th>
								<th width="6%"></th>
								<th width="8%"></th>
							</tr>
							<tr role="row" class="filter">
								<td></td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="depa_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="usba_account">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="usba_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="role_name">
								</td>
								<td>
									<select name="usro_status" class="form-control form-filter input-sm">
										<option value="">请选择...</option>
										<option value="1">禁用</option>
										<option value="2">启用</option>
									</select>
								</td>
								<td class="text-center">
									<div class="margin-bottom-5">							
										<button class="btn btn-sm yellow filter-submit">
											<i class="fa fa-search"></i> 查询
										</button>
									</div>
									<button class="btn btn-sm red filter-cancel">
										<i class="fa fa-times"></i> 重置
									</button>
								</td>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<#-- End: life time stats -->
	</div>
</div>
<#-- END PAGE CONTENT-->


<div id="userRoleQueryUserModel" class="modal fade" tabindex="-1" data-keyboard="false" data-width="650">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">添加用户角色</h4>
	</div>
	<div class="modal-body">
		<!-- BEGIN FORM-->
		<form action="javascript:void(0)" action="#" class="form-horizontal"  id="userRoleForm">
			<div class="form-body">
				<div class="form-group ">
					<label class="control-label col-md-2">部门</label>
					 <div class="col-md-10">
			          <select id="user_role_department" class="form-control select2" multiple>
											
					  </select>
			        </div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-2">搜索</label>
					<div class="col-md-8" style="padding-right:0px;">
						<input id="user_role_queryNameAccountInput" type="text" class="form-control" placeholder="输入用户的姓名或账号">
					</div>
					<div class="col-md-2">
						<button type="button" id="user_role_queryNameAccountBtn" class="btn blue btn-sm" style="height:34px;"><i class="fa fa-search"></i> 查询</button>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">用户
					<span class="required">*</span>
					</label>
					<div class="col-md-10">
						<#-- 未被选中的用户 -->
						<select multiple="multiple" id="user_role_notSelectedUser" style="overflow-y: auto;" class="col-md-5" size="10">
						</select>
						<div class="col-md-2" style="margin:0 auto;padding:11px;">
							<a class="btn btn-sm default" id="user_role_addUser">添加>></a>
							<br><br>
							<a class="btn btn-sm default" id="user_role_removeUser"><<移除</a>
						</div>
						<#-- 被选中的用户 -->
						<select multiple="multiple" id="user_role_selectedUser"   style="overflow-y: auto;" class="col-md-5" size="10">
						</select>
					</div>
				</div>
				<div class="form-group ">
					<label class="control-label col-md-2">角色
					<span class="required">*</span>
					</label>
					 <div class="col-md-10">
			          <select id="user_role_role" class="form-control select2 " name="roleId" placeholder="选中用户完成之后，请选择需要绑定的角色" >
											
					  </select>
			        </div>
				</div>
				<#-- 被选中的用户集合 -->
				<input type="hidden" name="usbaIds" id="user_role_usba_ids" />
				<p>注：最多只能同时显示20名用户，如果用户已经绑定过选中的角色，则不会再次绑定！</p>
			</div>
		</form>
		<#--END FORM-->
	</div>
	<div class="modal-footer">
		<button type="button" id="submitSelectedUser" class="btn blue" href="admin/userRole/saveUserRole">提交</button>
		<button type="button" data-dismiss="modal" class="btn btn-default">返回</button>
	</div>
</div>

<#--BEGIN PAGE JAVASCRIPT -->
<script>
    require(["jquery", "${base}/assets/custom/scripts/admin/user_role/user_role_list_init.js",
    "${base}/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js"], function($, userRoleListInit) {
        $(function() {
            Metronic.init();
            userRoleListInit.init();
        });
    });
</script>
<#--END PAGE JAVASCRIPT-->

