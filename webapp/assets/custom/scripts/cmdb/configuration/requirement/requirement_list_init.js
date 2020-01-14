define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "需求id",
			"mData" : "req_id"
		}, {
			"sTitle" : "司务号",
			"mData" : "cm_id"
		}, {
			"sTitle" : "手机号",
			"mData" : "user_mobile"
		}, {
			"sTitle" : "企业名称",
			"mData" : "company_name"
		}, {
			"sTitle" : "需求类型",
			"mData" : "req_type",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'0': '未知',
					'1': '找钱',
					'2': '赚钱',
					'3': '省钱'
				};
				map[data] && (str = map[data]);
				return str;
			}
		}, {
			"sTitle" : "需求名称",
			"mData" : "req_name"
		}, {
			"sTitle" : "行业",
			"mData" : "industry_name"
		}, {
			"sTitle" : "需求状态",
			"mData" : "state",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'0': '发布中',
					'1': '已抢单未满员',
					'2': '满员',
					'3': '已成交',
					'4': '已失效'
				};
				map[data] && (str = map[data]);
				return str;
			}
		}, {
			"sTitle" : "发布时间",
			"mData" : "create_time"
		}, {
			"sTitle" : "操作",
			"mData" : "req_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_requirement_view_' + data + '" href="configuration/requirement/view?req_id=' + data + '"><i class="fa fa-eye"></i> 需求详情</a>');

				return str;
			}
		}];
		return aoData;
	}
	;

	var tableObj;
	var initDataTableAjax = function(params){
		var s_url=cmdbHost + "/itil?_tablename=view_requirement_list&_action=get";
		if(params.company_id)s_url=s_url+"&company_id="+params.company_id;
		tableObj = DataTableAjaxJsonp.init("#requirement_datatable_" + params.uuid, s_url, getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});