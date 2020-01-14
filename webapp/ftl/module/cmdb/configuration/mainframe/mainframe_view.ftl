<style type="text/css">
	.application-td {
		max-width: 150px;
		overflow: hidden;
	}
</style>
<div class="row" id="mainframe-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-server"></i>主机信息
				</div>
			</div>
			<div id="mainframe-view-content-${id}" class="portlet-body" style="min-height: 0px;position: relative;">
				<img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484285287586&di=fa1d838c45473d318ed475302bf10af4&imgtype=0&src=http%3A%2F%2Fimg.icpcw.com%2FArticle%2F2014%2F10%2F0a19f0347c5d0fc771b8abf19e659dba.jpg" style="position: absolute;right: 32px;top: 8px;width: 128px;">
				<#include "mainframe_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<div class="col-md-4">
		<#assign deviceId="mainframe-device-content">
		<#include "../device/device_portlet.ftl" />
	</div>
	
	<div class="col-md-4">
		<#include "../platform/platform_portlet.ftl" />
	</div>

	<div class="col-md-4">
		<#assign chiefplatformId="mainframe-chief-platform-content">
		<#include "../chiefplatform/chiefplatform_portlet.ftl" />
	</div>
	
	<div class="col-md-4">
		<#assign ostypeId="mainframe-os-type-content">
		<#include "../ostype/ostype_portlet.ftl" />
	</div>
	
	<div class="col-md-4">
		
		<div class="portlet blue box"  v-if="assignedips.length > 0">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-magic"></i>IP信息
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td>ip</td>
							<td>vlan</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="assignedip in assignedips">
							<td>
								<a :module_id="'module_assignedip_view_' + assignedip.id" :href="'configuration/assignedip/view?id=' + assignedip.id" data-html="true" class="btn btn-xs ajaxify">{{assignedip.ip}}</a>
								<a class="hidden"><i class="fa fa-eye"></i> {{assignedip.ip}} 详情</a></td>
							<td>{{assignedip.vlan}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<#include "../application/application_list_portlet.ftl" />
	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/mainframe/mainframe_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>