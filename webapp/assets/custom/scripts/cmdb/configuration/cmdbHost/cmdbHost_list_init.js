define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "主机名",
			"mData" : "ch.host_name"
		}, {
			"sTitle" : "类型",
			"mData" : "ch.host_type",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'0': '虚拟机',
					'1': '物理机'

				};
				map[data] && (str = map[data]);
				return str;
			}
		}, {
			"sTitle" : "状态",
			"mData" : "ch.host_status",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'0': '投产中',
					'1': '维护中',
					'2': '待部署',
					'3': '已部署',
					'4': '回收'

				};
				map[data] && (str = map[data]);
				return str;
			}
		},{
			"sTitle" : "所属机房",
			"mData" : "room.short_name"
		},{
			"sTitle" : "平台名称",
			"mData" : "cp.platform_name"
		}, {
			"sTitle" : "操作系统ID",
			"mData" : "cos.operate_system_type_id"

		},{
			"sTitle" : "操作",
			"mData" : "ch.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbHost_edit_' + data + '" href="configuration/cmdb_host/' + data + '?displayName=主机"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbHost_view_' + data + '" href="configuration/cmdbHost/view?id=' + data + '"><i class="fa fa-eye"></i> 主机详情</a>';
				return str;
			}
		}];
		return aoData;
	}



			var tableObj;
			var initDataTableAjax = function(params){
				tableObj = DataTableAjaxJsonp.init("#cmdbHost_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_host ch,cmdb_room room,cmdb_platform cp,cmdb_operate_system cos&_joincols=ch.belong_to_room_id(-) room.id,ch.belong_to_platform_id(-) cp.id,ch.belong_to_os_id(-) cos.id&_action=get", getAoData(params));
			};


			return {
				init : function(params) {
					initDataTableAjax(params);
				}
			};


		});