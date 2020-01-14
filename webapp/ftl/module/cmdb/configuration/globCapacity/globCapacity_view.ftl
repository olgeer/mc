<div class="row" id="globCapacity-main-content-${belong_to_detail_object_id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-server"></i>容量信息
				</div>
			</div>
			<div  class="portlet-body" style="min-height: 0px;position: relative;">
				<#include "globCapacity_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<div class="col-md-4">
		<#include "../cmdbLicence/cmdbLicence_portlet.ftl" />
	</div>

</div>

<script type="text/javascript">
	require(['../custom/scripts/cmdb/configuration/globCapacity/globCapacity_view_init'], function(view) {
		view.init({
			belong_to_detail_object_id: '${belong_to_detail_object_id}'
		});
	});
</script>