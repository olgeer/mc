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
					<i class="fa fa-magic"></i>公网与负载IP列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_NETWORK_PUBLICLOADIP_EDIT">
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_public_load_ip_add" href="configuration/public_load_ip/add?displayName=公网IP">
							<i class="fa fa-plus-square-o"></i> 添加公网IP
							</a>
						</div>
					</@security.authorize>
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th ></th>
								<th ></th>
								<th width="7%"></th>
								<th></th>
								<th width="8%"></th>
								<th></th>
								<th></th>
								<th width="15%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="text" class="form-control form-filter input-sm" name="p.platform_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="pli.ip">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="pir.relation">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="pir.ip">
								</td>
								<td>
									<select class="form-control form-filter input-sm" name="pir.target">
										<option value="">选择</option>
										<option value="public_load_ip">公网</option>
										<option value="assigned_ip">物理</option>
										<option value="vip">vip</option>
									</select>
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="pir.port">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="pli.remarks">
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
	require(["jquery", "../custom/scripts/cmdb/configuration/public_load_ip/public_load_ip_list_init"], function($, page) {
		$(function() {
			Metronic.init();
			page.init({
				uuid: '${uuid}',
				hasEdit: '<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_NETWORK_PUBLICLOADIP_EDIT">true</@security.authorize>'
			});
		});
	});
</script>