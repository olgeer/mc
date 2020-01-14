define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getCodeToValueFn(params, name) {
		var map = {};
		$('#' + params.uuid + ' [name="' + name + '"] option').each(function() {
			$(this).attr('value') && (map[$(this).attr('value')] =  $(this).text());
		});
		
		return function(data) {
			return map[data] || data;
		}
	}
	
	function getAoData(params) {
		var aoData = [{
			"sTitle" : "工单号",
			"mData" : "flow_no"
		}, {
			"sTitle" : "流程标题",
			"mData" : "process_title"
		}, {
			"sTitle" : "开始时间",
			"mData" : "start_time",
			"mRender" : function(data) {
				data || (data == '');
				if (data) {
					
				}
				return data;
			}
		}, {
			"sTitle" : "结束时间",
			"mData" : "end_time"
		}, {
			"sTitle" : "流程状态",
			"mData" : "flow_status",
			"mRender" : getCodeToValueFn(params, 'flow_status')
		}, {
			"sTitle" : "操作",
			"mData" : "abc",
			"bSortable" : false
		}];

		return aoData;
	}
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/report?_action=flowlist", getAoData(params));
	};
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});