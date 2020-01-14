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
					<i class="fa fa-cog"></i>网络策略列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<#-- <@security.authorize ifAnyGranted="ROLE_CONFIGURATION_NETWORK_NETWORKPOLICY_EDIT">
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_network_policy_add" href="configuration/network_policy/add?displayName=网络策略">
							<i class="fa fa-plus-square-o"></i> 添加网络策略
							</a>
						</div>
					</@security.authorize> -->
					<div class="btn-group">
						<a class="btn yellow generate">
							<i class="fa fa-plus-square-o"></i> 生成VLAN
						</a>
					</div>
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th></th>
								<th></th>
								<th></th>
								<th width="15%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="hidden" class="form-control form-filter input-sm" name="vlan_pool_id" value="${vlan_pool_id!}">
									<input type="text" class="form-control form-filter input-sm" name="vlan">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="network_num">
								</td>
								<td>
									<@dictSelect name="status" type="cmdb_network_vlan_status" class="form-control form-filter input-sm" />
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
	require(["jquery", "../custom/scripts/cmdb/configuration/network_vlan/network_vlan_list_init"], function($, page) {
		$(function() {
			Metronic.init();
			page.init({
				uuid: '${uuid}',
				poolId: '${vlan_pool_id!}',
				hasEdit: 'true'
			});
		});
	});
</script>