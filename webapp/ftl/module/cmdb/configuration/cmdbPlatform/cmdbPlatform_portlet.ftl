<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-building-o"></i>平台信息
		</div>
	</div>
	<div  class="portlet-body">
		<a class="ajaxify"
				:module_id="cmdbPlatform.id | concat('module_cmdbPlatform_view_')" :href="cmdbPlatform.id | concat('configuration/cmdbPlatform/view?id=')" title="平台详情" data-html="true">
			<#include "cmdbPlatform_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 平台详情</a>
	</div>
</div>

