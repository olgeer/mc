<#--START MODAL 选择收件人的modal;-->
<div id="addUserRoleModel" class="modal fade" tabindex="-1" data-keyboard="false" data-width="600" data-height="200">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">添加用户角色</h4>
	</div>
	<div class="modal-body">
	
		<!-- BEGIN FORM-->
		<form action="javascript:void(0)" action="#" class="form-horizontal" id="addUserRoleForm">
			<div class="form-body">
				<div class="form-group">
					<label class="control-label col-md-2">
						角色名称
					</label>
					<div class="col-md-10">
						<div class="input-icon right">
							<i class="fa"></i>
							<select class="bs-select form-control" name="roleId" id="roleId" data-size="4">
								<#list roles as role>
									<option value="${role.roleId}">${role.roleName}</option>
								</#list>
							</select>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-2">是否启用</label>
					<div class="col-md-10">
						<div class="input-icon right">
							<i class="fa"></i>
							<select class="bs-select form-control" name="usroStatus" id="usroStatus">
								<option value="0" selected>否</option>
								<option value="2">是</option>
							</select>
						</div>
					</div>
				</div>
				<input type="hidden" name="usbaId" id="usbaId" value="${userDetails.usba_id}">
			</div>
			<#--/row-->
		</form>
		
	</div>
	<div class="modal-footer">
		<button type="button" id="addUserRoleSubmit" class="btn blue" href="admin/manageUserAccount/addUserRole">确定</button>
		<button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
	</div>
</div>
<#--END MODAL-->
