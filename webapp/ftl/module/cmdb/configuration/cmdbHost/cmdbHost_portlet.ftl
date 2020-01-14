<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-sitemap"></i>机房信息
		</div>
	</div>
	<div class="portlet-body">
		<a class="ajaxify" :module_id="cmdbRoom.id | concat('module_cmdbRoom_view_')" :href="cmdbRoom.id | concat('configuration/cmdbRoom/view?id=')" title="机房详情" data-html="true">
			<#include "cmdbHost_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 机房详情</a>
	</div>
</div>