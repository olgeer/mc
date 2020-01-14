<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-book"></i>设备信息
		</div>
	</div>
	<div id="${deviceId}-${id}" class="portlet-body">
		<a v-if="device.id" class="ajaxify"
				:module_id="device.id | concat('module_device_view_')" :href="device.id | concat('configuration/device/view?id=')" title="设备详情" data-html="true">
			<#include "../device/device_fragment.ftl" />
		</a>
		<div v-else>
			<#include "../device/device_fragment.ftl" />
		</div>
		<a class="hidden"><i class="fa fa-eye"></i> 设备详情</a>
	</div>
</div>