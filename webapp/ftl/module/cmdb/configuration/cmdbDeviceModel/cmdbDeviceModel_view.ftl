<div class="row" id="cmdbDeviceModel-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-building-o"></i>型号信息
				</div>
			</div>
			<div id="cmdbDeviceModel-cmdb_device_model-content-${id}" class="portlet-body">
				<#include "cmdbDeviceModel_fragment.ftl" />
			</div>
		</div>
	</div>

</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/cmdbDeviceModel/cmdbDeviceModel_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>