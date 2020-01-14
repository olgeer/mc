requirejs.config({
	baseUrl : "assets/global",
    waitSeconds: 0,
	paths : {
		"jquery_dataTables" : "plugins/data-tables/jquery.dataTables.min",
		"DT_bootstrap" : "plugins/data-tables/DT_bootstrap",
		"bootstrap_datetimepicker" : "plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min",
		"bootstrap_datetimepicker_zh" : "plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN",
		"bootstrap_modalmanager" : "plugins/bootstrap-modal/js/bootstrap-modalmanager",
		"bootstrap_modal" : "plugins/bootstrap-modal/js/bootstrap-modal",

		"datatable" : "scripts/datatable",
		"datatable_ajax" : "../custom/scripts/common/datatable-ajax",
		"loginlog_query_list_init" : "../custom/scripts/common/loginlog_query_list_init"
	},

	shim : {
		"DT_bootstrap" : {
			deps : ["jquery_dataTables"]
		},
		"datatable_ajax" : {
			deps : ["DT_bootstrap", "datatable"],
			exports : "DataTableAjax"
		},
		"bootstrap_modal" : {
			deps : ["bootstrap_modalmanager"]
		},
		"loginlog_query_list_init" : {
			deps : ["datatable_ajax"],
			exports : "loginlogQueryListInit"
		}
	}

});

require(["jquery", "loginlog_query_list_init","bootstrap_datetimepicker", "bootstrap_modal","bootstrap_datetimepicker_zh"],
		function($, loginlogQueryListInit) {
			$(document).ready(function() {
				Metronic.init();
				loginlogQueryListInit.init();
			});
		});