define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "产品类型",
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
			"sTitle" : "产品名称",
			"mData" : "cdi.device_name"
		},{
			"sTitle" : "型号",
			"mData" : "cdi.device_model"
		},{
			"sTitle" : "序列号",
			"mData" : "cdi.device_sequence_number"
		}, {
			"sTitle" : "存放位置",
			"mData" : "cdi.physics_area"

		},{
			"sTitle" : "维保单位",
			"mData" : "cdi.supplier"

		}, {
			"sTitle" : "维保期限",
			"mData" : "cdi.guarantee_deadline"
		}, {
			"sTitle" : "过保时间",
			"mData" : "cdi.outof_guarantee_deadline"
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
				tableObj = DataTableAjaxJsonp.init("#cmdbDeviceInformation_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_device_information cdi,cmdb_device_model cdm,cmdb_room room,cmdb_logic_area cla&_joincols=cdi.belong_to_device_model_id(-) cdm.id,cdi.belong_to_room_id(-) room.id,cdi.belong_to_logic_area_id(-) cla.id&_action=get", getAoData(params));
			};


			return {
				init : function(params) {
					initDataTableAjax(params);
				}
			};


		});