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
					<i class="fa fa-gears"></i>购买记录
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="score_datatable_${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th></th>
								<th></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th></th>
								<th></th>
								<th width="10%"></th>
								<th width="5%"></th>
								<th width="6%"></th>
								<th width="10%"></th>
								<th width="15%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="text" class="form-control form-filter input-sm" name="record_id">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="order_id">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="user_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="company_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="main_type">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="detail_type">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="score">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="score_avaid">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="state">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="create_time">
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
<script src="${base}/assets/custom/scripts/cmdb/configuration/score/score_list_main.js"></script>
<#--END PAGE JAVASCRIPT-->
<script type="text/javascript">
	require(["jquery", "score_list_init","jquery_validator_messages", "bootstrap_datepicker", "bootstrap_modal"],
		function($, scoreListInit) {
			$(document).ready(function() {
				Metronic.init();
				scoreListInit.init({
					uuid: '${uuid}',
					<#if company_id??>
					company_id: '${company_id}',
					</#if>
					<#--hasEdit: '<!@security.authorize ifAnyGranted="ROLE_CONFIGURATION_BASIS_OSTYPE_EDIT">true</@security.authorize>'-->
					hasEdit:'true'
				});
			});
		});
</script>