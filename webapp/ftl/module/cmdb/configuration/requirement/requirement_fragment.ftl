<div class="form-horizontal">
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-4 control-label">需求id</label>
			<div class="col-md-8">
				<label class="control-label">{{requirement.req_id}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">用户id</label>
			<div class="col-md-8">
				<label class="control-label">{{requirement.user_id}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">司务号</label>
			<div class="col-md-8">
				<label class="control-label">{{requirement.cm_id}}</label>
			</div>
		</div>

			<div class="form-group">
				<label class="col-md-4 control-label">公司名称</label>
				<div class="col-md-8">
					<a class="ajaxify" :module_id="requirement.company_id | concat('module_company_view_')" :href="requirement.company_id | concat('configuration/company/view?company_id=')" title="企业详情" data-html="true">
						{{requirement.company_name}}
					</a>
					<a class="hidden"><i class="fa fa-eye"></i> 需求详情</a>
				</div>
			</div>

		<div class="form-group">
			<label class="col-md-4 control-label">需求类型</label>
			<div class="col-md-8">
				<label  v-if="requirement.req_type==0" class="control-label">未知</label>
				<label  v-if="requirement.req_type==1" class="control-label">找钱</label>
				<label  v-if="requirement.req_type==2" class="control-label">赚钱</label>
				<label  v-if="requirement.req_type==2" class="control-label">省钱</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">具体业务</label>
			<div class="col-md-8">
				<label class="control-label">{{requirement.req_name}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">发布时间</label>
			<div class="col-md-8">
				<label class="control-label">{{requirement.create_time}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">详细需求</label>
			<div class="col-md-8">
				<label class="control-label">{{requirement.remark}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">需求状态</label>
			<div class="col-md-8">
				<label  v-if="requirement.state==0" class="control-label">发布中</label>
				<label  v-if="requirement.state==1" class="control-label">已抢单未满员</label>
				<label  v-if="requirement.state==2" class="control-label">满员</label>
				<label  v-if="requirement.state==3" class="control-label">已成交</label>
				<label  v-if="requirement.state==2" class="control-label">已失效</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">相关附件</label>
			<div class="col-md-8">
				<label class="control-label">{{requirement.attachment}}</label>
			</div>
		</div>
	</div>
</div>