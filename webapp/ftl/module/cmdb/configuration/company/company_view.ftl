<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"]/>
<div class="row" id="company-content-${company_id}">
	<div class="col-md-4">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs"></i>企业及相关信息
				</div>
				<@security.authorize ifAnyGranted="ROLE_COMPANY_EDIT">
					<div class="tools">
						<a class="ajaxify"
						   :module_id="company.company_id | concat('module_company_edit_')" :href="company.company_id | concat('configuration/t_company/')+'?displayName='" title="编辑企业" data-html="true">
							<i class="fa fa-pencil"></i>编辑企业
						</a>
						<a class="hidden"><i class="fa fa-pencil"></i> 编辑企业</a>
					</div>
				</@security.authorize>
			</div>
			<div class="portlet-body">
				<#assign companyId="company_id">
				<#include "company_fragment.ftl" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<#include "../user/user_portlet.ftl" />
		<#include "../score/score_portlet.ftl" />
	</div>
	<div class="col-md-4">
		<#include "../requirement/requirement_portlet.ftl" />
		<#include "../service/service_portlet.ftl" />
	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/company/company_view_init'], function(view) {
		view.init({
			company_id: '${company_id}'
		});
	});
</script>