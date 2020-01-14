<div class="row" id="cmdbRoom-content-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-sitemap"></i>机房信息
				</div>
			</div>
			<div class="portlet-body">
				<#include "cmdbRoom_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<div class="col-md-4">

	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/cmdbRoom/cmdbRoom_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>