define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "平台",
			"mData" : "p.platform_name"
		},{
			"sTitle" : "公网负载IP",
			"mData" : "pli.ip"
		}, {
			"sTitle" : "关系",
			"mData" : "pir.relation"
		}, {
			"sTitle" : "目标",
			"mData" : "pir.ip",
			"mRender" : function(ip, type, data) {
				var target = data.pir.target == 'assigned_ip' ? 'assignedip' : data.pir.target;
				var moduleId =  'module_' + target + '_view_' + data.pir.relevant_id;
				var url = 'configuration/' + target + '/view?id=' + data.pir.relevant_id;
				return '<a module_id="' + moduleId + '" href="' + url + '" data-html="true" class="btn btn-xs ajaxify">' + ip + '</a><a class="hidden"><i class="fa fa-eye"></i> 详情</a>';
			}
		}, {
			"sTitle" : "类型",
			"mData" : "pir.target",
			"mRender" : function(data) {
				if (data == 'public_load_ip') {
					return '公网';
				} else if (data == 'assigned_ip') {
					return '物理';
				} else if (data == 'vip') {
					return 'vip';
				}
				return '';
			}
		}, {
			"sTitle" : "端口",
			"mData" : "pir.port"
		}, {
			"sTitle" : "备注",
			"mData" : "pli.remarks"
		},{
			"sTitle" : "操作",
			"mData" : "pli.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_public_load_ip_edit_' + data + '" href="configuration/public_load_ip/' + data + '?displayName=公网IP"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_public_load_ip_view_' + data + '" href="configuration/public_load_ip/view?id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=public_load_ip pli, public_ip_relevance pir, platform p&_joincols=pli.id(-) pir.publicAndloadip_id,p.id pli.belongs_to_platform_id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});