<div class="row" id="device-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-book"></i>设备信息
				</div>
			</div>
			<div id="device-device-content-${id}" class="portlet-body">
				<#include "device_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<div class="col-md-4">
		<#include "../room/room_portlet.ftl" />
	</div>
	
	<div v-if="hosts.length > 0" class="col-md-4">
		<#assign hostFkid="belongs_to_device_id">
		<#assign tabName="device.maintenance_code">
		<#include "../mainframe/mainframe_list_portlet.ftl" />
	</div>
	
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/device/device_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>