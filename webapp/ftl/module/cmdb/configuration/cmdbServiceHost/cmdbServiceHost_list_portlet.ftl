<div class="portlet blue box" v-if="!applicationHref || applications.length > 0">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-archive"></i>应用信息
		</div>
		<template v-if="applicationHref">
			<a v-if="applications.length > 0" class="ajaxify pull-right" data-html="true" href="configuration/application/list?host_id=${id}" module_id="module_application_host_${id}" style="color: #fff">
				<i class="fa fa-list-ul"></i>
				查看更多
			</a>
			<a class="hidden">
				<i class="fa fa-archive"></i>
				<span>{{host.hostname}}</span> 应用
			</a>
		</template>
	</div>
	<div class="portlet-body">
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<td>应用名称</td>
					<td>应用明细</td>
				</tr>
			</thead>
			<tbody>
				<tr v-for="application in applications">
					<td class="application-td" :title="application.a.application_name">
						<a class="btn btn-xs ajaxify" :module_id="application.a.id | concat('module_application_view_')" :href="application.a.id | concat('configuration/application/view?id=')" data-html="true">{{application.a.application_name}}</a>
						<a class="hidden"><i class="fa fa-eye"></i> 应用详情</a>
					</td>
					<td class="application-td" :title="application.a.application_remarks">{{application.a.application_remarks}}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>