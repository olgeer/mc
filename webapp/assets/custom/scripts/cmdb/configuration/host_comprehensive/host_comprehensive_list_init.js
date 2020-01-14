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
			"sTitle" : "平台",
			"mData" : "platform_name"
		}, {
			"sTitle" : "主机名",
			"mData" : "host_name"
		}, {
			"sTitle" : "平台类型",
			"mData" : "logic_area"
		}, {
			"sTitle" : "主机IP",
			"mData" : "ip",
			"mRender": function (data) {
				data || (data == '');
				return data.split(',').join('<br>');
			}
		}, {
			"sTitle" : "集群VIP",
			"mData" : "vip_ip",
			"mRender": function (data) {
				data || (data == '');
				return data.split(',').join('<br>');
			}
		}, {
			"sTitle" : "负载VIP",
			"mData" : "load_ip",
			"mRender": function (data) {
				data || (data == '');
				return data.split(',').join('<br>');
			}
		}, {
			"sTitle" : "公网IP",
			"mData" : "public_ip",
			"mRender": function (data) {
				data || (data == '');
				return data.split(',').join('<br>');
			}
		}, {
			"sTitle" : "应用类型",
			"mData" : "app_type",
			"mRender" : getCodeToValueFn(params, 'app_type')
		}, {
			"sTitle" : "服务名称",
			"mData" : "services",
			"mRender": function (data) {
				data || (data == '');
				return data.split(',').join('<br>');
			}
		}, {
			"sTitle" : "主机状态",
			"mData" : "host_type",
			"mRender" : getCodeToValueFn(params, 'host_type')
		}, {
			"sTitle" : "操作",
			"mData" : "host_id",
			"bSortable" : false,
			"mRender" : function (data) {
				var str = '';
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_host_comprehensive_view_' + data + '" href="configuration/host_comprehensive/view?id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
				return str;
			}
		}];
		
		return aoData;
	}
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_host_information&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});