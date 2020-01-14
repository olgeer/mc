define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.uuid;
		
		var vm = new Vue({
			el : $('#' + mountId)[0],
			data: {
				ipType: {},
				room: {}
			}
		});
		
		initIpType({id: params.id}, vm);
	}
	
	
	function initIpType(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=iptype&_action=get',
			data: params
		}).then(function(rs) {
			vm.ipType = rs.returndata.data[0] || {};
			initRoom({id: vm.ipType.ip_belongs_to_room_id}, vm);
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