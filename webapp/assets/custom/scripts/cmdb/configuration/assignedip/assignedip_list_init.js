define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "IP地址",
			"mData" : "ai.ip"
		}, {
			"sTitle" : "vlan",
			"mData" : "ai.vlan"
		}, {
			"sTitle" : "IP类型",
			"mData" : "it.typename"
		},{
			"sTitle" : "操作",
			"mData" : "ai.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_edit_' + data + '" href="configuration/assigned_ip/' + data + '?displayName=IP"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_view_' + data + '" href="configuration/assignedip/view?id=' + data + '"><i class="fa fa-eye"></i> IP详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=assigned_ip ai, iptype it&_joincols=ai.belongs_to_iptype_id it.id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});