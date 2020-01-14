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
					<i class="fa fa-building-o"></i>设备型号列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_BASIS_CMDBDECIVEMODEL_EDIT">
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_cmdbDeviceModel_add" href="configuration/cmdb_device_model/add?displayName=设备型号">
							<i class="fa fa-plus-square-o"></i> 设备型号
							</a>
						</div>
					</@security.authorize>
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="cmdbDeviceModel_datatable_${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th width="20%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="15%"></th>
								<th width="10%"></th>
								<th ></th>
								<th width="20%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="text" class="form-control form-filter input-sm" name="device_model">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="device_type">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="device_height">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="cpu_total_core">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="memory_total">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="disk_capacity_total">
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


<#--BEGIN PAGE JAVASCRIPT -->
<script src="${base}/assets/custom/scripts/cmdb/configuration/cmdbDeviceModel/cmdbDeviceModel_list_main.js"></script>
<#--END PAGE JAVASCRIPT-->
<script type="text/javascript">
	require(["jquery", "cmdbDeviceModel_list_init","jquery_validator_messages", "bootstrap_datepicker", "bootstrap_modal"],
		function($, cmdbDeviceModelListInit) {
			$(document).ready(function() {
				Metronic.init();
                cmdbDeviceModelListInit.init({
					uuid: '${uuid}',
					hasEdit: '<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_BASIS_CMDBDECIVEMODEL_EDIT">true</@security.authorize>'
				});
			});
		});
</script>