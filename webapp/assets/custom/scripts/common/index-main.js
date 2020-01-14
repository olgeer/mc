/*!
 * 
 */
require.config({
	baseUrl: 'assets/global',

	paths: {
		"jquery": 'plugins/jquery-1.11.0.min',
		"jquery_migrate": 'plugins/jquery-migrate-1.2.1.min',
		"jquery_ui": 'plugins/jquery-ui/jquery-ui-1.10.3.custom.min',
		"bootstrap": 'plugins/bootstrap/js/bootstrap.min',
		"bootstrap_hover_dropdown": 'plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min',
		"jquery_slimscroll": 'plugins/jquery-slimscroll/jquery.slimscroll.min',
		"jquery_blockui": 'plugins/jquery.blockui.min',
		"jquery_cokie": 'plugins/jquery.cokie.min',
		"jquery_uniform": 'plugins/uniform/jquery.uniform.min',
		"bootstrap_select" : "plugins/bootstrap-select/bootstrap-select.min",
		"select2": "plugins/select2/select2.min",

		// CUSTOM PLUGINS
		"jquery_placeholder" : "../custom/plugins/jquery.placeholder",

		"metronic": 'scripts/metronic',
		"layout": 'layout/scripts/layout',
		"locate_menu": '../custom/scripts/common/locate_menu',
		"page_event": '../custom/scripts/common/page_event',

		"jquery_dataTables" : "plugins/data-tables/jquery.dataTables.min",
		"DT_bootstrap" : "plugins/data-tables/DT_bootstrap",
		"bootstrap_datepicker" : "plugins/bootstrap-datepicker/js/bootstrap-datepicker",
		"bootstrap_modalmanager" : "plugins/bootstrap-modal/js/bootstrap-modalmanager",
		"bootstrap_modal" : "plugins/bootstrap-modal/js/bootstrap-modal",

		"jquery_validate" : "plugins/jquery-validation/js/jquery.validate.min",
		"jquery_validator_messages" : "scripts/jquery.validator.messages",

		// PAGE LEVEL SCRIPTS
		"datatable" : "scripts/datatable",
		"datatable_ajax" : "../custom/scripts/common/datatable-ajax",
		"datatable_ajax_modal" : "../custom/scripts/common/datatable-ajax-modal",
		"datatable_ajax_view" : "../custom/scripts/common/datatable-ajax-view",
		"datatable_ajax_jsonp" : "../custom/scripts/common/datatable-ajax-jsonp",
		"lodash": "../global/scripts/lodash.min",
        "vue": "plugins/vue/vue.min",
		"moment": "plugins/moment/moment",
		"d3": "plugins/d3/d3.min",
        "numeral" : "../custom/plugins/numeral.min",

    },

	shim: {
		"bootstrap_select" : {
			deps: ['jquery']
		},
		"jquery_placeholder" : {
			deps: ['jquery']
		},
		"jquery_migrate" : {
			deps: ['jquery']
		},
		"jquery_ui" : {
			deps: ['jquery']
		},
		"bootstrap" : {
			deps: ['jquery', 'jquery_ui']
		},
		"bootstrap_hover_dropdown" : {
			deps: ['jquery', 'bootstrap']
		},
		"jquery_slimscroll" : {
			deps: ['jquery']
		},
		"jquery_blockui" : {
			deps: ['jquery']
		},
		"jquery_cokie" : {
			deps: ['jquery']
		},
		"jquery_uniform" : {
			deps: ['jquery']
		},
		"jquery" : {
			exports: "$"
		},

		"metronic" : {
			deps: ['jquery_slimscroll','jquery'],
			exports: "Metronic"
		},
		"bootstrap_datepicker" : {
			deps : ["jquery", 'jquery_ui']
		},
		"select2" : {
			deps : ["jquery"]
		},
		"jquery_validate" : {
			deps : ["jquery"]
		},
		"DT_bootstrap" : {
			deps : ["jquery_dataTables","jquery"]
		},
		"bootstrap_modalmanager" : {
			deps : ["bootstrap"]
		},
		"bootstrap_modal" : {
			deps : ["bootstrap_modalmanager"]
		},
		"datatable_ajax" : {
			deps : ["jquery_dataTables", "DT_bootstrap", "datatable"],
			exports : "TableAjax"
		},
		"datatable_ajax_jsonp" : {
			deps : ["jquery_dataTables", "DT_bootstrap", "datatable"],
			exports : "TableAjaxJsonp"
		},
		"datatable_ajax_view" : {
			deps : ["DT_bootstrap", "datatable"],
			exports : "DatatableAjaxView"
		},
		"datatable_ajax_modal" : {
			deps : ["DT_bootstrap", "datatable"],
			exports : "DatatableAjaxModal"
		},
		"jquery_validator_messages" : {
			deps : ["jquery_validate"]
		}
	}
});

require([ "jquery", "metronic", "layout", "jquery_migrate", "jquery_ui",
		"bootstrap", "bootstrap_hover_dropdown", "jquery_blockui",
		"jquery_cokie", "jquery_uniform", "jquery_placeholder",
		"bootstrap_select","locate_menu",'page_event','DT_bootstrap',
		'bootstrap_modal','datatable_ajax','datatable_ajax_view','datatable_ajax_modal',
		'jquery_validator_messages','select2','bootstrap_datepicker'], function($) {

	$(document).ready(function() {
		Metronic.init(); // init metronic core components
		Layout.init(); // init current layout
		var $home = $("#homePage")
		if ($home.length > 0) {
			$home.click();
		} else {
            Layout.openTab('home', '司务官后台欢迎页','module_homepage_index');
		}
		//window.loadTopMsgData && loadTopMsgData();
	});

});