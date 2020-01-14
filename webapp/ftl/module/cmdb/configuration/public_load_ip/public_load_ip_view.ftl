<div class="row" id="public_load_ip-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-road"></i> 公网IP信息
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-horizontal">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-4 control-label">ip</label>
							<div class="col-md-8">
								<label class="control-label">{{publicLoadIp.ip}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">明细</label>
							<div class="col-md-8">
								<label class="control-label">{{publicLoadIp.remarks}}</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-road"></i> 负载IP
				</div>
				<!--<a class="ajaxify pull-right" href="configuration/public_ip_relevance/add?displayName=映射&publicAndloadip_id=${id}" module_id="module_public_ip_relevance_add" style="color: #fff">
					<i class="fa fa-plus-square-o"></i>
					添加映射
				</a>-->
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td>ip</td>
							<td>类型</td>
							<td>关系</td>
							<td>端口</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="publicIpRelevance in publicIpRelevances">
							<td>
								<a class="btn btn-xs ajaxify" :module_id="publicIpRelevance | relevanceModuleId" :href="publicIpRelevance | relevanceHref" data-html="true">{{publicIpRelevance.ip}}</a>
								<a class="hidden"><i class="fa fa-eye"></i> 详情</a>
							</td>
							<td>
								<span v-if="publicIpRelevance.target=='public_load_ip'">公网</span>
								<span v-if="publicIpRelevance.target=='assigned_ip'">物理</span>
								<span v-if="publicIpRelevance.target=='vip'">vip</span>
							</td>
							<td>{{publicIpRelevance.relation}}</td>
							<td>{{publicIpRelevance.port}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	</div>
	
	<div class="col-md-4">
		<#include "../platform/platform_portlet.ftl" />
	</div>
	
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/public_load_ip/public_load_ip_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>