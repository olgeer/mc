<#--START MODAL 根据用户姓名和账号查询并选择用户的modal;-->

<div id="queryUserModel" class="modal fade" tabindex="-1" data-keyboard="false" data-width="650">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">查找用户</h4>
	</div>
	<div class="modal-body">
		<!-- BEGIN FORM-->
		<form action="javascript:void(0)" action="#" class="form-horizontal">
			<div class="form-body">
				<div class="form-group last">
					<label class="control-label col-md-2">部门</label>
					 <div class="col-md-10">
			          <select id="department" class="form-control select2" multiple>
											
					  </select>
			        </div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">搜索</label>
					<div class="col-md-8" style="padding-right:0px;">
						<input id="queryNameAccountInput" type="text" class="form-control" placeholder="输入用户的姓名或账号">
					</div>
					<div class="col-md-2">
						<button type="button" id="queryNameAccountBtn" class="btn blue btn-sm" style="height:34px;"><i class="fa fa-search"></i> 查询</button>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">用户</label>
					<div class="col-md-10">
						<#-- 未被选中的用户 -->
						<select multiple="multiple" id="notSelectedUser" class="col-md-5" size="10">
						</select>
						<div class="col-md-2" style="margin:0 auto;padding:11px;">
							<a class="btn btn-sm default" id="addUser">添加>></a>
							<br><br>
							<a class="btn btn-sm default" id="removeUser"><<移除</a>
						</div>
						<#-- 被选中的用户 -->
						<select multiple="multiple" id="selectedUser" class="col-md-5" size="10">
						</select>
					</div>
				</div>
				<p>注：最多只能同时显示100名用户</p>
			</div>
		</form>
		<#--END FORM-->
	</div>
	<div class="modal-footer">
		<button type="button" id="sureSelectedUser" data-dismiss="modal" class="btn blue">确定</button>
		<button type="button" data-dismiss="modal" class="btn btn-default">返回</button>
	</div>
</div>

<#--END MODAL-->
