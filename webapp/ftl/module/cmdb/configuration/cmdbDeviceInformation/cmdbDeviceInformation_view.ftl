<div class="row" id="cmdbDeviceInformation-content-${id}">
	<div class="col-md-6">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-sitemap"></i>资产信息
				</div>
			</div>
			<div class="portlet-body">
				<#include "cmdbDeviceInformation_fragment.ftl" />
			</div>
		</div>
	</div>
	<div class="col-md-6">
	<#include "../cmdbDeviceModel/cmdbDeviceModel_portlet.ftl" />
	</div>
	<div class="col-md-6">
	<#include "../cmdbRoom/cmdbRoom_portlet.ftl" />
	</div>
	<div class="col-md-6">
	<#include "../cmdbSupplier/cmdbSupplier_portlet.ftl" />
	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/cmdbDeviceInformation/cmdbDeviceInformation_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>