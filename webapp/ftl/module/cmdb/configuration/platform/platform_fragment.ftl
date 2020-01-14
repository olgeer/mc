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
		<div class="form-group">
			<label class="col-md-4 control-label">状态</label>
			<div class="col-md-8">
				<label class="control-label">
					<span v-if="platform.status == 0">生产</span>
					<span v-else-if="platform.status == 1">下线</span>
					<span v-else-if="platform.status == 2">未上线</span>
					<span v-else-if="platform.status == 3">内部平台使用</span>
					<span v-else="platform.status">{{platform.developer_tel}}</span>
				</label>
			</div>
		</div>
	</div>
</div>