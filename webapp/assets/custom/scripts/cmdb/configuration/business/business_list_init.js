define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "服务标签id",
			"mData" : "business_id"
		}, {
			"sTitle" : "服务方标签名称",
			"mData" : "business_name"
		}, {
			"sTitle" : "需求方标签名称",
			"mData" : "req_name"
		}, {
			"sTitle" : "上级标签名称",
			"mData" : "parent_business_name"
		}, {
			"sTitle" : "标签级别",
			"mData" : "business_rank"
		}, {
			"sTitle" : "标签类型",
			"mData" : "business_type"
		}, {
			"sTitle" : "需求明细标准化提示",
			"mData" : "business_memo"
		}, {
			"sTitle" : "操作",
			"mData" : "business_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_business_edit_' + data + '" href="configuration/t_business/' + data + '?displayName="><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_business_view_' + data + '" href="configuration/business/view?business_id=' + data + '"><i class="fa fa-eye"></i> 标签详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	;

	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#business_datatable_" + params.uuid, cmdbHost + "/itil?_tablename=view_business_list&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});