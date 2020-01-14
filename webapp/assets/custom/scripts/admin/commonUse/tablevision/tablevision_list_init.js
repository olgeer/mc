define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {

	function getAoData(params) {
		var aoData = [ 
                  {
	            "sTitle" : "ID值",
	            "mData" : "id"
                 }, {
         			"sTitle" : "表名",
        			"mData" : "tablename"
        		},{
	            "sTitle" : "列名",
	            "mData" : "columnname"
                 },
		    {
			"sTitle" : "显示名称",
			"mData" : "showname"
		  }, {
			"sTitle" : "字段描述",
			"mData" : "describes"
		   },{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a refresh  class="btn btn-xs blue ajaxify" module_id="module_tablevision_edit_' + data + '" href="admin/commonUse/tablevision/' + data + '?displayName=表格配置"><i class="fa fa-pencil"></i> 编辑</a>');
				return str;
			}
		}];
		return aoData;
	}
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#tablevision_datatable_" + params.uuid, cmdbHost + "/itil?_tablename=tablevision&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});