<div class="form-horizontal">
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-4 control-label">资产名称</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceInformation.device_name}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">资产类别</label>
			<div class="col-md-8">
                <label class="control-label">
                    <span v-if="cmdbDeviceInformation.device_type == 0">IP</span>
                    <span v-else-if="cmdbDeviceInformation.device_type == 1">第三方服务</span>
                    <span v-else-if="cmdbDeviceInformation.device_type == 2">软件</span>
                    <span v-else-if="cmdbDeviceInformation.device_type == 3">许可证</span>
                    <span v-else-if="cmdbDeviceInformation.device_type == 4">硬件</span>
                    <span v-else-if="cmdbDeviceInformation.device_type == 5">专线</span>
                    <span v-else>{{cmdbDeviceInformation.device_type}}</span>
                </label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">资产归属</label>
			<div class="col-md-8">
                <label class="control-label">
                    <span v-if="cmdbDeviceInformation.device_in_asset == 0">网金</span>
                    <span v-else-if="cmdbDeviceInformation.device_in_asset == 1">钱途</span>
                    <span v-else-if="cmdbDeviceInformation.device_in_asset == 2">钱端</span>
                    <span v-else>{{cmdbDeviceInformation.device_in_asset}}</span>
                </label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">资产状态</label>
			<div class="col-md-8">
                <label class="control-label">
                    <span v-if="cmdbDeviceInformation.device_status == 0">入库</span>
                    <span v-else-if="cmdbDeviceInformation.device_status == 1">投产中</span>
                    <span v-else-if="cmdbDeviceInformation.device_status == 2">上架</span>
                    <span v-else-if="cmdbDeviceInformation.device_status == 3">维护</span>
                    <span v-else-if="cmdbDeviceInformation.device_status == 4">下架</span>
                    <span v-else-if="cmdbDeviceInformation.device_status == 5">回收</span>
                    <span v-else-if="cmdbDeviceInformation.device_status == 6">出库</span>
                    <span v-else>{{cmdbDeviceInformation.device_status}}</span>
                </label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">物理位置</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceInformation.physics_area}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">序列号</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceInformation.device_sequence_number}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">购买时间</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceInformation.purchase_time}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">过保时间</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceInformation.outof_guarantee_deadline}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">保修期限</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceInformation.guarantee_deadline}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">备注</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbDeviceInformation.remark}}</label>
			</div>
		</div>
	</div>
</div>