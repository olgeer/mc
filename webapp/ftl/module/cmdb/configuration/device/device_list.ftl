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
					<i class="fa fa-book"></i>设备列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_BASIS_DEVICE_EDIT">
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_device_add" href="configuration/device/add?displayName=设备&_addMore=true">
							<i class="fa fa-plus-square-o"></i> 添加设备
							</a>
						</div>
					</@security.authorize>
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="device_datatable_${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th width="10%"></th>
								<th></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="12%"></th>
								<th width="12%"></th>
								<th width="6%"></th>
								<th width="15%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="hidden" name="belongs_to_room_id" value="${belongs_to_room_id!}">
									<input type="text" class="form-control form-filter input-sm" name="maintenance_code">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="device_cpu">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="device_mem">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="device_disk">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="room_col" placeholder="第几列">
									<input type="text" class="form-control form-filter input-sm" name="col_index" placeholder="第几个">
								</td>
								<td>
									<!--<input type="date" class="form-control form-filter input-sm" name="purchase_date">-->
									<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
										<input type="text" class="form-control form-filter input-sm" readonly name="purchase_date" placeholder="购买日期">
										<span class="input-group-btn">
											<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
								</td>
								<td>
									<!--<input type="date" class="form-control form-filter input-sm" name="purchase_date">-->
									<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
										<input type="text" class="form-control form-filter input-sm" readonly name="warranty_date" placeholder="保修日期">
										<span class="input-group-btn">
											<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
								</td>
								<td>
									<select class="form-control form-filter input-sm init-condition" name="device_status">
										<option value=""> 请选择 </option>
										<option value="0"> 上架 </option>
										<option value="1" selected="selected"> 生产 </option>
										<option value="2"> 维修 </option>
										<option value="3"> 下线 </option>
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


<#--BEGIN PAGE JAVASCRIPT -->
<script src="${base}/assets/custom/scripts/cmdb/configuration/device/device_list_main.js"></script>
<#--END PAGE JAVASCRIPT-->
<script>
require(["jquery", "device_list_init","jquery_validator_messages", "bootstrap_datepicker", "bootstrap_modal"],
		function($, deviceListInit) {
			$(document).ready(function() {
				Metronic.init();
				deviceListInit.init({
					uuid: '${uuid}',
					hasEdit: '<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_BASIS_DEVICE_EDIT">true</@security.authorize>'
				});
			});
		});
</script>