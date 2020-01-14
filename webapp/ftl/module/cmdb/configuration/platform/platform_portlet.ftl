<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-dashboard"></i>平台信息
		</div>
	</div>
	<div class="portlet-body">
		<a class="ajaxify"
				:module_id="platform.id | concat('module_platform_view_')" :href="platform.id | concat('configuration/platform/view?id=')" title="平台详情" data-html="true">
			<#include "../platform/platform_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 平台详情</a>
	</div>
</div>