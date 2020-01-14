define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "用户id",
			"mData" : "user_id"
		}, {
			"sTitle" : "用户名称",
			"mData" : "user_name"
		}, {
			"sTitle" : "司务号",
			"mData" : "cm_id"
		}, {
			"sTitle" : "手机号",
			"mData" : "user_mobile"
		}, {
			"sTitle" : "可用次数",
			"mData" : "score_avaid"
		}, {
			"sTitle" : "推荐码",
			"mData" : "referer_code"
		}, {
			"sTitle" : "推荐人",
			"mData" : "refer_id"
		}, {
			"sTitle" : "所在企业",
			"mData" : "company_name"
			// ,
			// "mRender" : function(data,type,full) {
			// 	return '<a class="btn btn-xs ajaxify" data-html="true" module_id="module_company_view_' + full.id + '" href="configuration/company/view?company_id=' + full.company_id  + '">' +data + '</a><a class="hidden"><i class="fa fa-eye"></i> 企业详情</a>';
			// }
		}, {
			"sTitle" : "划账金额",
			"mData" : "pay_amount"
		}, {
			"sTitle" : "创建时间",
			"mData" : "create_time"
		}, {
			"sTitle" : "操作",
			"mData" : "user_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_user_edit_' + data + '" href="configuration/t_user/' + data + '?displayName="><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_user_view_' + data + '" href="configuration/user/view?user_id=' + data + '"><i class="fa fa-eye"></i> 用户详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	;

	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#user_datatable_" + params.uuid, cmdbHost + "/itil?_tablename=view_user_list&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});