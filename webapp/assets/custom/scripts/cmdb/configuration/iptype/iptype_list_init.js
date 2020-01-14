define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "ip类型名",
			"mData" : "typename"
		}, {
			"sTitle" : "网络区域",
			"mData" : "network_area"
		}, {
			"sTitle" : "网络分类",
			"mData" : "network_zone"
		}, {
			"sTitle" : "起始位ip",
			"mData" : "start"
		}, {
			"sTitle" : "末位ip",
			"mData" : "end"
		}, {
			"sTitle" : "升将序",
			"mData" : "order",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'0': '升序',
					'1': '降序'
				};
				map[data] && (str = map[data]);
				return str;
			}
		}, {
			"sTitle" : "所属的类型",
			"mData" : "ip_type"
		}, {
			"sTitle" : "是否成对",
			"mData" : "in_pairs",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'0': '否',
					'1': '是'
				};
				map[data] && (str = map[data]);
				return str;
			}
		},{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_iptype_edit_' + data + '" href="configuration/iptype/' + data + '?displayName=IP类型"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_iptype_view_' + data + '" href="configuration/iptype/view?id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=iptype&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});