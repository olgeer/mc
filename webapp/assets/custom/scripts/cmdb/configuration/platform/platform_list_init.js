define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "平台名称",
			"mData" : "platform_name"
		}, {
			"sTitle" : "公司",
			"mData" : "company"
		}, {
			"sTitle" : "项目经理",
			"mData" : "manager"
		}, {
			"sTitle" : "项目经理电话",
			"mData" : "manager_tel"
		},{
			"sTitle" : "研发人",
			"mData" : "developer"
		},{
			"sTitle" : "研发人电话",
			"mData" : "developer_tel"
		},{
			"sTitle" : "状态",
			"mData" : "status",
			"mRender" : function(data,type,full){
				var str = '';
				var map = {
					'0': '生产',
					'1': '下线',
					'2': '未上线',
					'3': '内部平台使用'
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
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_platform_edit_' + data + '" href="configuration/platform/' + data + '?displayName=项目"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_platform_view_' + data + '" href="configuration/platform/view?id=' + data + '"><i class="fa fa-eye"></i> 项目详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#platform_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=platform&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});