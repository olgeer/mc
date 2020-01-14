define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {

	function getAoData(params) {
		var aoData = [ 
                  {
	            "sTitle" : "ID值",
	            "mData" : "id"
                 }, {
         			"sTitle" : "字段种类",
        			"mData" : "kind"
        		},{
	            "sTitle" : "具体含义",
	            "mData" : "detial"
                 },
		    {
			"sTitle" : "编码的值",
			"mData" : "value"
		  }, {
			"sTitle" : "编码",
			"mData" : "code"
		   },{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a refresh  class="btn btn-xs blue ajaxify" module_id="module_dictionary_edit_' + data + '" href="admin/commonUse/dictionary/' + data + '?displayName=数据字典"><i class="fa fa-pencil"></i> 编辑</a>');
				return str;
			}
		}];
		return aoData;
	}
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#dictionary_datatable_" + params.uuid, cmdbHost + "/itil?_tablename=dictionary&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});