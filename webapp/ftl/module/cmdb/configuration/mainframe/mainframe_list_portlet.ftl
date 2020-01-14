<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-server"></i>主机信息
		</div>
		<a v-if="hosts.length > 0" class="ajaxify pull-right" data-html="true" href="configuration/mainframe/list?${hostFkid}=${id}" module_id="module_host_${hostFkid}_${id}" style="color: #fff">
			<i class="fa fa-list-ul"></i>
			查看更多
		</a>
		<a class="hidden">
			<i class="fa fa-server"></i>
			{{${tabName}}} 主机
		</a>
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
						<a class="btn btn-xs ajaxify" :module_id="host.id | concat('module_mainframe_view_')" :href="host.id | concat('configuration/mainframe/view?id=')" data-html="true">{{host.hostname}}</a>
						<a class="hidden"><i class="fa fa-eye"></i> 主机详情</a>
					</td>
					<td>{{host.host_network_area}}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>