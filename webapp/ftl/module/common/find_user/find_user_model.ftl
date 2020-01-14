<#--START MODAL 根据用户姓名和账号查询并选择用户的modal;-->

<div id="findUserModel" class="modal fade" tabindex="-1" data-keyboard="false" data-width="550">
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
			          <select id="department_multiple" class="form-control select2" multiple>
											
					  </select>
			        </div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">搜索</label>
					<div class="col-md-8" style="padding-right:0px;">
						<input id="findNameAccountInput" type="text" class="form-control" placeholder="输入用户的姓名或账号">
					</div>
					<div class="col-md-2">
						<button type="button" id="findNameAccountBtn" class="btn blue btn-sm" style="height:34px;"><i class="fa fa-search"></i> 查询</button>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">用户</label>
					<div class="col-md-10">
						<select multiple="multiple" id="findUserList" class="col-md-12" style="overflow-y: auto;" size="10">
						</select>
					</div>
				</div>
				<p>注：最多只能同时显示20名用户</p>
			</div>
		</form>
		<#--END FORM-->
	</div>
	<div class="modal-footer">
		<button type="button" id="sureSelectedUserBtn" data-dismiss="modal" class="btn blue">确定</button>
		<button type="button" data-dismiss="modal" class="btn btn-default">返回</button>
	</div>
</div>

<#--END MODAL-->
