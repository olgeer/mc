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
			"sTitle" : "源IP",
			"mData" : "src_ip"
		}, {
			"sTitle" : "目的IP",
			"mData" : "dst_ip"
		}, {
			"sTitle" : "目的端口",
			"mData" : "dst_port"
		}, {
			"sTitle" : "动作",
			"mData" : "action",
			"mRender" : getCodeToValueFn(params, 'action')
		}, {
			"sTitle" : "备注",
			"mData" : "remarks"
		}, {
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_network_policy_edit_' + data + '" href="configuration/cmdb_network_policy/' + data + '?displayName=网络策略"><i class="fa fa-pencil"></i> 编辑</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_view_' + data + '" href="configuration/assignedip/view?id=' + data + '"><i class="fa fa-eye"></i> IP详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_network_policy&_action=get", getAoData(params));
	};
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});