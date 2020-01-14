<div class="row" id="assignedip-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-magic"></i>物理IP信息
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-horizontal">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-4 control-label">ip</label>
							<div class="col-md-8">
								<label class="control-label">{{assignedip.ip}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">vlan</label>
							<div class="col-md-8">
								<label class="control-label">{{assignedip.vlan}}</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<#include "../mainframe/mainframe_portlet.ftl" />
	</div>
	
	<div class="col-md-4">
		<#include "../public_load_ip/public_load_ip_portlet.ftl" />
		<#include "../iptype/iptype_portlet.ftl" />
	</div>
	
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/assignedip/assignedip_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>