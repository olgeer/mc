define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "IP地址",
			"mData" : "vip.ip"
		}, {
			"sTitle" : "vlan",
			"mData" : "vip.vlan"
		}, {
			"sTitle" : "IP类型",
			"mData" : "it.typename"
		}, {
			"sTitle" : "智能DNS",
			"mData" : "vip.dns"
		},{
			"sTitle" : "操作",
			"mData" : "vip.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_vip_edit_' + data + '" href="configuration/vip/' + data + '?displayName=VIP"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_vip_view_' + data + '" href="configuration/vip/view?id=' + data + '"><i class="fa fa-eye"></i> vip详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=vip vip, iptype it&_joincols=vip.vip_belongs_to_iptype_id it.id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});