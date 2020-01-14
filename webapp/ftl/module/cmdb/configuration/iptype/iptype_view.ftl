<div class="row" id="${uuid}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-code-fork"></i>网络设备信息
				</div>
			</div>
			<div class="portlet-body">
				<#include "iptype_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<div class="col-md-4">
		<#include "../room/room_portlet.ftl" />
	</div>
	
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/iptype/iptype_view_init'], function(view) {
		view.init({
			id: '${id}',
			uuid: '${uuid}'
		});
	});
</script>