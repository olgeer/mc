<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"]/>
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/global/plugins/data-tables/DT_bootstrap.css" type="text/css" rel="stylesheet" />
<!-- END PAGE LEVEL STYLES -->


<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- Begin: life time stats -->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-shopping-cart"></i>指标管理
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<@security.authorize ifAnyGranted="ROLE_ADMIN">
					   <div class="btn-group">
							<a class="btn yellow" id="addDepartment"> 
								<i class="fa fa-plus-circle"></i> 添加指标
							</a>
						</div>
					</@security.authorize>
				</div>
				<div class="table-container">
					<div class="table-actions-wrapper">
						<span> </span> 
						<select class="table-group-action-input form-control input-inline input-sm">
							<option value="">选择事项...</option>
							<option value="delete">删除</option>
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
						id="adminDepartmentList">
						<thead>
							<tr role="row" class="heading">
								<th width="3%"></th>
								<th width="30%"></th>
								<th width="9%"></th>
								<th width="10%"></th>
								<th width="30%"></th>
								<th width="9%"></th>
								<th width="7%"></th>
							</tr>
							<tr role="row" class="filter">
								<td></td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="id">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="iname">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="imark">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="status">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="ilevel">
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
<div id="departmentInfo" class="modal fade" tabindex="-1" data-keyboard="false" data-width="600">
</div>
<!--END MODAL-->


<!--START MODAL-->
<div id="uploadDepartmentExcelFile" class="modal fade" tabindex="-1" data-keyboard="false" data-width="500">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">部门导入</h4>
	</div>
	<div class="modal-body ">
		<div class="row">
			<div class="col-md-9">
				<input type="file" id="uploadDepartmentFile" name="uploadDepartmentFile" class="required" >
			</div>
			<div class="col-md-3" style='padding-left:0px'>
				<#--<a class="btn blue tooltips" href="common/exceltemplate/downloadExcelTemplate?excelName=部门架构Excel.xls" data-placement="top" data-original-title="请下载导入部门架构Excel模板"> Excel模板下载-->
				</a>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn default">取消</button>
		<a id="uploadDepartmentExcelFileSubmit"  class="btn green">
				<i class="fa fa-check"></i> 提交
		</a>
	</div>
</div>
<!--END MODAL-->

<script src="${base}/assets/custom/scripts/idx/idx_list_main.js" type="text/javascript"></script>

