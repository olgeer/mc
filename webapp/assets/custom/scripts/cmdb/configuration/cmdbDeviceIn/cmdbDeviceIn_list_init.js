define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "所属机房",
			"mData" : "room.short_name"
		}, {
			"sTitle" : "逻辑区域",
			"mData" : "cla.area_name"
		}, {
			"sTitle" : "设备名称",
			"mData" : "cdi.device_name"

		},{
			"sTitle" : "设备类型",
			"mData" : "cdi.device_type",
			"mRender" : function(data) {
				var str = '';
				var map = {
					'0': '服务器',
					'1': '存储设备',
					'2': '防火墙',
					'3': '交换机',
					'4': '路由器',
					'5': '其他设备',
					'6': '视频会议设备',
					'7': '无线设备',
					'8': '负载均衡',
					'9': 'IP',
					'10': '安全设备',
					'11': '板卡',
					'12': '堡垒机',
					'13': '第三方服务',
					'14': '电源',
					'15': '机箱',
					'16': '软件',
					'17': '许可证',
					'18': '专线',
					'19': '硬件'

				};
				map[data] && (str = map[data]);
				return str;
			}
		}, {
			"sTitle" : "序列号",
			"mData" : "cdi.device_sequence_number"
		}, {
				"sTitle" : "OOB地址",
				"mData" : "cdi.oob_adress"
			},{
			"sTitle" : "机柜位置",
			"mData" : "cdi.device_sequence_number",
			"mRender" : function(data,type,full){
				var str = [];
				full.cc.line && str.push(full.cc.line);
				full.cc.number && str.push(full.cc.number);
				return str.join('-');
			}

		},{
			"sTitle" : "操作",
			"mData" : "cdi.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbDeviceInformation_edit_' + data + '" href="configuration/cmdb_device_information/' + data + '?displayName=资产"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbDeviceInformation_view_' + data + '" href="configuration/cmdbDeviceInformation/view?id=' + data + '"><i class="fa fa-eye"></i> 资产详情</a>';
				return str;
			}
		}];
		return aoData;
	}



			var tableObj;
			var initDataTableAjax = function(params){
				tableObj = DataTableAjaxJsonp.init("#cmdbDeviceIn_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_device_information cdi,cmdb_room room,cmdb_logic_area cla,cmdb_cabinet_use ccu,cmdb_cabinet cc&_joincols=cdi.belong_to_room_id(-) room.id,cdi.belong_to_logic_area_id(-) cla.id,cdi.id(-) ccu.belong_to_device_id,ccu.belong_to_cabinet_id(-) cc.id&_action=get", getAoData(params));
			};


			return {
				init : function(params) {
					initDataTableAjax(params);
				}
			};


		});