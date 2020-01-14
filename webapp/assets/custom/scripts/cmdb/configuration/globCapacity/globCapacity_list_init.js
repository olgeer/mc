define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {

	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "产品名称",
			"mData" : "gc.remind_obj"
		}, {
			"sTitle" : "产品序列号",
			"mData" : "cl.equipment_serial_number"
		}, {
			"sTitle" : "产品型号",
			"mData" : "cl.equipment_type"
		}, {
			"sTitle" : "容量能力",
			"mData" : "gc.capacity_ability"
		},{
			"sTitle" : "使用量",
			"mData" : "gc.capacity_use"
		},
		{
			"sTitle" : "使用率",
			"mData" : "gc.capacity_rate"
		}
		,	{
			"sTitle" : "使用情况",
			"mData" : "gc.end_use_remind_status",
			"mRender" : function(data, type, full){
			if(data === 0) {
				return '<span class="label label-primary"> 充足 </span>';
			}
			if(data==1) {
				return '<span class="label label-warning"> 黄色 </span>';
			}if(data==2) {
				return '<span class="label label-info"> 红色 </span>';
			}
			if(data==8) {
				return '<span class="label label-danger"> 用完 </span>';
			}
		 }
		}
		,{
			"sTitle" : "操作",
			"mData" : "gc.belong_to_detail_object_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				
				if(full.gc.detail_object_tablename=="cmdb_licence"){
					var str = '';
					params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_globCapacity_edit_' + data + '" href="configuration/globCapacity/glob_capacity/' + full.glob_cap_id+ '?displayName=容量管理"><i class="fa fa-pencil"></i> 编辑</a>');
					str += '<a class="btn btn-xs blue ajaxify"module_id="module_globCapacity_view_' + data + '" href="configuration/globCapacity/view?belong_to_detail_object_id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
					return str;
			}else{
				return "暂时没数据可链接";
			}
				
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#globCapactity_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=glob_capacity gc,cmdb_licence cl&_joincols=gc.belong_to_detail_object_id cl.licence_id&gc.data_valid=1&gc.detail_object_tablename=cmdb_licence&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});