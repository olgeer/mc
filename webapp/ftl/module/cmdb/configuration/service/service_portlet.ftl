<div class="portlet blue box" v-if="services.length > 0">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>需求分配列表
		</div>
	</div>
	<div id="${companyId}-service-${company_id}" class="portlet-body">
			<#include "service_fragment.ftl" />
	</div>
</div>