<div class="form-horizontal">
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-4 control-label">型号</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceModel.device_model}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">类型</label>
			<div class="col-md-8">
                <div  v-if="cmdbDeviceModel.device_type==0" >
                    <label class="control-label">服务器</label>
                </div >
                <div  v-else-if="cmdbDeviceModel.device_type==1">
                    <label class="control-label" >网络设备</label>
                </div >
                <div  v-else-if="cmdbDeviceModel.device_type==2">
                    <label class="control-label" >其它设备</label>
                </div >
                <div  v-else="cmdbDeviceModel.device_type==3">
                    <label class="control-label">存储设备</label>

                </div  >
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">高度U数</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceModel.device_height}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">合计-CPU（内核）</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceModel.cpu_total_core}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">合计-内存容量(GB)</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceModel.memory_total}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">合计-硬盘容量(GB)</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceModel.disk_capacity_total}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">设备规格</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceModel.device_standard}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">PDU容量(W)</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceModel.pdu_capacity}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">设备品牌</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceModel.brand_name}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">备注</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceModel.remark}}</label>
			</div>
		</div>
	</div>
</div>