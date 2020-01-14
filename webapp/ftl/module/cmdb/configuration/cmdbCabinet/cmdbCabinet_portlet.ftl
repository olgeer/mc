<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-building-o"></i>项目信息
		</div>
	</div>
	<div id="${chiefplatformId}-${id}" class="portlet-body">
		<a class="ajaxify"
				:module_id="chiefPlatform.id | concat('module_chiefplatform_view_')" :href="chiefPlatform.id | concat('configuration/chiefplatform/view?id=')" title="项目详情" data-html="true">
			<#include "cmdbCabinet_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 项目详情</a>
	</div>
</div>