<div class="row" id="platform-${id}">
	<div class="col-md-4">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-dashboard"></i>平台信息
				</div>
			</div>
			<div id="platform-platform-content-${id}" class="portlet-body">
				<#include "platform_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<div class="col-md-4">
		<#assign chiefplatformId="platform-chief-platform-content">
		<#include "../chiefplatform/chiefplatform_portlet.ftl" />
	</div>
	
	<div class="col-md-4">
		<#assign ippoolId="platform-ippool-content">
		<#include "../ippool/ippool_portlet.ftl" />
	</div>
	
	<div class="col-md-4 pull-right" id="mainframe-view-list-content-${id}">
		<#assign hostFkid="belongs_to_PlatForm_id">
		<#assign tabName="platform.platform_name">
		<#include "../mainframe/mainframe_list_portlet.ftl" />
	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/platform/platform_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>