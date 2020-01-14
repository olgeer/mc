define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [{
			"sTitle" : "所属品牌",
			"mData" : "db.brandname"
		},{
			"sTitle" : "型号名称",
			"mData" : "dm.modelname"
		},{
			"sTitle" : "高度",
			"mData" : "dm.u_bit"
		},{
			"sTitle" : "操作",
			"mData" : "dm.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_device_model_edit_' + data + '" href="configuration/device_model/' + data + '?displayName=设备型号"><i class="fa fa-pencil"></i> 编辑</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_view_' + data + '" href="configuration/assignedip/view?id=' + data + '"><i class="fa fa-eye"></i> IP详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=device_model dm, device_brand db&_joincols=dm.belongs_to_brand_id db.id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});