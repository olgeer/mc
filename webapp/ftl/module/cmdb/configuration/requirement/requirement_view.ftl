<div class="row" id="requirement-content-${req_id}">
	<div class="col-md-4">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs"></i>需求信息
				</div>
				<div class="tools">
					<a class="ajaxify" module_id="module_assign_add" href="configuration/t_requirementassign/add?displayName=">
						<i class="fa fa-plus-square-o"></i> 添加匹配企业
					</a>
					<a class="hidden"><i class="fa fa-pencil"></i> 添加匹配企业</a>
				</div>
			</div>
			<div class="portlet-body">
				<#include "requirement_fragment.ftl" />
			</div>
		</div>
	</div>
	<div class="col-md-8">
		<#include "detail_portlet.ftl" />
	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/requirement/requirement_view_init'], function(view) {
		view.init({
			req_id: '${req_id}'
		});
	});
</script>