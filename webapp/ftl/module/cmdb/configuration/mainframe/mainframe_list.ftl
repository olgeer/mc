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
					<i class="fa fa-server"></i>主机列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_BASIS_MAINFRAME_EDIT">
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_mainframe_add" href="configuration/host/add?displayName=主机&_addMore=true">
							<i class="fa fa-plus-square-o"></i> 添加主机
							</a>
						</div>
					</@security.authorize>
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="mainframe_datatable_${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th width="10%"></th>
								<th width="25%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="6%"></th>
								<th width="8%"></th>
								<th width="10%"></th>
								<th width="28%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="hidden" name="h.belongs_to_ostype_id" value="${belongs_to_ostype_id!}">
									<input type="hidden" name="h.belongs_to_device_id" value="${belongs_to_device_id!}">
									<input type="hidden" name="h.belongs_to_PlatForm_id" value="${belongs_to_PlatForm_id!}">
									<input type="text" class="form-control form-filter input-sm" name="h.hostname">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="p.platform_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="h.host_mem">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="h.host_disk">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="h.host_cpu">
								</td>
								<td>
									<select class="form-control form-filter input-sm init-condition" name="h.host_status">
										<option value=""> 请选择 </option>
										<option value="0">生产</option>
										<option value="1">下线</option>
										<option value="2">维修</option>
										<option value="3">未上线</option>
									</select>
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="h.host_remarks">
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
<!--START MODAL-->
<div id="historyViewModel" class="modal fade" tabindex="-1" data-keyboard="false" tabindex="-1" data-width="960" style="margin-top:-1000px">
</div>
<!--END MODAL-->


<#--BEGIN PAGE JAVASCRIPT -->
<script src="${base}/assets/custom/scripts/cmdb/configuration/mainframe/mainframe_list_main.js"></script>
<#--END PAGE JAVASCRIPT-->

<script>
require(["jquery", "mainframe_list_init","jquery_validator_messages", "bootstrap_datepicker", "bootstrap_modal"],
		function($, mainframeListInit) {
			$(document).ready(function() {
				Metronic.init();
				mainframeListInit.init({
					uuid: '${uuid}',
					hasEdit: '<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_BASIS_MAINFRAME_EDIT">true</@security.authorize>'
				});
			});
		});
</script>