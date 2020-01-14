<#-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<#-- Begin: life time stats -->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-shopping-cart"></i>用户账号
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="btn-group">
						<a class="btn yellow ajaxify" href="admin/manageUserAccount/toAddUserAccount">
						<i class="fa fa-plus-circle"></i> 添加用户
						</a>
					</div>
					<div class="btn-group">
						<a class="btn yellow" href="admin/manageUserAccount/activateAllUserAccount" id="activateAllUserAccountBtn">
						<i class="fa fa-play"></i> 启用全部用户账号
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
						id="adminUserAccountList">
						<thead>
							<tr role="row" class="heading">
								<th width="3%"></th>
								<th width="16%"></th>
								<th width="13%"></th>
								<th width="12%"></th>
								<th width="12%"></th>
								<th width="9%"></th>
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
									<input type="text" class="form-control form-filter input-sm" name="superior_name">
								</td>
								<td>
									<select name="usba_account_enable" class="form-control form-filter input-sm">
										<option value="">请选择...</option>
										<option value="0">禁用</option>
										<option value="1">启用</option>
									</select>
								</td>
								<td>
									<select name="usba_account_locked" class="form-control form-filter input-sm">
										<option value="">请选择...</option>
										<option value="0">锁定</option>
										<option value="1">开放</option>
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

<#--BEGIN PAGE JAVASCRIPT -->
<script>
    require(["jquery", "${base}/assets/custom/scripts/admin/manageuseraccount/user_account_list_init.js"], function($, userAccountListInit) {
        $(function() {
            Metronic.init();
            userAccountListInit.init();
        });
    });
</script>
<#--END PAGE JAVASCRIPT-->

