<!-- BEGIN SIDEBAR -->
	<div class="page-sidebar-wrapper">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU --> 
		        <ul class="page-sidebar-menu" data-auto-scroll="true" data-auto-speed="200">
		        	<li class="sidebar-toggler-wrapper">
						<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
						<div class="sidebar-toggler">
						</div>
						<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					</li>
					<li class="start">
						<a href="home" id="homePage" class="ajaxify" module_id="module_home">
						<i class="fa fa-home"></i>
						<span class="title">首页</span>
						<span class="selected"></span>
						</a>
					</li>
					<#list permissions as firstSidebar>
						<li id="li_${firstSidebar.permId}">
							<#if firstSidebar.permUrl?? && firstSidebar.permUrl != ''>
							<a class="ajaxify" href="${firstSidebar.permUrl}" module_id="module_${firstSidebar.permId}">
							<#else>
							<a href="javascript:void(0);">
							</#if>
			        			<i class="${firstSidebar.permIcon}"></i>
			        			<span class="title">${firstSidebar.permName}</span>
			        			<span class="arrow"></span>
								<span class="selected"></span>
		        			</a>
		        			<ul class="sub-menu">
								<#list firstSidebar.children as secondSidebar>
									<li>
										<#if secondSidebar.children?? && (secondSidebar.children?size>0) >
											<a href="javascript:void(0);">
		        								<i class="${secondSidebar.permIcon}"></i>
							        			<span class="title">${secondSidebar.permName}</span>
							        			<span class="arrow"></span>
												<span class="selected"></span>
						        			</a>
							        		<ul class="sub-menu">
												<#list secondSidebar.children as thirdSidebar>
													<li>
								        				<a class="ajaxify" href="${thirdSidebar.permUrl}" module_id="module_${thirdSidebar.permId}">
								        				<i class="${thirdSidebar.permIcon }"></i>
								        				${thirdSidebar.permName }
								        				</a>
								        			</li>
												</#list>
											</ul>
										<#else>
											<a class="ajaxify" href="${secondSidebar.permUrl}" module_id="module_${secondSidebar.permId}">
					        				<i class="${secondSidebar.permIcon }"></i>
					        				${secondSidebar.permName }
					        				</a>
										</#if>
				        			</li>
								</#list>
							</ul>
						</li>
					</#list>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
<!-- END SIDEBAR -->