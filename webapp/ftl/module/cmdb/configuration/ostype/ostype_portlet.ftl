<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>系统信息
		</div>
	</div>
	<div id="${ostypeId}-${id}" class="portlet-body">
		<a class="ajaxify"
				:module_id="osType.id | concat('module_ostype_view_')" :href="osType.id | concat('configuration/ostype/view?id=')" title="系统详情" data-html="true">
			<#include "ostype_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 系统详情</a>
	</div>
</div>