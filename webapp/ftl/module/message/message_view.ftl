<div class="row">
	<div class="col-md-12">
		<!-- BEGIN ALERTS PORTLET-->
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-envelope-open-o"></i>查看消息
				</div>
			</div>
			<div class="portlet-body">
				<#if msg.glob_detail_url?? >
					<a class="ajaxify" module_id="module_message_url_${msg.id}" href="message/messct/toMsgUrl?id=${msg.id}" data-html="true">
				</#if>
				<div class="form-horizontal">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-4 control-label">标题</label>
							<div class="col-md-8">
								<label class="control-label">${msg.title}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">内容</label>
							<div class="col-md-8">
								<label class="control-label">${msg.detail}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">发送人</label>
							<div class="col-md-8">
								<label class="control-label">${msg.send_user_name!}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">发送时间</label>
							<div class="col-md-8">
								<label class="control-label">${msg.send_time!}</label>
							</div>
						</div>
					</div>
				</div>
				<#if msg.glob_detail_url?? >
					</a>
					<a class="hidden"><span title="${msg.detail}"><span class="fa fa-pencil"></span>任务处理</span></a>
				</#if>
				
				
			</div>
		</div>
		<!-- END ALERTS PORTLET-->
	</div>
</div>