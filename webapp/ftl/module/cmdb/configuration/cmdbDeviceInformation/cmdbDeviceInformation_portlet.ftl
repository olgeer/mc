<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-sitemap"></i>机房信息
		</div>
	</div>
	<div class="portlet-body">
		<a class="ajaxify"
				:module_id="cmdbDeviceInformation.id | concat('module_cmdbDeviceInformation_view_')" :href="cmdbDeviceInformation.id | concat('configuration/cmdb_device_information/view?id=')" title="资产详情" data-html="true">
			<#include "cmdbDeviceInformation_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 机房详情</a>
	</div>
</div>