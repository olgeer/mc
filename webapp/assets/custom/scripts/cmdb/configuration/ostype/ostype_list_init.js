define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "系统模版名称",
			"mData" : "templatename"
		}, {
			"sTitle" : "系统类型",
			"mData" : "ostype"
		}, {
			"sTitle" : "操作系统详细",
			"mData" : "os_detail_name"
		}, {
			"sTitle" : "业务类型",
			"mData" : "business_type"
		}, {
			"sTitle" : "cpu",
			"mData" : "template_cpu"
		}, {
			"sTitle" : "内存",
			"mData" : "template_mem"
		}, {
			"sTitle" : "硬盘",
			"mData" : "template_disk"
		}, {
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_ostype_edit_' + data + '" href="configuration/os_type/' + data + '?displayName=系统"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_ostype_view_' + data + '" href="configuration/ostype/view?id=' + data + '"><i class="fa fa-eye"></i> 系统详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#ostype_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=os_type&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});