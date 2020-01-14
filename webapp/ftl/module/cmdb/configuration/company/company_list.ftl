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
					<i class="fa fa-gears"></i>会员列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<@security.authorize ifAnyGranted="ROLE_COMPANY_EDIT">
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_user_add" href="configuration/t_user/add?displayName=用户">
								<i class="fa fa-plus-square-o"></i> 1、添加用户
							</a>
						</div>
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_company_add" href="configuration/t_company/add?displayName=企业">
							<i class="fa fa-plus-square-o"></i> 2、添加企业
							</a>
						</div>

						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_auth_add" href="configuration/t_authrecord/add?displayName=认证">
								<i class="fa fa-plus-square-o"></i> 3、添加用户认证
							</a>
						</div>
					</@security.authorize>
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="company_datatable_${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th width="10%"></th>
								<th></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="6%"></th>
								<th width="6%"></th>
								<th width="10%"></th>
								<th width="15%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="text" class="form-control form-filter input-sm" name="cp_id">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="company_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="user_mobile">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="incorporator_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="company_connector">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="company_tel">
								</td>
								<td>
									<select class="form-control form-filter input-sm init-condition" name="company_type">
										<option value="" selected="selected"> 请选择 </option>
										<option value="-1"> 初始 </option>
										<option value="0"> 普通 </option>
										<option value="1"> 需求方 </option>
										<option value="2"> 服务方 </option>
										<option value="3"> 找钱VIP服务方 </option>
										<option value="4"> 赚钱VIP服务方 </option>
										<option value="5"> 省钱VIP服务方 </option>
										<option value="6"> 全渠道服务方 </option>
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
<script src="${base}/assets/custom/scripts/cmdb/configuration/company/company_list_main.js"></script>
<#--END PAGE JAVASCRIPT-->
<script type="text/javascript">
	require(["jquery", "company_list_init","jquery_validator_messages", "bootstrap_datepicker", "bootstrap_modal"],
		function($, companyListInit) {
			$(document).ready(function() {
				Metronic.init();
				companyListInit.init({
					uuid: '${uuid}',
					_sortcol: 'create_time',
					_sortdir: 'desc',
					hasEdit: '<@security.authorize ifAnyGranted="ROLE_COMPANY_EDIT">true</@security.authorize>'
					// hasEdit:'true'
				});
			});
		});
</script>