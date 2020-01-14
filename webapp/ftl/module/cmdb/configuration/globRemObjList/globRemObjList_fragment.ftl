<div class="form-horizontal">
	 <div class="form-body">
	 	<div v-if="globRemind&&globRemind.glre_pre_yellow_warning&&globRemObjList&&globRemObjList.grol_rem_end_date&&globRemind.glre_pre_remind_day">
		<div class="form-group">
			<label class="col-md-4 control-label">黄色提醒指标</label>
			<div class="col-md-8">
			 <div v-if="!(redWarning(globRemind.glre_pre_yellow_warning,globRemObjList.grol_rem_end_date)) && redWarning(globRemind.glre_pre_remind_day,globRemObjList.grol_rem_end_date)&&redWarning(0,globRemObjList.grol_rem_end_date)  ">
			   <label class="control-label"  style="color:#A67D3D">警告:已超黄色警告指标{{globRemind.glre_pre_yellow_warning}}天</label>
				</div>
				<div v-else>
				<label class="control-label">{{globRemind.glre_pre_yellow_warning}}天</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">红色提醒指标</label>
			<div class="col-md-8">
			   <div v-if="!redWarning(globRemind.glre_pre_remind_day,globRemObjList.grol_rem_end_date)&&redWarning(0,globRemObjList.grol_rem_end_date)">
			 		<label class="control-label" style="color:#FF0000">警告:已超红色警告指标 {{globRemind.glre_pre_remind_day}}天</label>
				</div>
				<div v-else>
				 	<label class="control-label">{{globRemind.glre_pre_remind_day}}天</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">到期时间</label>
			<div class="col-md-8">
			 <div v-if="!redWarning(0,globRemObjList.grol_rem_end_date) ">
			   <label class="control-label" style="color:#FF0000">该产品在{{globRemObjList.grol_rem_end_date}}已到期</label>
				</div>
				<div v-else>
				<label class="control-label">{{globRemObjList.grol_rem_end_date}}</label>
				</div>
			</div>
		</div>
		</div>
		<div v-else>
		 <label class="control-label" style="color:#030303">没有数据</label>
		</div>
	 </div>
</div>











