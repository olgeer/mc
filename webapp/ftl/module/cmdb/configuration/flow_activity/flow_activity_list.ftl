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
					<i class="fa fa-archive"></i>流程步骤分析
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
				
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr role="row" class="heading">
								<th>流程名称</th>
								<th>步骤名称</th>
								<th>执行角色</th>
								<th>最短用时</th>
								<th>平均用时</th>
								<th>最长用时</th>
								<th width="15%">操作</th>
							</tr>
							<tr id="${uuid}_search" role="row" class="filter">
								<td colspan="6">
									<div style="float: right;" class="input-group input-large date-picker input-daterange" data-date-format="yyyy-dd-mm">
										<input type="text" class="form-control" name="_start" placeholder="开始时间">
										<span class="input-group-addon"> 到 </span>
										<input type="text" class="form-control" name="_end" placeholder="结束时间">
									</div>
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
						<tbody  id="${uuid}">
							
						</tbody>
					</table>
				</div>
				<template id="${uuid}_tpl">
					<tbody>
						<tr v-for="item in items">
							<td>{{item.process_name}}</td>
							<td>{{item.task_name}}</td>
							<td>{{item.task_role_name}}</td>
							<td>{{formatSeconds(item.min_time)}}</td>
							<td>{{formatSeconds(item.avg_time)}}</td>
							<td>{{formatSeconds(item.max_time)}}</td>
							<td></td>
						</tr>
					</tbody>
				</template>
			</div>
		</div>
		<#-- End: life time stats -->
	</div>
</div>
<#-- END PAGE CONTENT -->

<script>
	require(["jquery", "../custom/scripts/cmdb/configuration/flow_activity/flow_activity_list_init"], function($, page) {
		$(function() {
			Metronic.init();
			page.init({
				uuid: '${uuid}',
				processName: '${processName}',
				hasEdit: 'true'
			});
		});
	});
</script>