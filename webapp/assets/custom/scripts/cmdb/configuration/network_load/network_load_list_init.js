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
			"sTitle" : "负载VIP",
			"mData" : "load_ip"
		}, {
			"sTitle" : "虚拟端口",
			"mData" : "load_port"
		}, {
			"sTitle" : "真实IP",
			"mData" : "real_ip",
			"mRender": function (data) {
				data || (data == '');
				return data.split(',').join('<br>');
			}
		}, {
			"sTitle" : "真实端口",
			"mData" : "real_port"
		}, {
			"sTitle" : "会话限制",
			"mData" : "session_limit"
		}, {
			"sTitle" : "算法",
			"mData" : "load_arithmetic",
			"mRender" : getCodeToValueFn(params, 'load_arithmetic')
		}, {
			"sTitle" : "超时时间",
			"mData" : "load_timeout"
		}, {
			"sTitle" : "加密",
			"mData" : "encrypt",
			"mRender" : getCodeToValueFn(params, 'encrypt')
		}, {
			"sTitle" : "解密",
			"mData" : "decode",
			"mRender" : getCodeToValueFn(params, 'decode')
		}, {
			"sTitle" : "状态",
			"mData" : "status",
			"mRender" : getCodeToValueFn(params, 'status')
		}, {
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full) {
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_network_load_edit_' + data + '" href="configuration/cmdb_network_load/' + data + '?displayName=负载均衡"><i class="fa fa-pencil"></i> 编辑</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_view_' + data + '" href="configuration/assignedip/view?id=' + data + '"><i class="fa fa-eye"></i> IP详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	var tableObj;
	var initDataTableAjax = function(params) {
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_network_load&_action=get", getAoData(params));
	};
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});