<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-th"></i>许可证信息
		</div>
	</div>
	<div  class="portlet-body">
		<a v-if="cmdbLicence" class="ajaxify"
				:module_id="cmdbLicence.licence_id | concat('module_cmdbLicence_view_')" :href="cmdbLicence.licence_id | concat('configuration/cmdbLicence/view?licence_id=')" title="许可证详情" data-html="true">
			<#include "cmdbLicence_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 许可证详情</a>
		
	</div>
	</div>
</div>