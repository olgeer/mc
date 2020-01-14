<!--START MODAL-->
<form action="${base}/module/login/initData" method="post">
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">选择角色</h4>
</div>
<div class="modal-body">
	<div class="scroller" data-always-visible="1" data-rail-visible1="1">
		<div class="row">
			<div class="col-md-12">
				<h4>用户角色</h4>
				<div class="form-group">
					<div class="radio-list">
						<#list userRoles as userRole>
							<label><input type="radio" name="logininfo" id="optionsRadios1" value="${userRole.usroId}" <#if userRole_index==0>checked</#if>> ${userRole.usroRole.roleName}
							</label>
						</#list>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal-footer">
	<a class="btn default cancelLogin" data-dismiss="modal" href="user/j_spring_security_logout">取消</a>
	<input type="submit" class="btn green" value="确定" />
</div>
</form>
<!--END MODAL-->