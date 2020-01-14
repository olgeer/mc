<style type="text/css">
	.application-td {
		max-width: 150px;
		overflow: hidden;
	}
</style>
<div class="row" id="globRemObjList-content-${grol_obj_detail_id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-server"></i>时间提醒详情
				</div>
			</div>
			<div id="globRemObjList-view-content-${grol_obj_detail_id}" class="portlet-body" style="min-height: 0px;position: relative;">
				<#include "globRemObjList_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<div class="col-md-4">
		<#include "../cmdbLicence/cmdbLicence_portlet.ftl" />
	</div>
	
	
</div>

<script type="text/javascript">
	require(['../custom/scripts/cmdb/configuration/globRemObjList/globRemObjList_view_init'], function(view) {
		view.init({
			grol_obj_detail_id: '${grol_obj_detail_id}'
		});
	});
</script>