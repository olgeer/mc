requirejs.config({
	baseUrl : "assets/global",
    waitSeconds: 0,
	paths : {
		"idx_add_edit_init" : "../custom/scripts/idx/idx_add_edit_init",
		"jquery_validate" : "plugins/jquery-validation/js/jquery.validate.min",
		"jquery_validator_messages" : "scripts/jquery.validator.messages",
		"idx_list_init" : "../custom/scripts/admin/idx/idx_list_init"
	},

	shim : {
		"jquery_validate" : {
			deps : ['jquery']
		},
		"jquery_validator_messages" : {
			deps : ["jquery_validate"]
		},
		"idx_list_init" : {
			deps : ["datatable_ajax"],
			exports : "DepartmentListInit"
		},
		"idx_add_edit_init" : {
			deps : ["datatable_ajax","jquery_validate","idx_list_init"],
			exports : "DepartmentAddEditInit"
		}

	}

});

require(["jquery", "idx_add_edit_init", "bootstrap_modal","jquery_chained","idx_list_init"],
		function($, DepartmentAddEditInit) {
			$(document).ready(function() {
				DepartmentAddEditInit.init();
			});

		});