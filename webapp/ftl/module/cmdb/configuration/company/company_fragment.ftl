<div class="form-horizontal">
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-2 control-label"><i class="fa fa-info-circle"></i></label>
			<div class="col-md-10">
				<label class="control-label">{{company.company_id}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">企业名称</label>
			<div class="col-md-8">
				<label class="control-label">{{company.company_name}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">司务号</label>
			<div class="col-md-8">
				<label class="control-label">{{company.cp_id}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">组织机构代码</label>
			<div class="col-md-8">
				<label class="control-label">{{company.institution_code}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">联系电话</label>
			<div class="col-md-8">
				<label class="control-label">{{company.company_tel}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">法人名称</label>
			<div class="col-md-8">
				<label class="control-label">{{company.incorporator_name}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">联系地址</label>
			<div class="col-md-8">
				<label class="control-label">{{company.company_address}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">供求角色</label>
			<div class="col-md-8">
				<label  v-if="company.company_type==-1" class="control-label">初始</label>
				<label  v-if="company.company_type==0" class="control-label">普通</label>
				<label  v-if="company.company_type==1" class="control-label">需求方</label>
				<label  v-if="company.company_type==2" class="control-label">服务方</label>
				<label  v-if="company.company_type==3" class="control-label">找钱VIP服务方</label>
				<label  v-if="company.company_type==4" class="control-label">赚钱VIP服务方</label>
				<label  v-if="company.company_type==5" class="control-label">省钱VIP服务方</label>
				<label  v-if="company.company_type==6" class="control-label">全渠道服务方</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">信誉积分</label>
			<div class="col-md-8">
				<label class="control-label">{{company.credit_score}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">发布需求数</label>
			<div class="col-md-8">
				<label class="control-label">{{company.req_count}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">服务数</label>
			<div class="col-md-8">
				<label class="control-label">{{company.ser_count}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">创建时间</label>
			<div class="col-md-8">
				<label class="control-label">{{company.create_time}}</label>
			</div>
		</div>

	</div>
</div>