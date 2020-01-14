define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getCodeToValueFn(params, name) {
		var map = {};
		$('#' + params.uuid + ' [name="' + name + '"] option').each(function() {
			$(this).attr('value') && (map[$(this).attr('value')] =  $(this).text());
		});
		
		return function(data) {
			return map[data] || data;
		}
	}
	
	function getAoData(params) {
		var aoData = [{
			"sTitle" : "区域",
			"mData" : "pool_area",
			"bSortable" : false,
			"mRender" : getCodeToValueFn(params, 'pool_area')
		}, {
			"sTitle" : "类型",
			"mData" : "pool_type",
			"bSortable" : false,
			"mRender" : getCodeToValueFn(params, 'pool_type')
		}, {
			"sTitle" : "平台",
			"mData" : "platform_name",
			"bSortable" : false
		}, {
			"sTitle" : "业务",
			"mData" : "bearer_service",
			"bSortable" : false,
		}, {
			"sTitle" : "网络号",
			"mData" : "network_num",
			"bSortable" : false
		}, {
			"sTitle" : "掩码",
			"mData" : "netmask",
			"bSortable" : false
		}, {
			"sTitle" : "网关",
			"mData" : "gateway",
			"bSortable" : false
		}, {
			"sTitle" : "vlan",
			"mData" : "vlan",
			"bSortable" : false
		}, {
			"sTitle" : "可用IP数",
			"mData" : "pool_count",
			"bSortable" : false
		}, {
			"sTitle" : "剩余IP数",
			"mData" : "unused",
			"bSortable" : false
		}, {
			"sTitle" : "状态",
			"mData" : "status",
			"bSortable" : false,
			"mRender" : getCodeToValueFn(params, 'status')
		}, {
			"sTitle" : "机房",
			"mData" : "short_name",
			"bSortable" : false
		}, {
			"sTitle" : "备注",
			"mData" : "remarks",
			"bSortable" : false
		}, {
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_network_ip_pool_edit_' + data + '" href="configuration/cmdb_network_ip_pool/' + data + '?displayName=IP资源池"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_network_ip_list_view_' + data + '" href="configuration/network_ip/list?belongs_ip_pool_id=' + data + '"><i class="fa fa-eye"></i> IP</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=view_cmdb_network_ip_pool&_action=get&_sortcol=inet_aton(network_num)&_sortdir=asc", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});