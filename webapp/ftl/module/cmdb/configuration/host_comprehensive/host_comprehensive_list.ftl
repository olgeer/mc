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
					<i class="fa fa-archive"></i>总览信息
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input type="text" class="form-control form-filter input-sm" name="platform_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="host_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="logic_area">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="ip">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="vip_ip">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="load_ip">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="public_ip">
								</td>
								<td>
									<select class="form-control form-filter input-sm init-condition" name="app_type">
										<option value=""> 请选择 </option>
										<option value="0">APP</option>
										<option value="1">CACHE</option>
										<option value="2">DB</option>
										<option value="3">JUMP</option>
										<option value="4">MAG</option>
										<option value="5">MSG</option>
										<option value="6">SER</option>
										<option value="7">VMHOST</option>
										<option value="8">WEB</option>
									</select>
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="services">
								</td>
								<td>
									<select class="form-control form-filter input-sm init-condition" name="host_type">
										<option value=""> 请选择 </option>
										<option value="0">生产</option>
										<option value="1">下线</option>
										<option value="2">维修</option>
										<option value="3">未上线</option>
									</select>
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
	require(["jquery", "../custom/scripts/cmdb/configuration/host_comprehensive/host_comprehensive_list_init"], function($, page) {
		$(function() {
			Metronic.init();
			page.init({
				uuid: '${uuid}',
				hasEdit: 'true'
			});
		});
	});
</script>