<!-- BEGIN PAGE LEVEL STYLES -->
<!-- END PAGE LEVEL STYLES -->

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- Begin: life time stats -->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-shopping-cart"></i> 权限管理
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="btn-group">
						<a class="btn yellow" id="addPermission"> 
						<i class="fa fa-plus-circle"></i> 添加权限
						</a>
					</div>
				</div>
				<div class="table-container">
					<div class="table-actions-wrapper">
						<span> </span> 
						<select class="table-group-action-input form-control input-inline input-sm">
							<option value="">选择事项...</option>
							<option value="delete">删除</option>
							<option value="open">启用</option>
							<option value="close">关闭</option>
						</select> 
						<a class="btn btn-sm yellow table-group-action-submit"data-target="#staticModal"> 
							<i class="fa fa-check"></i> 确定
						</a>
						<!-- static modal-->
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
						id="adminPermissionList">
						<thead>
							<tr role="row" class="heading">
								<th width="5%"></th>
								<th width="12%"></th>
								<th width="12%"></th>
								<th width="27%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="7%"></th>
							</tr>
							<tr role="row" class="filter">
								<td></td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="perm_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="perm_parent_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="perm_url">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="perm_resource">
								</td>
								<td>
									<select name="perm_use_status" class="form-control form-filter input-sm">
										<option value="">全部</option>
										<option value="0">关闭</option>
										<option value="1">启用</option>
									</select>	
								</td>
								<td>
									<select name="perm_type" class="form-control form-filter input-sm">
										<option value="">全部</option>
										<option value="0">功能</option>
										<option value="1">模块</option>
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
		<!-- End: life time stats -->
	</div>
</div>
<!-- END PAGE CONTENT-->

<!--START MODAL-->
<div id="permissionModel" class="modal fade" tabindex="-1" data-keyboard="false" data-width="600">
</div>
<!--END MODAL-->

<#--BEGIN PAGE JAVASCRIPT -->
<script>
    require(["jquery", "${base}/assets/custom/scripts/admin/permission/permission_list_init.js"], function($, permissionListInit) {
        $(function() {
            Metronic.init();
            permissionListInit.init();
        });
    });
</script>
<#--END PAGE JAVASCRIPT-->
