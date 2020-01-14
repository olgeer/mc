<div class="row" id="cmdbChiefPlatform-${id}">
	<div class="col-md-6">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-building-o"></i>项目信息
				</div>
			</div>
			<div id="cmdbChiefPlatform-cmdb-chief-platform-content-${id}" class="portlet-body">
				<#include "cmdbChiefPlatform_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<#--<div class="col-md-6">
	<#include "../cmdbPlatform/cmdbPlatform_portlet.ftl" />
	</div>-->

</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/cmdbChiefPlatform/cmdbChiefPlatform_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>