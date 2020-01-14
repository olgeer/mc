<div class="row" id="business-content-${business_id}">
	<div class="col-md-4">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs"></i>服务标签信息
				</div>
			</div>
			<div class="portlet-body">
				<#include "business_fragment.ftl" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
	</div>
	<div class="col-md-4">
	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/business/business_view_init'], function(view) {
		view.init({
			business_id: '${business_id}'
		});
	});
</script>