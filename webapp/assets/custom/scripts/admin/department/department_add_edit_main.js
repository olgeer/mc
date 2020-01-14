requirejs.config({
	baseUrl : "assets/global",
    waitSeconds: 0,
	paths : {
		"department_add_edit_init" : "../custom/scripts/admin/department/department_add_edit_init",
		"jquery_validate" : "plugins/jquery-validation/js/jquery.validate.min",
		"jquery_validator_messages" : "scripts/jquery.validator.messages",
		"department_list_init" : "../custom/scripts/admin/department/department_list_init"
	},

	shim : {
		"jquery_validate" : {
			deps : ['jquery']
		},
		"jquery_validator_messages" : {
			deps : ["jquery_validate"]
		},
		"department_list_init" : {
			deps : ["datatable_ajax"],
			exports : "DepartmentListInit"
		},
		"department_add_edit_init" : {
			deps : ["datatable_ajax","jquery_validate","department_list_init"],
			exports : "DepartmentAddEditInit"
		}

	}

});

require(["jquery", "department_add_edit_init", "bootstrap_modal","jquery_chained","department_list_init"],
		function($, DepartmentAddEditInit) {
			$(document).ready(function() {
				DepartmentAddEditInit.init();
			});

		});