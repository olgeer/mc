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
					<i class="fa fa-archive"></i>NAT列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<#-- <@security.authorize ifAnyGranted="ROLE_CONFIGURATION_APPLICATION_APPTYPE_EDIT"> -->
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_network_nat_add" href="configuration/cmdb_network_nat/add?displayName=NAT">
							<i class="fa fa-plus-square-o"></i> 添加NAT
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
								<th width="5%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<@dictSelect name="nat_type" type="ccnft_nat_type" class="form-control  form-filter input-sm"/>
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="nat_source_ip">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="nat_before_ip">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="nat_before_port">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="nat_after_ip">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="nat_after_port">
								</td>
								<td>
									<@dictSelect name="nat_status" type="cmdb_network_nat_status" class="form-control  form-filter input-sm"/>
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
	require(["jquery", "../custom/scripts/cmdb/configuration/network_nat/network_nat_list_init"], function($, page) {
		$(function() {
			Metronic.init();
			page.init({
				uuid: '${uuid}',
				hasEdit: 'true'
			});
		});
	});
</script>