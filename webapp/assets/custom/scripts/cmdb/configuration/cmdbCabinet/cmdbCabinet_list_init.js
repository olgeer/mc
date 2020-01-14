define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {

	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "机房名称",
			"mData" : "room.room_name"
		},{
			"sTitle" : "逻辑区域",
			"mData" : "ca.logic_area"
		}, {
			"sTitle" : "机柜高度（U）",
			"mData" : "ca.height_u"
		}, {
			"sTitle" : "剩余最大U",
			"mData" : "ca.max_empty_height_u"
		},{
			"sTitle" : "PDU容量（W）",
			"mData" : "ca.pdu_capacity"
		},{
			"sTitle" : "操作",
			"mData" : "ca.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbCabinet_edit_' + data + '" href="configuration/cmdb_cabinet/' + data + '?displayName=机柜"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbCabinet_view_' + data + '" href="configuration/cmdbCabinet/view?id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#cmdbCabinet_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_cabinet ca,cmdb_room room&_joincols=ca.belong_to_room_id room.id&_action=get", getAoData(params));
	};


	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});