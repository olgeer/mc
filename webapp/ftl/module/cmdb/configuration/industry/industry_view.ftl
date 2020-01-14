<div class="row" id="industry-content-${industry_id}">
	<div class="col-md-4">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs"></i>标签信息
				</div>
			</div>
			<div class="portlet-body">
				<#include "industry_fragment.ftl" />
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
	require(['../custom/scripts/cmdb/configuration/industry/industry_view_init'], function(view) {
		view.init({
			industry_id: '${industry_id}'
		});
	});
</script>