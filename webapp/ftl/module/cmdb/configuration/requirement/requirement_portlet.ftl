<div class="portlet blue box" v-if="requires.length > 0">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>需求列表
		</div>
		<@security.authorize ifAnyGranted="ROLE_COMPANY_EDIT">
			<div class="tools">
				<a class="ajaxify"
				   :module_id="company.company_id | concat('module_require_list_')" :href="company.company_id | concat('configuration/requirement/list?company_id=')" title="需求列表" data-html="true">
					<i class="fa fa-pencil"></i>更多需求
				</a>
				<a class="hidden"><i class="fa fa-pencil"></i> 需求列表</a>
			</div>
		</@security.authorize>
	</div>
	<div id="${companyId}-require-${company_id}" class="portlet-body">
		<#include "requirement_fragment2.ftl" />
	</div>
</div>