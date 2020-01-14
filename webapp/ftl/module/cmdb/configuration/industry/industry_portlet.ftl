<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>标签信息
		</div>
	</div>
	<div id="${companyId}-${company_id}" class="portlet-body">
		<a class="ajaxify"
				:module_id="industry.industry_id | concat('module_industry_view_')" :href="industry.industry_id | concat('configuration/industry/view?industry_id=')" title="标签详情" data-html="true">
			<#include "industry_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 标签详情</a>
	</div>
</div>