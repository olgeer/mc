define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "机房名称",
			"mData" : "room_name"
		}, {
			"sTitle" : "联系人",
			"mData" : "contacts"
		}, {
			"sTitle" : "联系人电话",
			"mData" : "contacts_tel"
		},{
			"sTitle" : "出口带宽",
			"mData" : "broad_band"
		},{
			"sTitle" : "运营商",
			"mData" : "operator"
		}, {
			"sTitle" : "线路",
			"mData" : "line"

		},{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbRoom_edit_' + data + '" href="configuration/cmdb_room/' + data + '?displayName=机房"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbRoom_view_' + data + '" href="configuration/cmdbRoom/view?id=' + data + '"><i class="fa fa-eye"></i> 机房详情</a>';
				return str;
			}
		}];
		return aoData;
	}



			var tableObj;
			var initDataTableAjax = function(params){
				tableObj = DataTableAjaxJsonp.init("#cmdbRoom_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_room&_action=get", getAoData(params));
			};


			return {
				init : function(params) {
					initDataTableAjax(params);
				}
			};


		});