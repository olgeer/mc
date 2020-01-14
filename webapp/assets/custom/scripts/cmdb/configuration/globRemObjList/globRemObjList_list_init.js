define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {

	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "提醒对象名称",
			"mData" : "go.glob_name"
		}, {
			"sTitle" : "资产类型",
			"mData" : "go.glob_asset_type",
			"mRender" : function(data, type, full){
				if(data === 1) {
					return '<span class="label label-primary"> 有型资产 </span>';
				}
				if(data==2) {
					return '<span class="label label-success"> 无型资产 </span>';
				}else{
					return '<span class="label label-success"> 未确认 </span>';
				}
			}
		}, {
			"sTitle" : "到期时间",
			"mData" : "grol.grol_rem_end_date"
		},
		{
			"sTitle" : "当前提醒",
			"mData" : "grol.grol_rem_status",
			"mRender" : function(data, type, full){
				if(data === 0) {
					return '<span class="label label-success"> 未到期 </span>';
				}
				if(data==1) {
					return '<span class="label label-warning"> 黄色到期需提醒 </span>';
				}if(data==2){
					return '<span class="label label-info"> 红色到期提醒 </span>';
				}if(data==8){
					return '<span class="label label-danger"> 已到期 </span>';
				}
			}
		},
		{
			"sTitle" : "任务状态",
			"mData" : "gr.glre_status",
			"mRender" : function(data, type, full){
				if(data === 0) {
					return '<span class="label label-primary"> 未激活 </span>';
				}
				if(data==1) {
					return '<span class="label label-success"> 激活 </span>';
				}if(data==9){
					return '<span class="label label-success"> 终止 </span>';
				}
			}
		}
		,{
			"sTitle" : "操作",
			"mData" : "grol.grol_obj_detail_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				if(full.go.glob_table_name=="cmdb_licence"){
				return '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbLicence_view_' + data + '" href="configuration/globRemObjList/view?grol_obj_detail_id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
			}else{
				return "";
			}
				
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#globRemObjList_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=glob_rem_obj_list grol,glob_obj go,glob_remind gr&_joincols=grol.grol_obj_id go.glob_id ,grol.grol_rem_id gr.glre_id &_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});