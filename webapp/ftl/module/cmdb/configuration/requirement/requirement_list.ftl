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
					<i class="fa fa-gears"></i>需求列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="requirement_datatable_${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="7%"></th>
								<th></th>
								<th width="10%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="text" class="form-control form-filter input-sm" name="req_id">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="cm_id">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="user_mobile">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="company_name">
								</td>
								<td>
									<select class="form-control form-filter input-sm init-condition" name="req_type">
										<option value="" selected="selected"> 请选择 </option>
										<option value="0"> 未知 </option>
										<option value="1"> 找钱 </option>
										<option value="2"> 赚钱 </option>
										<option value="3"> 省钱 </option>
									</select>
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="req_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="industry_name">
								</td>
								<td>
									<select class="form-control form-filter input-sm init-condition" name="state">
										<option value="" selected="selected"> 请选择 </option>
										<option value="0"> 发布中 </option>
										<option value="1"> 已抢单未满员 </option>
										<option value="2"> 满员 </option>
										<option value="3"> 已成交 </option>
										<option value="4"> 已失效 </option>
									</select>
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
<script src="${base}/assets/custom/scripts/cmdb/configuration/requirement/requirement_list_main.js"></script>
<#--END PAGE JAVASCRIPT-->
<script type="text/javascript">
	require(["jquery", "requirement_list_init","jquery_validator_messages", "bootstrap_datepicker", "bootstrap_modal"],
		function($, requirementListInit) {
			$(document).ready(function() {
				Metronic.init();
				requirementListInit.init({
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