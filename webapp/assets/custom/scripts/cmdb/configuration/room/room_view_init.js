define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;

		var vm = new Vue({
			el : '#room-content-' + mountId,
			data: {
				room: {},
				devices: []
			}
		});
		

		initRoom(params, vm);
		initDevice({
			belongs_to_room_id: params.id,
			_pagesize: 10
		}, vm);
	}
	
	
	function initRoom(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=room&_action=get',
			data: params
		}).then(function(rs) {
			var room = rs.returndata.data[0];
			vm.room = room;
		});
	}
	
	
	function initDevice(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=device&_action=get',
			data: params
		}).then(function(rs) {
			var devices = rs.returndata.data;
			vm.devices = devices;
		});
	}
	

	return {
		init : init
	};
	
});