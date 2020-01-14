<div class="row" id="cmdbLicence-view-content-${licence_id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-server"></i>产品具体信息
				</div>
			</div>
			<div class="portlet-body" style="min-height: 485px;position: relative;">
			<img src="assets/custom/img/device/licence.png" style="position: absolute;right: 32px;top: 8px;width: 128px;">
				<#include "cmdbDeviceModelConfig_fragment.ftl" />
			</div>
		</div>
	</div>

	<div class="col-md-4">
		<#include "../deviceBrand/deviceBrand_fragment.ftl" />
	</div>
	
	<div class="col-md-4">
		<#include "../cmdbSupplier/cmdbSupplier_fragment.ftl" />
	</div>

	<div class="col-md-8">
		<#include "../globCapacity/globCapacityl_portlet.ftl" />
	</div>
	<div class="col-md-4">
	<#include "../globRemObjList/globRemObjList_portlet.ftl" />
	</div>
	
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/cmdbLicence/cmdbLicence_view_init'], function(view) {
		view.init({
		  licence_id:"${licence_id}"
		});
	});
</script>