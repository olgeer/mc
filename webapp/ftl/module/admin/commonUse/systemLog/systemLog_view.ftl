<div class="row" id="systemLog-${sylo_id}">
	<div class="col-md-12">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-book"></i>系统日志详情
				</div>
			</div>
			<div id="systemLog-systemLog-content-${sylo_id}" class="portlet-body">
				<div class="form-horizontal">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-4 control-label">方法名</label>
							<div class="col-md-8">
								<label class="control-label">{{systemLog.sylo_method_name}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">方法描述</label>
							<div class="col-md-8">
								<label class="control-label">{{systemLogExtend.method_description}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">方法类型</label>
							<div class="col-md-8">
								<label class="control-label">{{systemLog.sylo_method_type}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">访问IP</label>
							<div class="col-md-8">
								<label class="control-label">{{systemLog.sylo_ip}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">用户名</label>
							<div class="col-md-8">
								<label class="control-label">{{systemLog.sylo_usro_name}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">角色</label>
							<div class="col-md-8">
								<label class="control-label">{{systemLog.sylo_role_name}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">记录时间</label>
							<div class="col-md-8">
								<label class="control-label">{{systemLog.sylo_create_date}}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">日志类型</label>
							<div class="col-md-8">
							<div  v-if="systemLog.sylo_own == 0" >
							<label class="control-label"> 用户日志</label>
				          </div>
				          <div v-else>
				             <label class="control-label">管理员日志</label>
				             </div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/admin/commonUse/systemLog/systemLog_view_init'], function(view) {
		view.init({
			sylo_id: '${sylo_id}'
		});
	});
</script>