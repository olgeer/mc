define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "域名名称",
			"mData" : "dnsd.domain_name"
		}, {
			"sTitle" : "域名服务商",
			"mData" : "domv.domain_vendor"
		}
			, {
				"sTitle" : "服务明细",
				"mData" : "dnsd.service_detail"
			}
			, {
				"sTitle" : "所属账号",
				"mData" : "dnsa.service_account"
			}
			, {
				"sTitle" : "备注",
				"mData" : "dnsd.remarks"
			},{
			"sTitle" : "操作",
			"mData" : "dnsd.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_dnspod_domain_edit_' + data + '" href="configuration/dnspod_domain/' + data + '?displayName=域名管理"><i class="fa fa-pencil"></i> 编辑</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_dnspodrecord_view_' + data + '" href="configuration/dnspodrecord/view?id=' + data + '"><i class="fa fa-eye"></i> 域名详情</a>';
				return str;
			}
		}];

		return aoData;
	}


	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=dnspod_domain dnsd,dnspod_account dnsa ,domain_vendor domv&_joincols=dnsd.belongs_to_account_id dnsa.id,dnsd.belongs_to_vendor_id domv.id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});