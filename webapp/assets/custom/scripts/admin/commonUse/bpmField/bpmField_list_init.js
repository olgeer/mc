define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {

	function getAoData(params) {
		var aoData = [ 
                {
	            "sTitle" : "表单",
	            "mData" : "bf.bpfo_name"
                 },{
	            "sTitle" : "表中文名称",
	            "mData" : "bpta.bpta_zh_name"
                 },
		 {
			"sTitle" : "表英文名称",
			"mData" : "bpta.bpta_en_name"
		}, {
			"sTitle" : "字段中文名称",
			"mData" : "bpfi.bpfi_zh_field"
		}, {
			"sTitle" : "字段英文名称",
			"mData" : "bpfi.bpfi_en_field"
		},{
			"sTitle" : "操作",
			"mData" : "bpfi.bpfi_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a refresh  class="btn btn-xs blue ajaxify" module_id="module_bpmField_edit_' + data + '" href="admin/commonUse/bpm_field/' + data + '?displayName=字段配置"><i class="fa fa-pencil"></i> 编辑</a>');
				return str;
			}
		}];
		return aoData;
	}
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#bpmField_datatable_" + params.uuid, cmdbHost + "/itil?_tablename=bpm_field bpfi,bpm_table bpta,bpm_form_table bft,bpm_form bf&_joincols=bpfi.bpta_id bpta.bpta_id,bpta.bpta_id bft.bpta_id,bft.bpfo_id bf.bpfo_id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});