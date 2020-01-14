requirejs.config({
	baseUrl : "assets/global",
    waitSeconds: 0,
	paths : {
		// CORE PLUGINS
		//"jquery" : "plugins/jquery-1.11.0.min",
		"jquery_dataTables" : "plugins/data-tables/jquery.dataTables.min",
		"DT_bootstrap" : "plugins/data-tables/DT_bootstrap",
		
		// PAGE LEVEL SCRIPTS
		"datatable" : "scripts/datatable",
		"datatable_ajax" : "../custom/scripts/common/datatable-ajax"
			
	},

	shim : {
		"DT_bootstrap" : {
			deps : ["jquery_dataTables"]
		},
		"datatable_ajax" : {
			deps : ["jquery_dataTables", "DT_bootstrap", "datatable"],
			exports : "TableAjax"
		}
	}

});

require(["jquery", "datatable_ajax"],
		function($, DatatableAjax) {
			$(document).ready(function() {
				Metronic.init();
				DatatableAjax.init("#commonTemplateDocumentList", "common/templatedocument/getTemplateDocumentDatatable", 
				[{
					"sTitle" : "文件模板名称",
					"mData" : "mtdo_name",
					"bSortable" : false,
					"mRender" : function(data,type,full){
						var str = '<a href="common/templatedocument/downloadTemplateDocument/'+ full.mtdo_id +'" style="color:#000000">';
						if(0 == full.mtdo_type){
							str = str+'[管理规定]';
						}
						else if(1 == full.mtdo_type){
							str = str+'[模板文件]';
						}
						else if(2 == full.mtdo_type){
							str = str+'[安装软件]';
						}
						else {
							str = str+'[其他文件]';
						}
						str = str+data+'</a>';
						return str;
					}
				},{
					"sTitle" : "发布部门",
					"mData" : "depa_name"
					
				}, {
					"sTitle" : "日期",
					"mData" : "mtdo_create_date",
					"bSortable" : false,
					"mRender" : function(data){
						if(""!=data && null !=data){
							return data.substring(0,10);
						}
						else{
							return "数据出错";
						}
					}
				}]);
			});
		});