<style type="text/css">
	.application-td {
		max-width: 150px;
		overflow: hidden;
	}
</style>
<div class="row" id="el-${uuid}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-server"></i>主机信息
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-horizontal">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-4 control-label">主机名</label> 
							<div class="col-md-8">
								<label class="control-label">{{host.host_name}}</label>
							</div>
						</div> 
						<div class="form-group">
							<label class="col-md-4 control-label">主机类型</label> 
							<div class="col-md-8">
								<label class="control-label">{{host.host_type_value}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">应用类型</label> 
							<div class="col-md-8">
								<label class="control-label">{{host.application_type_value}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">主机状态</label> 
							<div class="col-md-8">
								<label class="control-label">{{host.host_status_value}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">cpu</label> 
							<div class="col-md-8">
								<label class="control-label">{{host.cpu}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">硬盘</label> 
							<div class="col-md-8">
								<label class="control-label">{{host.disk}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">内存</label> 
							<div class="col-md-8">
								<label class="control-label">{{host.memory}}</label>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		
		<div class="portlet blue box"  v-if="ips.length > 0">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-magic"></i>主机IP信息
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td>ip</td>
							<td>网关</td>
							<td>掩码</td>
							<td>vlan</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="ip in ips">
							<td>{{ip.ip}}</td>
							<td>{{ip.gateway}}</td>
							<td>{{ip.netmask}}</td>
							<td>{{ip.vlan}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	</div>
	
	<div class="col-md-4">
		<#--
		<#assign deviceId="mainframe-device-content">
		<#include "../device/device_portlet.ftl" /> -->
	</div>
	
	<div class="col-md-4" v-if="platform.id">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-dashboard"></i>平台信息
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-horizontal">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-4 control-label">平台名称</label>
							<div class="col-md-8">
								<label class="control-label">{{platform.platform_name}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">所属公司</label>
							<div class="col-md-8">
								<label class="control-label">{{platform.company}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">管理员</label>
							<div class="col-md-8">
								<label class="control-label">{{platform.manager}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">管理员电话</label>
							<div class="col-md-8">
								<label class="control-label">{{platform.manager_tel}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">开发者</label>
							<div class="col-md-8">
								<label class="control-label">{{platform.developer}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">开发者电话</label>
							<div class="col-md-8">
								<label class="control-label">{{platform.developer_tel}}</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-md-4">
	<#--
		<#assign chiefplatformId="mainframe-chief-platform-content">
		<#include "../chiefplatform/chiefplatform_portlet.ftl" />-->
	</div>
	
	<div class="col-md-4">
	<#--
		<#assign ostypeId="mainframe-os-type-content">
		<#include "../ostype/ostype_portlet.ftl" />-->
	</div>
	
	<div class="col-md-4">
		<#--
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
		-->
	</div>
</div>

<script type="text/javascript">
	require(['../custom/scripts/cmdb/configuration/cmdb_view_init'], function(view) {
		view.init({
			id: '${id}',
			uuid: '${uuid}',
			loadDatas: [{
				name: 'host',
				url: '/mc?_tablename=cmdb_host&_action=get',
				params: {id: 'id'},
				codes: {
					host_type: 'cmdb_host_type', 
					application_type: 'cmdb_host_application_type', 
					host_status: 'cmdb_host_status'
				}
			},{
				name: 'ips',
				url: '/mc?_tablename=cmdb_host_ip_view&_action=get',
				params: {host_id: 'id'}
			},{
				dept: 'host',
				name: 'platform',
				url: '/mc?_tablename=cmdb_platform&_action=get',
				params: {id: 'belong_to_platform_id'},
			}]
		});
	});
</script>