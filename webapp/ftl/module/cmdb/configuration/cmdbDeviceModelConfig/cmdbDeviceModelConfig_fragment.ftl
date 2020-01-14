<div class="form-horizontal">
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-3 control-label">许可证名称</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbLicence.licence_name}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">许可证用途</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbLicence.equipment_use}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">许可证型号</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbLicence.equipment_type}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">许可证序列号</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbLicence.equipment_serial_number}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">使用状态</label>
			<div class="col-md-9">
			<div v-if="cmdbLicence.licence_status==0">
			<label class="control-label">已使用</label>
			</div>
			<div v-else>
				<label class="control-label">未使用</label>
			</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">规格说明</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbLicence.specification}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">许可证有效开始时间</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbLicence.licence_begin_date}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">许可证到期时间</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbLicence.licence_end_date}}</label>
			</div>
		</div>
			<div class="form-group">
			<label class="col-md-3 control-label">备注</label>
			<div class="col-md-9">
				<div>{{cmdbLicence.remark}}</div>
			</div>
		</div>
	</div>
</div>