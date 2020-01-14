<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>用户信息
		</div>
		<@security.authorize ifAnyGranted="ROLE_COMPANY_EDIT">
			<div class="tools">
				<a class="ajaxify"
								  :module_id="company.user_id | concat('module_user_edit_')" :href="company.user_id | concat('configuration/t_user/')+'?displayName='" title="用户编辑" data-html="true">
					<i class="fa fa-pencil"></i>用户编辑
				</a>
				<a class="hidden"><i class="fa fa-pencil"></i> 用户详情</a>
			</div>
		</@security.authorize>
	</div>
	<div id="user-${companyId}" class="portlet-body">
		<#include "user_fragment.ftl" />
	</div>
</div>