define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {

	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "项目名称",
			"mData" : "chief_platform_name"
		},{
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
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_chiefplatform_edit_' + data + '" href="configuration/chief_platform/' + data + '?displayName=平台"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_chiefplatform_view_' + data + '" href="configuration/chiefplatform/view?id=' + data + '"><i class="fa fa-eye"></i> 平台详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#chiefplatform_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=chief_platform&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});