<div class="portlet blue box" v-if="ipType.id">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-code-fork"></i>IP类型
		</div>
	</div>
	<div class="portlet-body">
		<a class="ajaxify" :module_id="ipType.id | concat('module_iptype_view_')" :href="ipType.id | concat('configuration/iptype/view?id=')" title="IP类型详情" data-html="true">
			<#include "iptype_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> IP类型详情</a>
	</div>
</div>
	