<div class="row" id="ippool-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-th"></i>IP池信息
				</div>
			</div>
			<div class="portlet-body">
				<#include "ippool_fragment.ftl" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<#include "../iptype/iptype_portlet.ftl" />
		<#include "../platform/platform_portlet.ftl" />
	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/ippool/ippool_view_init'], function(view) {
		view.init({
			ip_segment: '${id}'
		});
	});
</script>