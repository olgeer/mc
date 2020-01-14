requirejs.config({
	baseUrl : "assets/global",
    waitSeconds: 0,
	paths : {
		"grade_add_edit_init" : "../custom/scripts/admin/grade/grade_add_edit_init",
		"jquery_validate" : "plugins/jquery-validation/js/jquery.validate.min",
		"jquery_validator_messages" : "scripts/jquery.validator.messages",
		"select2" : "plugins/select2/select2.min",
		"grade_list_init" : "../custom/scripts/admin/grade/grade_list_init"
	},

	shim : {
		"jquery_validate" : {
			deps : ['jquery']
		},
		"jquery_validator_messages" : {
			deps : ["jquery_validate"]
		},
		"grade_list_init" : {
			deps : ["datatable_ajax"],
			exports : "GradetListInit"
		},
		"grade_add_edit_init" : {
			deps : ["datatable_ajax","jquery_validate","grade_list_init",'select2'],
			exports : "GradeAddEditInit"
		}

	}

});

require(["jquery", "grade_add_edit_init", "bootstrap_modal","jquery_chained","grade_list_init",'select2'],
		function($, GradeAddEditInit) {
			$(document).ready(function() {
				GradeAddEditInit.init();
			});

		});