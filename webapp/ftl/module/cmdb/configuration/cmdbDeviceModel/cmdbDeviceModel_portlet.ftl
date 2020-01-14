<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-building-o"></i>型号信息
		</div>
	</div>
	<div  class="portlet-body">
		<a class="ajaxify" :module_id="cmdbDeviceModel.id | concat('module_cmdbDeviceModel_view_')" :href="cmdbDeviceModel.id | concat('configuration/cmdbDeviceModel/view?id=')" title="型号详情" data-html="true">
			<#include "cmdbDeviceModel_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 型号详情</a>
	</div>
</div>