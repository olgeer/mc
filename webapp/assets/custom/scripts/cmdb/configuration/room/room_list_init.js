define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "机房名称",
			"mData" : "roomname"
		}, {
			"sTitle" : "机房地址",
			"mData" : "address"
		}, {
			"sTitle" : "联系人",
			"mData" : "contacts"
		}, {
			"sTitle" : "联系人电话",
			"mData" : "contacts_tel"
		},{
			"sTitle" : "带宽",
			"mData" : "broad_band"
		},{
			"sTitle" : "运营商",
			"mData" : "line"
		},{
			"sTitle" : "明细",
			"mData" : "remarks"
		},{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_room_edit_' + data + '" href="configuration/room/' + data + '?displayName=机房"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_room_view_' + data + '" href="configuration/room/view?id=' + data + '"><i class="fa fa-eye"></i> 机房详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#room_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=room&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});