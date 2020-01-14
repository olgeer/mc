requirejs.config({
	baseUrl : "assets/global",
    waitSeconds: 0,
	paths : {
		// CORE PLUGINS
		//"jquery" : "plugins/jquery-1.11.0.min",
		"jquery_dataTables" : "plugins/data-tables/jquery.dataTables.min",
		"DT_bootstrap" : "plugins/data-tables/DT_bootstrap",
		"bootstrap_datepicker" : "plugins/bootstrap-datepicker/js/bootstrap-datepicker",
		"bootstrap_modalmanager" : "plugins/bootstrap-modal/js/bootstrap-modalmanager",
		"bootstrap_modal" : "plugins/bootstrap-modal/js/bootstrap-modal",
		
		"jquery_validate" : "plugins/jquery-validation/js/jquery.validate.min",
		"jquery_validator_messages" : "scripts/jquery.validator.messages",
		
		// PAGE LEVEL SCRIPTS
		"datatable" : "scripts/datatable",
		"datatable_ajax_jsonp" : "../custom/scripts/common/datatable-ajax-jsonp",
		"ippool_list_init" : "../custom/scripts/cmdb/configuration/ippool/ippool_list_init"
	},

	shim : {
		"DT_bootstrap" : {
			deps : ["jquery_dataTables"]
		},
		"bootstrap_modal" : {
			deps : ["bootstrap_modalmanager"]
		},
		"datatable_ajax_jsonp" : {
			deps : ["jquery_dataTables", "DT_bootstrap", "datatable"],
			exports : "TableAjaxJsonp"
		},
		"jquery_validator_messages" : {
			deps : ["jquery_validate"]
		},
		"person_task_list_init" : {
			deps : ["datatable_ajax"],
			exports : "personTaskListInit"
		}
	}

});

