<div class="form-horizontal">
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-4 control-label">开始 IP</label>
			<div class="col-md-8">
				<label class="control-label">{{ippool.start_ip}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">结束 IP</label>
			<div class="col-md-8">
				<label class="control-label">{{ippool.en_ip}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">网关</label>
			<div class="col-md-8">
				<label class="control-label">{{ippool.gateway}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">掩码</label>
			<div class="col-md-8">
				<label class="control-label">{{ippool.netmask}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">vlan</label>
			<div class="col-md-8">
				<label class="control-label">{{ippool.vlan}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">状态</label>
			<div class="col-md-8">
				<label class="control-label">
					<span v-if="ippool.pool_status=='0'">停用</span>
					<span v-if="ippool.pool_status=='1'">可用</span>
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">类型</label>
			<div class="col-md-8">
				<label class="control-label">
					<span v-if="ippool.pool_type=='0'">私有</span>
					<span v-if="ippool.pool_type=='1'">公有</span>
				</label>
			</div>
		</div>
	</div>
</div>