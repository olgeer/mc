requirejs.config({
	baseUrl : "assets/global",
    waitSeconds: 0,
	paths : {
		// CORE PLUGINS
		"jquery" : "plugins/jquery-1.11.0.min",
		//"jquery_migrate" : "plugins/jquery-migrate-1.2.1.min",
		"bootstrap" : "plugins/bootstrap/js/bootstrap.min",
		//"bootstrap_hover_dropdown" : "plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min",
		//"jquery_slimscroll" : "plugins/jquery-slimscroll/jquery.slimscroll.min",
		//"jquery_blockui" : "plugins/jquery.blockui.min",
		//"jquery_cokie" : "plugins/jquery.cokie.min",
		"jquery_uniform" : "plugins/uniform/jquery.uniform.min",
		//"bootstrap_modalmanager" : "plugins/bootstrap-modal/js/bootstrap-modalmanager",
		//"bootstrap_modal" : "plugins/bootstrap-modal/js/bootstrap-modal",
		"toastr" : "plugins/bootstrap-toastr/toastr.min",

		// PAGE LEVEL PLUGINS
		"jquery_backstretch" : "plugins/backstretch/jquery.backstretch.min",
		"jquery_validate" : "plugins/jquery-validation/js/jquery.validate.min",
		// PAGE LEVEL SCRIPTS
		"metronic" : "scripts/metronic",
		//"layout" : "layout/scripts/layout",
		"login_init" : "../custom/scripts/common/login_init"
	},
	shim : {
		"jquery" : {
			exports : "$"
		},
		/*"jquery_migrate" : {
			deps : ['jquery']
		},*/
		"bootstrap" : {
			deps : ['jquery']
		},
		/*"bootstrap_hover_dropdown" : {
			deps : ['jquery', 'bootstrap']
		},
		"jquery_slimscroll" : {
			deps : ['jquery']
		},
		"jquery_blockui" : {
			deps : ['jquery']
		},
		"jquery_cokie" : {
			deps : ['jquery']
		},*/
		"jquery_uniform" : {
			deps : ['jquery']
		},
		"jquery_backstretch" : {
			deps : ['jquery']
		},
		"jquery_validate" : {
			deps : ['jquery']
		},
		/*"bootstrap_modalmanager" : {
			deps : ["bootstrap"]
		},
		"bootstrap_modal" : {
			deps : ["bootstrap_modalmanager"]
		},*/
		"toastr" : {
			deps : ["bootstrap"],
			exports : "toastr"
		},
		"metronic" : {
			exports : "Metronic"
		},
		"login_init" : {
			deps : ['toastr'/*, 'metronic', 'jquery', 'bootstrap_modal'*/],
			exports : "LoginInit"
		}
	}
});

require(["jquery", "login_init", "metronic", "jquery_backstretch", "jquery_validate",
	/*'bootstrap_modal',*/
				/*"bootstrap",*/ "jquery_uniform"/*, "jquery_migrate"*/], function($, LoginInit) {

	$(document).ready(function() {
		Metronic.init(); // init metronic core components
		//Layout.init(); // init current layout
		LoginInit.init();
		
	});
});