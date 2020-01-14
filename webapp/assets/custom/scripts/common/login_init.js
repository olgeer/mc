define(['toastr'],function(toastr) {

	var loginAjax = function() {
		$("#usernameError").hide();
		$("#captchaError").hide();
		$("#noPermissionError").hide();
		var	url = "module/j_spring_security_check";
		//用户类型
		var chooseUserType = $("input[name='chooseUserType']:checked").val();
		//管理员
		if(chooseUserType == "admin"){
			url = "admin/j_spring_security_check";
		}
		$.ajax({
			type : "post",
			url : url,
			data : $(".login-form").serializeArray(),
			success : function(res) {
				if(res.message === "captchaError"){
					$("#captchaError").show();
				}
				if(res.message === "usernameError"){
					$("#usernameError").show();
				}
				if(res.message === "captchaRequired"){
					$("#formKaptcha").show();
					$("#captchaRequired").show();
				}
				if(res.message === "success"){
					window.location.href=res.address;
					return;
				}
				if(res.message === "chooseRole"){
					var $modal = $('#chooseRole');
					// create the backdrop and wait for next modal to be triggered
					Metronic.stopPageLoading();
					$('body').modalmanager('loading');
					
					$modal.load(res.address+'?'+Math.random(), '', function(){
						$modal.modal();
					});
					return;
				}
				if(res.message === "noAuthority") {
					$("#noPermissionError").show();
				}
				$("#captcha").click();
				$("#kaptcha").val("");
				Metronic.stopPageLoading();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				if(xhr.status === 403) {
					$("#noPermissionError").show();
					$("#captcha").click();
					$("#kaptcha").val("");
				}
				Metronic.stopPageLoading();
			}
		});
	};
	
	var forgetAjax = function() {
		$("#existError").hide();
		$("#emailError").hide();
		$("#emailSendError").hide();
		$.ajax({
			type : "post",
			url : "common/forget/retrievePassword",
			data : $(".forget-form").serializeArray(),
			success : function(res) {
				if(res.message === "existError"){
					$("#existError").show();
				}
				if(res.message === "emailError"){
					$("#emailError").show();
				}
				if(res.message === "emailSendError"){
					$("#emailSendError").show();
				}
				if(res.message === "success"){
					$("#resetUsername").val(res.username);
					$("#resetUserType").val(res.userType);
					jQuery('.forget-form').hide();
					jQuery('.reset-form').show();
				}
				Metronic.stopPageLoading();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				Metronic.stopPageLoading();
			}
		});
	};
	
	var resetAjax = function() {
		$("#emailCaptchaError").hide();
		$.ajax({
			type : "post",
			url : "common/forget/resetPassword",
			data : $(".reset-form").serializeArray(),
			success : function(res) {
				if(res.message === "captchaError"){
					$("#emailCaptchaError").show();
				}
				if(res.message === "success"){
					$("#resetPasswordSuccess").show();
					jQuery('.reset-form').hide();
					jQuery('.login-form').show();
				}
				Metronic.stopPageLoading();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				Metronic.stopPageLoading();
			}
		});
	};

	var handleLogin = function() {
		$('.login-form').validate({
			errorElement : 'span', // default input error message
			// container
			errorClass : 'help-block', // default input error message
			// class
			focusInvalid : false, // do not focus the last invalid
			// input
			rules : {
				username : {
					required : true
				},
				j_password : {
					required : true
				},
//				challenge : {
//					required : true
//				},
				remember : {
					required : false
				}
			},

			messages : {
				username : {
					required : "请输入您的用户名"
				},
				j_password : {
					required : "请输入您的密码"
				},
				kaptcha : {
					required : "请输入验证码"
				}
			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set
			},

			success : function(label) {
				label.closest('.form-group').removeClass('has-error');
				label.remove();
			},

			errorPlacement : function(error, element) {
				error.insertAfter(element.closest('.input-icon'));
			}
		});
	};

	var handleForgetPassword = function() {
		$('.forget-form').validate({
			errorElement : 'span', // default input error message
			// container
			errorClass : 'help-block', // default input error message
			// class
			focusInvalid : false, // do not focus the last invalid
			// input
			ignore : "",
			rules : {
				username : {
					required : true
				}
			},

			messages : {
				username : {
					required : "请输入您的用户名"
				}
			},

			invalidHandler : function(event, validator) { // display
			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set
			},

			success : function(label) {
				label.closest('.form-group').removeClass('has-error');
				label.remove();
			},

			errorPlacement : function(error, element) {
				error.insertAfter(element.closest('.input-icon'));
			},

			submitHandler : function(form) {
				form.submit();
			}
		});
		
		jQuery('#forget-password').on('click', function() {
			jQuery('.login-form').hide();
			jQuery('.forget-form').show();
		});
		
	};
	
	var handleReset = function() {
		$('.reset-form').validate({
			errorElement : 'span', // default input error message
			// container
			errorClass : 'help-block', // default input error message
			// class
			focusInvalid : false, // do not focus the last invalid
			// input
			rules : {
				password : {
					required : true,
					rangelength : [6, 16]
				},
				rePassword : {
					required : true,
					rangelength : [6, 16],
					equalTo : "#password"
				},
				emailAuthcode : {
					required : true
				}
			},

			messages : {
				password : {
					required : "请输入新密码",
					rangelength : "密码长度必须为6-16个字符"
				},
				rePassword : {
					required : "请输入确认新密码",
					rangelength : "密码长度必须为6-16个字符",
					equalTo : "两次输入密码不一致"
				},
				emailAuthcode : {
					required : "请输入验证码"
				}
			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set
			},

			success : function(label) {
				label.closest('.form-group').removeClass('has-error');
				label.remove();
			},

			errorPlacement : function(error, element) {
				error.insertAfter(element.closest('.input-icon'));
			}
		});
	};
	
	var initBackgroud = function() {
		$.backstretch(["assets/custom/img/bg/1.jpg",
				"assets/custom/img/bg/2.jpg",
				"assets/custom/img/bg/3.jpg"], {
			fade : 1000,
			duration : 8000
		});
		if(screen.width > 1000 && (navigator.userAgent.indexOf("MSIE 6.0")>0 || navigator.userAgent.indexOf("MSIE 7.0")>0)){ 
			toastr.options = {
				"closeButton": true,
				"debug": false,
				"positionClass": "toast-top-full-width",
				"onclick": function () {
						window.open('chrome.exe');
					},
				"showDuration": "1000",
				"hideDuration": "1000",
				"timeOut": "30000",
				"extendedTimeOut": "30000",
				"showEasing": "swing",
				"hideEasing": "linear",
				"showMethod": "fadeIn",
				"hideMethod": "fadeOut"
			};
			var text = "如果页面出现异常或无法登陆，请点击这里下载并安装Google Chrome（谷歌浏览器）进行访问";
			toastr['info'](text, "系统提示：");
		
		}
	};
	
	var initSubmit = function() {
		$("#loginButton").on('click', function() {
			if ($(".login-form").valid()) {
				Metronic.startPageLoading();
				loginAjax();
			}
		});
		$("#forgetButton").on('click', function() {
			if ($(".forget-form").valid()) {
				Metronic.startPageLoading();
				forgetAjax();
			}
		});
		$("#resetButton").on('click', function() {
			if ($(".reset-form").valid()) {
				Metronic.startPageLoading();
				resetAjax();
			}
		});
		$('.login-form input').keypress(function(e) {
			if (e.which == 13) {
				if ($('.login-form').valid()) {
					Metronic.startPageLoading();
					loginAjax();
				}
				return false;
			}
		});
		$('.forget-form input').keypress(function(e) {
			if (e.which == 13) {
				if ($('.forget-form').valid()) {
					Metronic.startPageLoading();
					forgetAjax();
				}
				return false;
			}
		});
		$('.reset-form input').keypress(function(e) {
			if (e.which == 13) {
				if ($('.reset-form').valid()) {
					Metronic.startPageLoading();
					resetAjax();
				}
				return false;
			}
		});
	};
	
	handleChooseRole = function() {
		$('#chooseRole').on("click", ".cancelLogin", function(){
			$("#captcha").click();
			$("#kaptcha").val("");
		});
	};
	
	var handleBackToLogin = function() {
		
		jQuery('.back-to-login').on('click', function() {
			jQuery('.login-form').show();
			jQuery('.forget-form').hide();
			jQuery('.reset-form').hide();
		});
	};

	return {
		// main function to initiate the module
		init : function() {
			handleLogin();
			handleForgetPassword();
			handleReset();
			initBackgroud();
			initSubmit();
			handleChooseRole();
			handleBackToLogin();
		}
	};
});

function rememberMe(){
	if(!$("#rememberMe_a").hasClass('remember_on')){
		$("#rememberMe_a").removeClass('remember_off');
		$("#rememberMe_a").addClass('remember_on');
		$("#rememberMe_input").val(1);
	}else{
		$("#rememberMe_a").removeClass('remember_on');
		$("#rememberMe_a").addClass('remember_off');
		$("#rememberMe_input").val(0);
	}
}