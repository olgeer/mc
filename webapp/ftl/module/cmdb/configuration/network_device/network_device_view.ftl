<div class="row" id="${uuid}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-puzzle-piece"></i>网络设备信息
				</div>
			</div>
			<div class="portlet-body">
				<div class="form-horizontal">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-4 control-label">设备编号</label>
							<div class="col-md-8">
								<label class="control-label">{{networkDevice.device_num}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">设备名称</label>
							<div class="col-md-8">
								<label class="control-label">{{networkDevice.device_name}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">接口</label>
							<div class="col-md-8">
								<label class="control-label">{{networkDevice.port}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">VC名称</label>
							<div class="col-md-8">
								<label class="control-label">{{networkDevice.vc}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">备注</label>
							<div class="col-md-8">
								<label class="control-label">{{networkDevice.remarks}}</label>
							</div>
						</div>
						<div class="form-group" v-if="networkDevice.binding_device_id && networkDevice.bindingDevice">
							<label class="col-md-4 control-label">绑定设备</label>
							<div class="col-md-8">
								<label class="control-label">{{networkDevice.bindingDevice.device_num}}</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-md-4">
		
		<div class="portlet blue box" v-if="deviceAssignedips.length > 0">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-magic"></i>设备IP
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td width="50%">ip</td>
							<td>vlan</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="assignedip in deviceAssignedips">
							<td>{{assignedip.ai.ip}}</td>
							<td>{{assignedip.ai.vlan}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="portlet blue box" v-if="externalAssignedips.length > 0">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-magic"></i>外网IP
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td width="50%">ip</td>
							<td>vlan</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="assignedip in externalAssignedips">
							<td>{{assignedip.ai.ip}}</td>
							<td>{{assignedip.ai.vlan}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="portlet blue box" v-if="internalAssignedips.length > 0">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-magic"></i>内网IP
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td width="50%">ip</td>
							<td>vlan</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="assignedip in internalAssignedips">
							<td>{{assignedip.ai.ip}}</td>
							<td>{{assignedip.ai.vlan}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="portlet blue box" v-if="loopbackAssignedips.length > 0">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-magic"></i>loopbackIP
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td width="50%">ip</td>
							<td>vlan</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="assignedip in loopbackAssignedips">
							<td>{{assignedip.ai.ip}}</td>
							<td>{{assignedip.ai.vlan}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	</div>
	
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/network_device/network_device_view_init'], function(view) {
		view.init({
			id: '${id}',
			uuid: '${uuid}'
		});
	});
</script>