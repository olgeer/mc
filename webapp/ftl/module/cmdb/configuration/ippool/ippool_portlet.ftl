<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-th"></i>IP池信息
		</div>
	</div>
	<div id="${ippoolId}-${id}" class="portlet-body">
		<a v-if="ippool.ip_segment" class="ajaxify"
				:module_id="ippool.ip_segment | concat('module_ippool_view_')" :href="ippool.ip_segment | concat('configuration/ippool/view?id=')" title="IP池详情" data-html="true">
			<#include "ippool_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> IP池详情</a>
	</div>
</div>