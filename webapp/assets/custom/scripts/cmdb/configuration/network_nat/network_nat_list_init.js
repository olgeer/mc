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
			"sTitle" : "NAT类型",
			"mData" : "nat_type",
			"mRender" : getCodeToValueFn(params, 'nat_type')
		}, {
			"sTitle" : "源IP",
			"mData" : "nat_source_ip",
			"mRender": function (data) {
				data || (data == '');
				return data.split(',').join('<br>');
			}
		}, {
			"sTitle" : "目的IP",
			"mData" : "nat_before_ip",
			"mRender": function (data) {
				data || (data == '');
				return data.split(',').join('<br>');
			}
		}, {
			"sTitle" : "目的端口",
			"mData" : "nat_before_port"
		}, {
			"sTitle" : "映射IP",
			"mData" : "nat_after_ip",
			"mRender": function (data) {
				data || (data == '');
				return data.split(',').join('<br>');
			}
		}, {
			"sTitle" : "映射端口",
			"mData" : "nat_after_port"
		}, {
			"sTitle" : "状态",
			"mData" : "nat_status",
			"mRender" : getCodeToValueFn(params, 'nat_status')
		}, {
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_network_nat_edit_' + data + '" href="configuration/cmdb_network_nat/' + data + '?displayName=NAT"><i class="fa fa-pencil"></i> 编辑</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_view_' + data + '" href="configuration/assignedip/view?id=' + data + '"><i class="fa fa-eye"></i> IP详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_network_nat&_action=get", getAoData(params));
	};
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});