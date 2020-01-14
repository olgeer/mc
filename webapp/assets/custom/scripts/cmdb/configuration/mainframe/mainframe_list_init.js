define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {

	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "主机名称",
			"mData" : "h.hostname"
		}, {
			"sTitle" : "平台名称",
			"mData" : "p.platform_name"
		}, {
			"sTitle" : "主机内存",
			"mData" : "h.host_mem"
		},{
			"sTitle" : "主机硬盘",
			"mData" : "h.host_disk"
		},{
			"sTitle" : "cpu数",
			"mData" : "h.host_cpu"
		},{
			"sTitle" : "状态",
			"mData" : "h.host_status",
			"mRender" : function(data) {
				var str = '';
				var map = {
						'0': '生产',
				        '1': '下线',
				        '2': '维修',
				        '3': '未上线'
					};
				map[data] && (str = map[data]);
				return str;
			}
		},{
			"sTitle" : "备注",
			"mData" : "h.host_remarks"
		},{
			"sTitle" : "操作",
			"mData" : "h.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_mainframe_edit_' + data + '" href="configuration/host/' + data + '?displayName=主机"><i class="fa fa-pencil"></i> 编辑</a>');
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_mainframe_add_ip_' + data + '" href="configuration/assigned_ip/add?displayName=IP&belongs_to_host_id=' + data + '"><i class="fa fa-pencil"></i> 添加IP</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_mainframe_view_' + data + '" href="configuration/mainframe/view?id=' + data + '"><i class="fa fa-eye"></i> 主机详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	
	
	var tableObj;
	var initDataTableAjax = function(params) {
		tableObj = DataTableAjaxJsonp.init("#mainframe_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=host h, platform p&_joincols=h.belongs_to_PlatForm_id p.id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});