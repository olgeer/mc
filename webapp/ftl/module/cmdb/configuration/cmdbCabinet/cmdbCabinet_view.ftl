<div class="row" id="cmdbCabinet-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-building-o"></i>机柜信息
				</div>
			</div>
			<div id="cmdbCabinet-cmdb_cabinet-content-${id}" class="portlet-body">
				<#include "cmdbCabinet_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<div class="col-md-4">

    </div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/cmdbCabinet/cmdbCabinet_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>