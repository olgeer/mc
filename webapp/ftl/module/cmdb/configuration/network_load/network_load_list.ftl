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
					<i class="fa fa-archive"></i>负载均衡列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<#-- <@security.authorize ifAnyGranted="ROLE_CONFIGURATION_APPLICATION_APPTYPE_EDIT"> -->
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_network_load_add" href="configuration/cmdb_network_load/add?displayName=负载均衡">
							<i class="fa fa-plus-square-o"></i> 添加负载均衡
							</a>
						</div>
					<#-- </@security.authorize> -->
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th width=""></th>
								<th width=""></th>
								<th width=""></th>
								<th width=""></th>
								<th width=""></th>
								<th width=""></th>
								<th width=""></th>
								<th width=""></th>
								<th width=""></th>
								<th width=""></th>
								<th width="5%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="text" class="form-control form-filter input-sm" name="load_ip">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="load_port">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="real_ip">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="real_port">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="session_limit">
								</td>
								<td>
									<@dictSelect name="load_arithmetic" type="cmdb_clbt_load_algorithm" class="form-control  form-filter input-sm"/>
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="load_timeout">
								</td>
								<td>
									<@dictSelect name="encrypt" type="cmdb_network_load_is" class="form-control  form-filter input-sm"/>
								</td>
								<td>
									<@dictSelect name="decode" type="cmdb_network_load_is" class="form-control  form-filter input-sm"/>
								</td>
								<td>
									<@dictSelect name="status" type="cmdb_network_load_status" class="form-control  form-filter input-sm"/>
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
	require(["jquery", "../custom/scripts/cmdb/configuration/network_load/network_load_list_init"], function($, page) {
		$(function() {
			Metronic.init();
			page.init({
				uuid: '${uuid}',
				hasEdit: 'true'
			});
		});
	});
</script>