<div class="row" id="score-content-${record_id}">
	<div class="col-md-4">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs"></i>企业积分信息
				</div>
			</div>
			<div class="portlet-body">
				<#include "score_fragment2.ftl" />
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
	require(['../custom/scripts/cmdb/configuration/score/score_view_init'], function(view) {
		view.init({
			record_id: '${record_id}'
		});
	});
</script>