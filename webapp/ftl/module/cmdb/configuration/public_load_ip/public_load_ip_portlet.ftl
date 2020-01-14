<div class="portlet blue box" v-if="publicLoadIps.length > 0">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-road"></i>公网
		</div>
	</div>
	<div class="portlet-body">
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<td>ip</td>
					<td>关系</td>
					<td>端口</td>
				</tr>
			</thead>
			<tbody>
				<tr v-for="publicLoadIp in publicLoadIps">
					<td>
						<a class="ajaxify" :module_id="publicLoadIp.pli.id | concat('module_public_load_ip_view_')" 
						:href="publicLoadIp.pli.id | concat('configuration/public_load_ip/view?id=')" 
						title="详情" data-html="true">{{publicLoadIp.pli.ip}}</a>
						<a class="hidden"><i class="fa fa-eye"></i> 详情</a>
					</td>
					<td>{{publicLoadIp.pir.relation}}</td>
					<td>{{publicLoadIp.pir.port}}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>