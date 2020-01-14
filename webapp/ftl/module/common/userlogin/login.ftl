<!DOCTYPE html>
<!-- BEGIN HEAD -->
<head>
	
	<base href="${base}/">
	<meta charset="utf-8" />
	<title>司务官管理后台</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--[if IE 8]> <html lang="zh" class="ie8 no-js"> <![endif]-->
	<!--[if IE 9]> <html lang="zh" class="ie9 no-js"> <![endif]-->
	<!--[if !IE]><!-->
	<html lang="zh" class="no-js">
	<!--<![endif]-->
	<meta name="renderer" content="webkit">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="司务官管理后台" name="description" />
	<meta content="司务官管理后台" name="keywords" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<script type="text/javascript"> 
		<!-- 
		javascript:window.history.forward(1); 
		//--> 
	</script>
	<link href="assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/uniform/css/uniform.default.min.css" rel="stylesheet" type="text/css" />
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="assets/custom/css/common/login-soft.css" rel="stylesheet" type="text/css" />
<#--	<link href="assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />-->
	<link href="assets/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
	
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME STYLES -->
	<link href="assets/global/css/components.css" rel="stylesheet" type="text/css" />
	<!-- END THEME STYLES -->
	<link rel="shortcut icon" href="favicon.ico" />
	
	</head>
	<!-- END HEAD -->
	<!-- BEGIN BODY -->
	<body class="login">
		<input type="hidden" id="base" value="${base}">
		<!-- BEGIN LOGO -->
		<div class="logo">
		</div>
		<!-- END LOGO -->
		<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
		<div class="menu-toggler sidebar-toggler"></div>
		<!-- END SIDEBAR TOGGLER BUTTON -->
		<!-- BEGIN LOGIN -->
		<div class="content">
			<!-- BEGIN LOGIN FORM -->
			<style>
			.login .input-icon.div{ border-radius:6px!important; }
			.radio-inline, .checkbox-inline{padding-left:10px}
			</style>
			<form action="javascript:void(0)" name="loginForm" class="login-form" method="post">
				<h3 class="form-title text-center"><img src="assets/custom/img/logo-big.png" class="loginLogo" alt="司务官管理后台" style="width: 400px; height: 100px;"/></h3>
				<div id="usernameError" class="form-group alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span> 用户名或密码错误！ </span>
				</div>
				<div id="captchaError" class="form-group alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span> 验证码错误！ </span>
				</div>
				<div id="captchaRequired" class="form-group alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span> 请输入验证码！ </span>
				</div>
				<div id="noPermissionError" class="form-group alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span> 该用户未分配权限！ </span>
				</div>
				<div id="resetPasswordSuccess" class="form-group alert alert-success display-hide">
					<button class="close" data-close="alert"></button>
					<span> 密码重置成功！ </span>
				</div>
				<div class="form-group">
					<label class="control-label visible-ie8 visible-ie9">用户名</label>
					<div class="input-icon">
						<i class="fa fa-user"></i> 
						<input class="form-control" type="text" value=""  autocomplete="off" placeholder="请输入用户名" id="j_username" name="j_username" value="<#if cookiesLoginName??>${cookiesLoginName!}</#if>"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label visible-ie8 visible-ie9">密码</label>
					<div class="input-icon">
						<i class="fa fa-lock"></i> 
						<input class="form-control" type="password" autocomplete="off" placeholder="请输入密码" name="j_password" value=""/>
					</div>
				</div>
				<div class="form-group"  style="display:none;" id="formKaptcha">
					<label class="control-label visible-ie8 visible-ie9">验证码</label>
					<div class="input-icon">
						<i class="fa fa-key"></i> 
						<input type="text" id="kaptcha" name="kaptcha" class="form-control kaptcha" placeholder="请输入验证码" />
						<img id="captcha" class="captcha" name="captcha" src="getCaptcha?${.now?long}" onclick="document.loginForm.captcha.src='getCaptcha?' + Math.random()" />
					</div>
				</div>
				
					
				
				<div class="form-group" >
					<a id="loginButton" href="javascript:void(0);" class="btn blue " style="width: 100%;">
						登录 <i class=" m-icon-white"></i>
					</a>
				</div>	
				
					
			</form>
			<!-- END LOGIN FORM -->
			<!-- BEGIN FORGOT PASSWORD FORM -->
			<form action="javascript:void(0)" class="forget-form" method="post">
				<h3 class="form-title text-center" style=" margin-bottom: 53px;margin-top: 32px;">忘记密码？</h3>
				<div id="existError" class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span> 不存在的用户名！ </span>
				</div>
				<div id="emailError" class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span> 您还没有绑定邮箱，请联系管理员进行密码重置！ </span>
				</div>
				<div id="emailSendError" class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span> 邮件发送失败，请稍后重试或者联系系秘书进行密码重置！</span>
				</div>
				<div class="form-group">
					<label class="control-label visible-ie8 visible-ie9">用户名</label>
					<div class="input-icon">
						<i class="fa fa-user"></i> 
						<input class="form-control" type="text" autocomplete="off" placeholder="用户名" name="username"   />
					</div>
				</div>
				<div class="form-actions">
					<a href="javascript:void(0);" class="btn default back-to-login">
						<i class="m-icon-swapleft"></i> 返回
					</a>
					<a id="forgetButton" href="javascript:void(0);" class="btn blue pull-right">
						提交 <i class="m-icon-swapright m-icon-white"></i>
					</a>
				</div>
			</form>
			<!-- END FORGOT PASSWORD FORM -->
			<!-- BEGIN RESET PASSWORD FORM -->
			<form action="javascript:void(0)" class="reset-form" method="post">
				<h3 class="text-center" style=" margin-bottom: 53px;margin-top: 32px;">重置密码</h3>
				<div id="emailCaptchaError" class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span> 验证码错误！ </span>
				</div>
				<div id="sendEmailSuccess" class="alert alert-success">
					<button class="close" data-close="alert"></button>
					<span> 验证码已发到您的绑定邮箱，请到您的绑定邮箱查看验证码！ </span>
				</div>
				<div class="form-group">
					<label class="control-label visible-ie8 visible-ie9">新密码</label>
					<div class="input-icon">
						<i class="fa fa-lock"></i> 
						<input class="form-control" type="password" autocomplete="off" placeholder="新密码" name="password" id="password" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label visible-ie8 visible-ie9">确认新密码</label>
					<div class="input-icon">
						<i class="fa fa-lock"></i> 
						<input class="form-control" type="password" autocomplete="off" placeholder="确认新密码" name="rePassword" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label visible-ie8 visible-ie9">验证码</label>
					<div class="input-icon">
						<i class="fa fa-lock"></i> 
						<input class="form-control" type="text" autocomplete="off" placeholder="验证码" name="emailAuthcode" />
					</div>
				</div>
				<input type="hidden" name="username" id="resetUsername" />
				<input type="hidden" name="userType" id="resetUserType" />
				<div class="form-actions">
					<a href="javascript:void(0);" class="btn default back-to-login">
						<i class="m-icon-swapleft"></i> 返回
					</a>
					<a id="resetButton" href="javascript:void(0);" class="btn blue pull-right">
						提交 <i class="m-icon-swapright m-icon-white"></i>
					</a>
				</div>
			</form>
			<!-- END RESET PASSWORD FORM -->
	
		</div>
		<!-- END LOGIN -->
		<!-- BEGIN COPYRIGHT -->
		<div class="copyright">&copy; 2019  七号数字  版权所有</div>
		<!-- END COPYRIGHT -->
	
		<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
		<!-- BEGIN CORE PLUGINS -->
		<!--[if lt IE 9]>
		<script src="assets/global/plugins/respond.min.js"></script>
		<script src="assets/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
	
		
		<script src="assets/custom/plugins/requirejs/require.js" data-main="assets/custom/scripts/common/login_main.js" type="text/javascript"></script>
	
		<!-- END JAVASCRIPTS -->               
		
		<!--START MODAL-->
		<div id="chooseRole" class="modal fade modal-scroll" data-backdrop="static" data-attention-animation="false" tabindex="-1" aria-hidden="true" >
		
		</div>
		<!--END MODAL-->
	</body>
	<!-- END BODY -->
</html>