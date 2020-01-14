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
		// PAGE LEVEL SCRIPTS
		"jquery_validate" : "plugins/jquery-validation/js/jquery.validate.min",
		"jquery_validator_messages" : "scripts/jquery.validator.messages",
		
		"datatable" : "scripts/datatable",
		"datatable_ajax" : "../custom/scripts/common/datatable-ajax",
		"datatable_ajax_modal" : "../custom/scripts/common/datatable-ajax-modal",
		"datatable_ajax_view" : "../custom/scripts/common/datatable-ajax-view",
		"officeTempList_init" : "../custom/scripts/common/officeTempList_init"
	},

	shim : {
		"DT_bootstrap" : {
			deps : ["jquery_dataTables"]
		},
		"bootstrap_modal" : {
			deps : ["bootstrap_modalmanager"]
		},
		"datatable_ajax" : {
			deps : ["jquery_dataTables", "DT_bootstrap", "datatable"],
			exports : "TableAjax"
		},
		"datatable_ajax_view" : {
			deps : ["DT_bootstrap", "datatable"],
			exports : "DatatableAjaxView"
		},
		"jquery_validator_messages" : {
			deps : ["jquery_validate"]
		},
		"datatable_ajax_modal" : {
			deps : ["DT_bootstrap", "datatable"],
			exports : "DatatableAjaxModal"
		}
	}

});
//"bootstrap_datepicker", "bootstrap_modal"  


	require(["jquery", "datatable_ajax","officeTempList_init","jquery_validator_messages","jquery_validate","bootstrap_datepicker","bootstrap_modal"],
			function($, DatatableAjax, officeTempListInit) {
				$(document).ready(function() {
					Metronic.init();
					officeTempListInit.init();
				});
			});

