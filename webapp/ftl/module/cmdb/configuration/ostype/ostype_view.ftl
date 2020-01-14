<div class="row" id="ostype-content-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs"></i>系统信息
				</div>
			</div>
			<div class="portlet-body">
				<#include "ostype_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<div class="col-md-4">
		<#assign hostFkid="belongs_to_ostype_id">
		<#assign tabName="osType.templatename">
		<#include "../mainframe/mainframe_list_portlet.ftl" />
	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/ostype/ostype_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>