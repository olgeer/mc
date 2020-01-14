<div class="row" id="chiefplatform-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-building-o"></i>项目信息
				</div>
			</div>
			<div id="chiefplatform-chief-platform-content-${id}" class="portlet-body">
				<#include "chiefplatform_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<div class="col-md-4">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-dashboard"></i>平台信息
				</div>
				<a v-if="platforms.length > 0" class="ajaxify pull-right" data-html="true" href="configuration/platform/list?belongs_to_chief_id=${id}" module_id="module_platform_${id}" style="color: #fff">
					<i class="fa fa-list-ul"></i>
					查看更多
				</a>
				<a class="hidden">
					<i class="fa fa-dashboard"></i>
					{{chiefPlatform.chief_platform_name}} 平台
				</a>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td>平台名称</td>
							<td>所属公司</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="platform in platforms">
							<td>
								<a class="btn btn-xs ajaxify" :module_id="platform.id | concat('module_platform_view_')" :href="platform.id | concat('configuration/platform/view?id=')" data-html="true">{{platform.platform_name}}</a>
								<a class="hidden"><i class="fa fa-eye"></i> 平台详情</a>
							</td>
							<td>{{platform.company}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/chiefplatform/chiefplatform_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>