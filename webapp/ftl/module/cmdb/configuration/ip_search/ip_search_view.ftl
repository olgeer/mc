<div id="${uuid}"></div>
<template id="template-${uuid}">
	<div style="width: 600px;margin: 64px auto;">
		<input type="text" class="form-control" name="" placeholder="请输入要搜索的ip" v-model.trim="ip" @keyup.enter="submit">
		
		<table v-if="rs.length > 0" class="table table-bordered table-striped" style="margin-top: 32px;">
			<thead>
				<tr>
					<td>ip</td>
					<td>类型</td>
				</tr>
			</thead>
			<tbody>
				<tr v-for="r in rs">
					<td>
						<a class="btn btn-xs ajaxify" :module_id="r | relevanceModuleId" :href="r | relevanceHref" data-html="true">{{r.ip}}</a>
						<a class="hidden"><i class="fa fa-eye"></i> 详情</a>
					</td>
					<td>{{r.typeName}}</td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/ip_search/ip_search_view_init'], function(view) {
		view.init({uuid: '${uuid}'});
	});
</script>