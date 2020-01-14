define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "维护编码",
			"mData" : "maintenance_code"
		}, {
			"sTitle" : "cpu",
			"mData" : "device_cpu"
		}, {
			"sTitle" : "内存",
			"mData" : "device_mem"
		}, {
			"sTitle" : "硬盘",
			"mData" : "device_disk"
		}, {
			"sTitle" : "位置",
			"mData" : "device_disk",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = [];
				full.room_col && str.push(full.room_col);
				full.col_index && str.push(full.col_index);
				full.start_u_bit && str.push(full.start_u_bit);
				return str.join('-');
			}
		},{
			"sTitle" : "购买时间",
			"mData" : "purchase_date"
		},{
			"sTitle" : "保修时间",
			"mData" : "warranty_date"
		},{
			"sTitle" : "状态",
			"mData" : "device_status",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'0': '上架',
			        '1': '生产',
			        '2': '维修',
			        '3': '下线'
				};
				map[data] && (str = map[data]);
				return str;
			}
		},
		{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_device_edit_' + data + '" href="configuration/device/' + data + '?displayName=设备"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_device_view_' + data + '" href="configuration/device/view?id=' + data + '"><i class="fa fa-eye"></i> 设备详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#device_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=device&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});