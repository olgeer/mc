<div class="row" id="cmdbPlatform-${id}">
	<div class="col-md-6">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-building-o"></i>平台信息
				</div>
			</div>
			<div id="cmdbPlatform-cmdb-platform-content-${id}" class="portlet-body">
				<#include "cmdbPlatform_fragment.ftl" />
			</div>
		</div>
	</div>

  <<div class="col-md-6">
       <#include "../cmdbChiefPlatform/cmdbChiefPlatform_portlet.ftl" />
</div>

</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/cmdbPlatform/cmdbPlatform_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>