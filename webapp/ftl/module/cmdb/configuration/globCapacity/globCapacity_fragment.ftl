	<div class="form-horizontal">
	<!-- 判断是否有容量管理-->
		 	<div v-if="globCapacity">
				<div class="form-group">
			    <label class="col-md-4 control-label">管理周期</label>
			<div class="col-md-8">
				<label class="control-label">{{globCapacity.capacity_management_cycle}}天</label>
			</div>
			</div>
			<div class="form-group">
			    <label class="col-md-4 control-label">单位</label>
			<div class="col-md-8">
				<label class="control-label">{{globCapacity.capacity_unit}}</label>
			</div>
			</div>
			<div class="form-group">
			    <label class="col-md-4 control-label">容量能力</label>
			<div class="col-md-8">
				<label class="control-label">{{globCapacity.capacity_ability}}</label>
			</div>
			</div>
			<div class="form-group">
			    <label class="col-md-4 control-label">使用量</label>
			<div class="col-md-8">
				<label class="control-label">{{globCapacity.capacity_use}}</label>
			</div>
			</div>
			<div class="form-group">
			    <label class="col-md-4 control-label">使用率</label>
			<div class="col-md-8">
				<label class="control-label">{{globCapacity.capacity_rate}}%</label>
			</div>
			</div>
			<div class="form-group">
			    <label class="col-md-4 control-label">黄色告警指标</label>
			<div class="col-md-8">
			<div  v-if="globCapacity.capacity_rate < globCapacity.yellow_warning" >
					 <label class="control-label">{{globCapacity.yellow_warning}}%</label>
			</div >		
			<div  v-else-if="globCapacity.capacity_rate >= globCapacity.yellow_warning && globCapacity.capacity_rate <= globCapacity.red_warning">
			 <label class="control-label" style="color:#A67D3D">警告:使用率已超黄色警告指标{{globCapacity.yellow_warning}}%</label>
			</div >
			<div  v-else>
			 <label class="control-label">{{globCapacity.yellow_warning}}%</label>
					
			</div  >
			</div>
			</div>
			
			<div class="form-group">
			    <label class="col-md-4 control-label">红色告警指标</label>
			<div class="col-md-8">
				<div  v-if="globCapacity.capacity_rate <= globCapacity.red_warning" >
						{{globCapacity.red_warning}}%
				</div>
				<div v-else>
				 <label class="control-label" style="color:#FF0000">警告:使用率已超红色警告指标 {{globCapacity.red_warning}}%</label>
				</div>
			</div>
			</div>
			<div class="form-group">
			    <label class="col-md-4 control-label">消息是否已发</label>
			<div class="col-md-8">
			 <div v-if="globCapacity.send_message==0">
			    <label class="control-label">未发送</label>
			</div>
			<div v-else-if="globCapacity.send_message==1">
				<label class="control-label">已发送</label>
			</div>
			<div v-else>
				<label class="control-label">发送错误消息</label>
			</div>
				</div>
			</div>
				<div class="form-group">
			    <label class="col-md-4 control-label">修改时间</label>
			<div class="col-md-8">
				<label class="control-label">{{globCapacity.modified_time}}</label>
			</div>
			</div>
			<div class="form-group">
			    <label class="col-md-4 control-label">修改人</label>
			<div class="col-md-8">
				<label class="control-label">{{userBasic.usba_name}}</label>
			</div>
			</div>
				<div class="form-group">
			    <label class="col-md-4 control-label">产品名称</label>
			<div class="col-md-8">
				<label class="control-label">{{globCapacity.remind_obj}}</label>
			</div>
			</div>
			<div class="form-group">
			    <label class="col-md-4 control-label">使用情况</label>
			<div class="col-md-8">
			 <div v-if="globCapacity.end_use_remind_status==0">
			    <label class="control-label" style="color:#009900">充足</label>
			</div>
			<div v-else-if="globCapacity.end_use_remind_status==1">
				<label class="control-label" style="color:#A67D3D">黄色用量提醒</label>
			</div>
			<div  v-else-if="globCapacity.end_use_remind_status==2">
				<label class="control-label" style="color:#FF0000">红色用量提醒</label>
			</div>
			<div  v-else-if="globCapacity.end_use_remind_status==8">
				<label class="control-label" style="color:#FF1CAE">已经用完了</label>
			</div>
				</div>
			</div>
			
			
			</div>
			<div v-else>
		    <label class="control-label" style="color:#FF00FF">没有数据</label>
		</div>
		
</div>
