<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<img src="assets/custom/img/logo.png" alt="logo" class="logo-default" style="width: 200px; height: 54px;"/>
			<div class="menu-toggler sidebar-toggler hide">
				<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
			</div>
		</div>
		<!-- END LOGO -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<div class="top-menu">
			<ul id="newTipUl" class="nav navbar-nav pull-right">

				<#include "newTip.ftl">

				<!-- END INBOX DROPDOWN -->
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<li class="dropdown dropdown-user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<#--<img alt="" class="img-circle" src="module/setbasemessage/initTeaAvatar" height="29px" width="29px"/>-->
					<span class="username">
					${Session.SPRING_SECURITY_CONTEXT.authentication.principal.usbaName}</span>
					<i class="fa fa-angle-down"></i>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a class="ajaxify" module_id="module_manageUserAccounttoViewUserAccount" href="admin/manageUserAccount/toViewUserAccount">
							<i class="fa fa-user"></i> 个人信息 </a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="module/logout">
							<i class="fa fa-key"></i> 退出 </a>
						</li>
					</ul>
				</li>
				<!-- END USER LOGIN DROPDOWN -->
				
				<li>
					<!-- BEGIN RESPONSIVE MENU TOGGLER -->
					<div class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
					</div>
					<!-- END RESPONSIVE MENU TOGGLER -->
				</li>
			</ul>
		</div>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->