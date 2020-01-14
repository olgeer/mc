define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "账号",
			"mData" : "dnsa.service_account"
		}, {
			"sTitle" : "service_TOKEN",
			"mData" : "dnsa.service_token"
		}
			, {
				"sTitle" : "service_accessID",
				"mData" : "dnsa.service_accessid"
			}
			, {
				"sTitle" : "service_access密钥",
				"mData" : "dnsa.service_accesssecret"
			}
			, {
				"sTitle" : "域名服务商",
				"mData" : "domv.domain_vendor"
			},{
			"sTitle" : "操作",
			"mData" : "dnsa.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_dnspod_account_edit_' + data + '" href="configuration/dnspod_account/' + data + '?displayName=域名账号管理"><i class="fa fa-pencil"></i> 编辑</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_dnspodrecord_view_' + data + '" href="configuration/dnspodrecord/view?id=' + data + '"><i class="fa fa-eye"></i> 域名详情</a>';
				return str;
			}
		}];

		return aoData;
	}


	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=dnspod_account dnsa ,domain_vendor domv&_joincols=dnsa.belongs_to_vendor_id domv.id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});