define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "策略名称",
			"mData" : "name"
		}, {
			"sTitle" : "源主机",
			"mData" : "src_host"
		}, {
			"sTitle" : "源IP",
			"mData" : "src_ip"
		}, {
			"sTitle" : "目的主机",
			"mData" : "dst_host"
		}, {
			"sTitle" : "目的IP",
			"mData" : "dst_ip"
		}, {
			"sTitle" : "协议",
			"mData" : "policy_protocol"
		}, {
			"sTitle" : "端口",
			"mData" : "port"
		}, {
			"sTitle" : "生效时间",
			"mData" : "start_date"
		}, {
			"sTitle" : "过期时间",
			"mData" : "end_date"
		}, {
			"sTitle" : "状态",
			"mData" : "state",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'1': '有效',
					'2': '无效',
					'3': '过期'
				};
				map[data] && (str = map[data]);
				return str;
			}
		},{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_network_policy_edit_' + data + '" href="configuration/network_policy/' + data + '?displayName=网络策略"><i class="fa fa-pencil"></i> 编辑</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_view_' + data + '" href="configuration/assignedip/view?id=' + data + '"><i class="fa fa-eye"></i> IP详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=network_policy&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});