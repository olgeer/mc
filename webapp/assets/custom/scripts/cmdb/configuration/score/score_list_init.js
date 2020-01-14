define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "记录id",
			"mData" : "record_id"
		}, {
			"sTitle" : "关联业务id",
			"mData" : "order_id"
		}, {
			"sTitle" : "用户名",
			"mData" : "user_name"
		}, {
			"sTitle" : "企业名称",
			"mData" : "company_name"
		}, {
			"sTitle" : "收支标记",
			"mData" : "main_type"
		}, {
			"sTitle" : "详细类别",
			"mData" : "detail_type"
		}, {
			"sTitle" : "变化积分",
			"mData" : "score"
		}, {
			"sTitle" : "可用积分",
			"mData" : "score_avaid"
		}, {
			"sTitle" : "状态标志",
			"mData" : "state"
		}, {
			"sTitle" : "记录日期",
			"mData" : "create_time"
		}, {
			"sTitle" : "操作",
			"mData" : "record_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_score_edit_' + data + '" href="configuration/t_scorerecord/' + data + '?displayName="><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_score_view_' + data + '" href="configuration/score/view?record_id=' + data + '"><i class="fa fa-eye"></i> 记录详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	;

	var tableObj;
	var initDataTableAjax = function(params){
		var s_url=cmdbHost + "/itil?_tablename=view_scorerecord_list&_action=get";
		if(params.company_id)s_url=s_url+"&company_id="+params.company_id;
		tableObj = DataTableAjaxJsonp.init("#score_datatable_" + params.uuid, s_url, getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});