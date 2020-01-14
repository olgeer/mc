<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-server"></i>主机信息
		</div>
	</div>
	<div class="portlet-body">
		<a v-if="host.id" class="ajaxify"
				:module_id="host.id | concat('module_mainframe_view_')" :href="host.id | concat('configuration/mainframe/view?id=')" title="主机详情" data-html="true">
			<#include "mainframe_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 主机详情</a>
	</div>
</div>