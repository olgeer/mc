define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "app类型",
			"mData" : "at.app_type"
		}, {
			"sTitle" : "app分类",
			"mData" : "at.app_detail_type"
		}, {
			"sTitle" : "服务名称",
			"mData" : "a.application_name"
		}, {
			"sTitle" : "主机名",
			"mData" : "a.hostname"
		},{
			"sTitle" : "操作",
			"mData" : "a.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_application_edit_' + data + '" href="configuration/application/' + data + '?displayName=应用"><i class="fa fa-pencil"></i> 编辑</a>');
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_application_add_vip_' + data + '" href="configuration/application_with_vip/add?displayName=vip&application_id=' + data + '"><i class="fa fa-pencil"></i> 添加vip</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_application_view_' + data + '" href="configuration/application/view?id=' + data + '"><i class="fa fa-eye"></i> 应用详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#application_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=host_applications ha,application a,app_type at&_joincols=ha.application_id a.id,a.belongs_to_apptype_id at.id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});