<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"]/>
<#--BEGIN PAGE LEVEL STYLES-->
<link rel="stylesheet" href="${base}/assets/global/plugins/data-tables/DT_bootstrap.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${base}/assets/global/plugins/bootstrap-datepicker/css/datepicker.css" />
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
<#--END PAGE LEVEL STYLES-->

<#-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<#-- Begin: life time stats -->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-archive"></i>流程实例统计
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th width=""></th>
								<th width=""></th>
								<th width=""></th>
								<th width=""></th>
								<th width=""></th>
								<th width="5%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="text" class="form-control form-filter input-sm" name="flow_no">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="process_title">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="_start">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="end_time">
								</td>
								<td>
									<select class="form-control form-filter input-sm init-condition" name="flow_status">
										<option value=""> 请选择 </option>
										<option value="0">进行中</option>
										<option value="1">已完成</option>
									</select>
								</td>
								<td class="text-center">
									<div class="margin-bottom-5">					
										<button class="btn btn-sm yellow filter-submit">
											<i class="fa fa-search"></i> 查询
										</button>
										<button class="btn btn-sm red filter-cancel">
											<i class="fa fa-times"></i> 重置
										</button>
									</div>
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
<#-- END PAGE CONTENT -->

<script>
	require(["jquery", "../custom/scripts/cmdb/configuration/flow_instance/flow_instance_list_init"], function($, page) {
		$(function() {
			Metronic.init();
			page.init({
				uuid: '${uuid}',
				hasEdit: 'true'
			});
		});
	});
</script>