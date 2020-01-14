<div class="form-horizontal">
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-4 control-label">编码</label>
			<div class="col-md-8">
				<label class="control-label">{{device.maintenance_code}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">设备型号</label>
			<div class="col-md-8">
				<label class="control-label">{{deviceModel.modelname}}</label>
			</div>
		</div>
		<div v-if="deviceType" class="form-group">
			<label class="col-md-4 control-label">设备类型</label>
			<div class="col-md-8">
				<label class="control-label">{{deviceType}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">CPU</label>
			<div class="col-md-8">
				<label class="control-label">{{device.device_cpu}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">内存</label>
			<div class="col-md-8">
				<label class="control-label">{{device.device_mem}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">硬盘</label>
			<div class="col-md-8">
				<label class="control-label">{{device.device_disk}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">机柜位置</label>
			<div class="col-md-8">
				<label class="control-label">
					{{device.room_col}}-{{device.col_index}}-{{device.start_u_bit}}
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">购买日期</label>
			<div class="col-md-8">
				<label class="control-label">{{device.purchase_date}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">保修日期</label>
			<div class="col-md-8">
				<label class="control-label">{{device.warranty_date}}</label>
			</div>
		</div>
	</div>
</div>