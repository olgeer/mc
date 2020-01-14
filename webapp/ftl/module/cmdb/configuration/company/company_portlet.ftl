<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>企业信息
		</div>
		<@security.authorize ifAnyGranted="ROLE_COMPANY_EDIT">
			<div class="tools">
				<a class="ajaxify"
				   :module_id="user.company_id | concat('module_company_edit_')" :href="user.company_id | concat('configuration/t_company/')+'?displayName='" title="编辑企业" data-html="true">
					<i class="fa fa-pencil"></i>编辑企业
				</a>
				<a class="hidden"><i class="fa fa-pencil"></i> 编辑企业</a>
			</div>
		</@security.authorize>
	</div>
	<div id="company-${companyId}" class="portlet-body">
		<a class="ajaxify"
				:module_id="user.company_id | concat('module_company_view_')" :href="user.company_id | concat('configuration/company/view?company_id=')" title="企业详情" data-html="true">
			<#include "company_fragment2.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 企业详情</a>

	</div>
</div>