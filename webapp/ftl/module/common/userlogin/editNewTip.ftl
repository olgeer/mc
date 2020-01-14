<!-- BEGIN NOTIFICATION DROPDOWN -->
<li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
	<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" 
		data-hover="dropdown" data-close-others="true">
		<i class="icon-bell"></i>
		<#if noticeMap.notReadCount!=0>
			<span class="badge badge-warning">${noticeMap.notReadCount}</span>
		</#if>
		<!--<span class="badge badge-danger">8</span>-->
		<!-- 有新消息的时候，变更为红色-->
	</a>
	<#if noticeMap.notReadCount!=0>
		<ul class="dropdown-menu">
			<li class="external">
				<h3>
					<i class="fa fa-bullhorn"></i> 
					<span class="bold">您有${noticeMap.notReadCount}条待操作任务.</span>
				</h3>
				<!--<a href="extra_profile.html">view all</a>-->
			</li>
			<li>
				<ul class="dropdown-menu-list scroller" style="height: 136px;" data-handle-color="#637283">
					<#list noticeMap.noticeDetails as noticeDetail>
						 <li>
							<a href="javascript:locateMenu('${noticeDetail.noty_full_modu_ids}');">
								<span class="time">${noticeDetail.node_modity_time}</span><!--针对不满一小时的，直接展示多少分钟前，小于1分钟的按1分钟计-->
								<span class="details">
									<span class="label label-icon label-primary">
										<i class="fa fa-plus"></i>
									</span>
									<span class="bold">${noticeDetail.noty_name}(${noticeDetail.node_count})<span> <#--等于0的时候不加粗，大于0的时候加粗-->
								</span>	
							</a>
						</li>
					</#list>
				</ul>
			</li>
		</ul>
	</#if>
</li>
<!-- END NOTIFICATION DROPDOWN -->