<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-building-o"></i>项目信息
		</div>
	</div>
	<div  class="portlet-body">
		<a class="ajaxify" :module_id="cmdbChiefPlatform.id | concat('module_cmdbChiefPlatform_view_')" :href="cmdbChiefPlatform.id | concat('configuration/cmdbChiefPlatform/view?id=')" title="项目详情" data-html="true">
			<#include "cmdbChiefPlatform_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 项目详情</a>
	</div>
</div>