define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "司务号",
			"mData" : "cp_id"
		}, {
			"sTitle" : "企业名称",
			"mData" : "company_name"
		}, {
			"sTitle" : "手机号",
			"mData" : "user_mobile"
		}, {
			"sTitle" : "企业法人",
			"mData" : "incorporator_name"
		}, {
			"sTitle" : "联系人",
			"mData" : "company_contector"
		}, {
			"sTitle" : "联系电话",
			"mData" : "company_tel"
		}, {
			"sTitle" : "类型",
			"mData" : "company_type",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'-1': '初始',
					'0': '普通',
					'1': '需求方',
					'2': '服务方',
					'3': '找钱VIP服务方',
					'4': '赚钱VIP服务方',
					'5': '省钱VIP服务方',
					'6': '全渠道服务方'
				};
				map[data] && (str = map[data]);
				return str;
			}
		}, {
			"sTitle" : "注册时间",
			"mData" : "create_time"
		}, {
			"sTitle" : "操作",
			"mData" : "company_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_company_edit_' + data + '" href="configuration/t_company/' + data + '?displayName="><i class="fa fa-pencil"></i> 编辑企业</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_company_view_' + data + '" href="configuration/company/view?company_id=' + data + '"><i class="fa fa-eye"></i> 企业详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	;

	var tableObj;
	var initDataTableAjax = function(params){
		var s_url=cmdbHost + "/itil?_tablename=view_company_list&_action=get";
		if(params.company_id)s_url=s_url+"&company_id="+params.company_id;
		tableObj = DataTableAjaxJsonp.init("#company_datatable_" + params.uuid, s_url, getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});