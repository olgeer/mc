requirejs.config({
	baseUrl : "assets/global",
    waitSeconds: 0,
	paths : {
		// CORE PLUGINS
		// "jquery" : "jquery-1.11.0.min",
		"jquery_dataTables" : "plugins/data-tables/jquery.dataTables.min",
		"DT_bootstrap" : "plugins/data-tables/DT_bootstrap",
		"bootstrap_modalmanager" : "plugins/bootstrap-modal/js/bootstrap-modalmanager",
		"bootstrap_modal" : "plugins/bootstrap-modal/js/bootstrap-modal",
		"jquery_chained" : "../custom/plugins/jquery-chained/jquery.chained.min",

		// PAGE LEVEL SCRIPTS
		"datatable" : "scripts/datatable",
		"datatable_ajax" : "../custom/scripts/common/datatable-ajax",
		"idx_list_init" : "../custom/scripts/idx/idx_list_init"
	},

	shim : {
		"DT_bootstrap" : {
			deps : ["jquery_dataTables"]
		},
		"bootstrap_modal" : {
			deps : ["bootstrap_modalmanager"]
		},
		"datatable_ajax" : {
			deps : ["DT_bootstrap", "datatable"],
			exports : "DataTableAjax"
		},
		"jquery_chained" : {
			deps : ["jquery"],
			exports : "JqueryChained"
		},
		"idx_list_init" : {
			deps : ["datatable_ajax"],
			exports : "DepartmentListInit"
		}
		

	}

});

require(["jquery", "idx_list_init", "bootstrap_modal","jquery_chained"],
		function($, DepartmentListInit) {
			$(document).ready(function() {
				Metronic.init();
				DepartmentListInit.init();
			});

		});