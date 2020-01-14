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
					<i class="fa fa-code-fork"></i>IP类型列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_NETWORK_IPTYPE_EDIT">
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_iptype_add" href="configuration/iptype/add?displayName=IP类型">
							<i class="fa fa-plus-square-o"></i> 添加IP类型
							</a>
						</div>
					</@security.authorize>
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th></th>
								<th width="8%"></th>
								<th width="8%"></th>
								<th width="8%"></th>
								<th width="8%"></th>
								<th width="8%"></th>
								<th width="10%"></th>
								<th width="8%"></th>
								<th width="15%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="text" class="form-control form-filter input-sm" name="typename">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="network_area">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="network_zone">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="start">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="end">
								</td>
								<td>
									<@dictSelect name="order" type="ip_order" class="form-control form-filter input-sm" />
								</td>
								<td>
									<select class="form-control form-filter input-sm" name="ip_type">
										<option value="">全部</option>
										<option value="VM">VM</option>
										<option value="PM">PM</option>
										<option value="VIP">VIP</option>
										<option value="负载">负载</option>
									</select>
								</td>
								<td>
									<@dictSelect name="in_pairs" type="ip_in_pairs" class="form-control form-filter input-sm" />
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
	require(["jquery", "../custom/scripts/cmdb/configuration/iptype/iptype_list_init"], function($, page) {
		$(function() {
			Metronic.init();
			page.init({
				uuid: '${uuid}',
				hasEdit: '<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_NETWORK_IPTYPE_EDIT">true</@security.authorize>'
			});
		});
	});
</script>