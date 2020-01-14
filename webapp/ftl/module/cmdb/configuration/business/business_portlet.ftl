<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>系统信息
		</div>
	</div>
	<div id="${businessId}-${business_id}" class="portlet-body">
		<a class="ajaxify"
				:module_id="business.business_id | concat('module_business_view_')" :href="business.business_id | concat('configuration/business/view?business_id=')" title="服务标签详情" data-html="true">
			<#include "business_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 系统详情</a>
	</div>
</div>