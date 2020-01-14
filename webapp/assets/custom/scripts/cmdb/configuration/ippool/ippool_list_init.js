define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "起始IP",
			"mData" : "start_ip"
		}, {
			"sTitle" : "结束IP",
			"mData" : "en_ip"
		}, {
			"sTitle" : "网关",
			"mData" : "gateway"
		}, {
			"sTitle" : "掩码",
			"mData" : "netmask"
		},{
			"sTitle" : "vlan",
			"mData" : "vlan"
		},{
			"sTitle" : "状态",
			"mData" : "pool_status",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'0': '停用',
					'1': '可用'
				};
				map[data] && (str = map[data]);
				return str;
			}
		},{
			"sTitle" : "类型",
			"mData" : "pool_type",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'0': '私有',
					'1': '公有'
				};
				map[data] && (str = map[data]);
				return str;
			}
		},{
			"sTitle" : "操作",
			"mData" : "ip_segment",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_ippool_edit_' + data + '" href="configuration/ip_pool/' + data + '?displayName=IP池"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_ippool_view_' + data + '" href="configuration/ippool/view?id=' + data + '"><i class="fa fa-eye"></i> IP池详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#ippool_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=ip_pool&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});