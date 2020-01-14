<div class="form-horizontal">
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-4 control-label">ip类型名</label>
			<div class="col-md-8">
				<label class="control-label">{{ipType.typename}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">网络区域</label>
			<div class="col-md-8">
				<label class="control-label">{{ipType.network_area}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">网络分类</label>
			<div class="col-md-8">
				<label class="control-label">{{ipType.network_zone}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">起始位ip</label>
			<div class="col-md-8">
				<label class="control-label">{{ipType.start}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">末位ip</label>
			<div class="col-md-8">
				<label class="control-label">{{ipType.end}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">升将序</label>
			<div class="col-md-8">
				<label class="control-label">
					<span v-if="ipType.order=='0'">升序</span>
					<span v-if="ipType.order=='1'">降序</span>
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">所属的类型</label>
			<div class="col-md-8">
				<label class="control-label">{{ipType.ip_type}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">是否成对</label>
			<div class="col-md-8">
				<label class="control-label">
					<span v-if="ipType.in_pairs=='0'">否</span>
					<span v-if="ipType.in_pairs=='1'">是</span>
				</label>
			</div>
		</div>
	</div>
</div>