<div class="row" id="application-${id}">
	<div class="col-md-8">
		<!-- BEGIN ALERTS PORTLET-->
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-archive"></i>应用信息
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-horizontal">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-4 control-label">应用名称</label>
							<div class="col-md-8">
								<label class="control-label">{{application.application_name}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">主机名</label>
							<div class="col-md-8">
								<label class="control-label">{{application.hostname}}</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END ALERTS PORTLET-->
	</div>
	
	<div class="col-md-4">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-server"></i>主机信息
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td>主机名称</td>
							<td>网络区域</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="host in hosts">
							<td>
								<a class="btn btn-xs ajaxify" :module_id="host.h.id | concat('module_mainframe_view_')" :href="host.h.id | concat('configuration/mainframe/view?id=')" data-html="true">{{host.h.hostname}}</a>
								<a class="hidden"><i class="fa fa-eye"></i> 主机详情</a>
							</td>
							<td>{{host.h.host_network_area}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-server"></i>vip信息
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td>vip</td>
							<td>vlan</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="vip in vips">
							<td>
								<a class="btn btn-xs ajaxify" :module_id="vip.v.id | concat('module_vip_view_')" :href="vip.v.id | concat('configuration/vip/view?id=')" data-html="true">{{vip.v.ip}}</a>
								<a class="hidden"><i class="fa fa-eye"></i> {{vip.v.ip}}详情</a>
							</td>
							<td>{{vip.v.vlan}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	</div>
	
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/application/application_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>