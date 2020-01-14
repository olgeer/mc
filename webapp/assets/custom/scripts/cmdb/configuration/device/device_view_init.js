define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		var deviceVm = new Vue({
			el : '#device-' + mountId,
			data: {
				device: {},
				deviceModel: {},
				deviceType: null,
				room: {},
				hosts: []
			}
		});
		
		initDevice({'d.id': params.id}, deviceVm);
	}
	
	
	function initDevice(params, deviceVm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=device d, device_model dm&_joincols=d.belongs_to_model_id dm.id&_action=get',
			data: params
		}).then(function(rs) {
			var data = cmdbUtils.fmData(rs.returndata.data[0]);
			deviceVm.device = data.d;
			deviceVm.deviceModel = data.dm;
			initRoom({id: data.d.belongs_to_room_id}, deviceVm);
		});
		
		initDeviceType(deviceVm, params['d.id']);
	}
	
	
	function initDeviceType(deviceVm, id) {
		var searchTable = [{
			tableName: 'host',
			fk: 'belongs_to_device_id',
			typeName: '主机'
		}, {
			tableName: 'assigned_ip',
			fk: 'belongs_to_device_id',
			typeName: '网络设备'
		}];
		
		var searchRs = [];
		for (var i = 0; i < searchTable.length; i++) {
			var params = {};
			params[searchTable[i].fk] = id;
			searchRs.push(cmdbUtils.getcmdbData({
				url: '/mc?_action=get&_pagesize=10&_tablename=' + searchTable[i].tableName,
				data: params
			}));
		}
		
		$.when.apply(null, searchRs).then(function() {
			var typeRs = [];
			for (var i = 0; i < arguments.length; i++) {
				var rs = arguments[i][0];
				if (rs.returncode == '200' && rs.returndata.resultsetparameter.total >= 1) {
					typeRs.push(searchTable[i].typeName);
					if (i == 0) {
						deviceVm.hosts = rs.returndata.data;
					}
				} 
			}
			deviceVm.deviceType = typeRs.join(',');
		});
	}
	
	
	function initRoom(params, deviceVm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=room&_action=get',
			data: params
		}).then(function(rs) {
			var room = rs.returndata.data[0];
			deviceVm.room = room;
		});
	}
	
	
	return {
		init : init
	};
	
});