define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "行业标签ID",
			"mData" : "industry_id"
		}, {
			"sTitle" : "行业标签名称",
			"mData" : "industry_name"
		}, {
			"sTitle" : "标签等级",
			"mData" : "industry_rank"
		}, {
			"sTitle" : "上级标签名称",
			"mData" : "parent_industry_name"
		}, {
			"sTitle" : "创建时间",
			"mData" : "create_time"
		}, {
			"sTitle" : "操作",
			"mData" : "industry_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_industry_edit_' + data + '" href="configuration/t_industry/' + data + '?displayName="><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_industry_view_' + data + '" href="configuration/industry/view?industry_id=' + data + '"><i class="fa fa-eye"></i> 标签详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	;

	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#industry_datatable_" + params.uuid, cmdbHost + "/itil?_tablename=view_industry_list&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});