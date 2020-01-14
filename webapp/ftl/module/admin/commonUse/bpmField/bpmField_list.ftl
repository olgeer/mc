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
					<i class="fa fa-server"></i>字段配置列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<@security.authorize ifAnyGranted="Role_COMMON_MANAGER_BPMFIELD_EDIT">
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_bpmField_add" href="admin/commonUse/bpm_field/add?displayName=字段配置">
							<i class="fa fa-plus-square-o"></i> 添加字段配置
							</a>
						</div>
					</@security.authorize>
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="bpmField_datatable_${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th width="23%"></th>
								<th width="23%"></th>
								<th width="20%"></th>
								<th width="20%"></th>
								<th ></th>
								<th width="16%">操作</th>
							</tr>
							<tr role="row" class="filter">
							<td>
									<input type="text" class="form-control form-filter input-sm" name="bpfo_name">
								</td>
							<td>
									<input type="text" class="form-control form-filter input-sm" name="bpta_zh_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="bpta_en_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="bpfi_zh_field">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="bpfi_en_field">
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
<script src="${base}/assets/custom/scripts/admin/commonUse/bpmField/bpmField_list_main.js"></script>
<#--END PAGE JAVASCRIPT-->

<script>
require(["jquery", "bpmField_list_init","jquery_validator_messages", "bootstrap_datepicker", "bootstrap_modal"],
		function($, bpmFieldListInit) {
			$(document).ready(function() {
				Metronic.init();
				bpmFieldListInit.init({
					uuid: '${uuid}',
					hasEdit: '<@security.authorize ifAnyGranted="Role_COMMON_MANAGER_BPMFIELD_EDIT">true</@security.authorize>'
				});
			});
		});
</script>