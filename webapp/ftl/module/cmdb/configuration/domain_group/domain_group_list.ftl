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
					<i class="fa fa-group"></i>域组列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_BASIS_DOMAINGROUP_EDIT">
						<div class="btn-group btn-group-sm">
							<a class="btn yellow ajaxify" module_id="module_domain_group_add" href="configuration/domain_group/add?displayName=域组">
								<i class="fa fa-plus-square-o"></i> 添加域组
							</a>
						</div>
						<div class="btn-group btn-group-sm">
							<a class="btn yellow ajaxify" module_id="module_domain_group_with_host_add" href="configuration/domain_group_with_host/add?displayName=域组主机关联">
								<i class="fa fa-plus-square-o"></i> 添加域组主机关联
							</a>
						</div>
					</@security.authorize>
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
									<input type="text" class="form-control form-filter input-sm" name="h.hostname">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="ai.ip">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="dg.group_name">
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
<script src="${base}/assets/custom/scripts/cmdb/configuration/configuration_list_main.js"></script>
<#--END PAGE JAVASCRIPT-->
<script>
	require(["jquery", "../custom/scripts/cmdb/configuration/domain_group/domain_group_list_init"], function($, page) {
		$(function() {
			Metronic.init();
			page.init({
				uuid: '${uuid}',
				hasEdit: '<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_BASIS_DOMAINGROUP_EDIT">true</@security.authorize>'
			});
		});
	});
</script>