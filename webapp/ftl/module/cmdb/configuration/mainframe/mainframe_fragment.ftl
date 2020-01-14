<div class="form-horizontal">
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-3 control-label">主机名</label>
			<div class="col-md-9">
				<label class="control-label">{{host.hostname}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">网络区域</label>
			<div class="col-md-9">
				<label class="control-label">{{host.host_network_area}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">cpu</label>
			<div class="col-md-9">
				<label class="control-label">{{host.host_cpu}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">内存</label>
			<div class="col-md-9">
				<label class="control-label">{{host.host_mem}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">硬盘</label>
			<div class="col-md-9">
				<label class="control-label">{{host.host_disk}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">主机数</label>
			<div class="col-md-9">
				<label class="control-label">{{host.host_count}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">主机状态</label>
			<div class="col-md-9">
				<label class="control-label">
					<span v-if="host.host_status == 0">生产</span>
					<span v-else-if="host.host_status == 1">下线</span>
					<span v-else-if="host.host_status == 2">维修</span>
					<span v-else-if="host.host_status == 3">未上线</span>
					<span v-else>{{host.host_status}}</span>
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">主机备注</label>
			<div class="col-md-9">
				<label class="control-label">{{host.host_remarks}}</label>
			</div>
		</div>
	</div>
</div>