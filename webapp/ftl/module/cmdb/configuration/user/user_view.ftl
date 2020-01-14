<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"]/>
<div class="row" id="user-content-${user_id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs"></i>用户信息
				</div>
				<@security.authorize ifAnyGranted="ROLE_COMPANY_EDIT">
					<div class="tools">
						<a class="ajaxify"
						   :module_id="user.user_id | concat('module_user_edit_')" :href="user.user_id | concat('configuration/t_user/')+'?displayName='" title="编辑用户" data-html="true">
							<i class="fa fa-pencil"></i>编辑用户
						</a>
						<a class="hidden"><i class="fa fa-pencil"></i> 编辑用户</a>
					</div>
				</@security.authorize>
			</div>
			<div class="portlet-body">
				<#include "user_fragment2.ftl" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<#assign companyId="user.company_id">
		<#include "../company/company_portlet.ftl" />
	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/user/user_view_init'], function(view) {
		view.init({
			user_id: '${user_id}'
		});
	});
</script>