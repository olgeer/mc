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
			"sTitle" : "机房",
			"mData" : "short_name"
		}, {
			"sTitle" : "区域",
			"mData" : "pool_area",
			"mRender" : getCodeToValueFn(params, 'pool_area')
		}, {
			"sTitle" : "类型",
			"mData" : "pool_type",
			"mRender" : getCodeToValueFn(params, 'pool_type')
		}, {
			"sTitle" : "起始VLAN",
			"mData" : "start_vlan"
		}, {
			"sTitle" : "终止VLAN",
			"mData" : "end_vlan"
		}, {
			"sTitle" : "可用VLAN数",
			"mData" : "pool_count"
		}, {
			"sTitle" : "剩余VLAN数",
			"mData" : "unused"
		}, {
			"sTitle" : "状态",
			"mData" : "status",
			"mRender" : getCodeToValueFn(params, 'status')
		}, {
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_network_vlan_pool_edit_' + data + '" href="configuration/cmdb_network_vlan_pool/' + data + '?displayName=VLAN资源池"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_network_vlan_pool_view_list_' + data + '" href="configuration/network_vlan/list?vlan_pool_id=' + data + '"><i class="fa fa-eye"></i> vlan</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=view_cmdb_network_vlan_pool&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});