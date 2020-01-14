<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-building-o"></i>供应商信息
		</div>
	</div>
	<div  class="portlet-body">
		<a class="ajaxify" :module_id="cmdbSupplier.id | concat('module_cmdbSupplier_view_')" :href="cmdbSupplier.id | concat('configuration/cmdbSupplier/view?id=')" title="供应商详情" data-html="true">
			<#include "cmdbSupplier_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 供应商详情</a>
	</div>
</div>