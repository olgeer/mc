define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.uuid;
		
		var vm = new Vue({
			el : $('#' + mountId)[0],
			data: {
				networkDevice: {},
				deviceAssignedips: [],
				externalAssignedips: [],
				internalAssignedips: [],
				loopbackAssignedips: []
			}
		});
		
		initNetworkDevice({id: params.id}, vm);
	}
	
	
	function initNetworkDevice(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=network_device&_action=get',
			data: params
		}).then(function(rs) {
			vm.networkDevice = rs.returndata.data[0] || {};
			
			if (vm.networkDevice.binding_device_id) {
				cmdbUtils.getcmdbData({
					url: '/mc?_tablename=network_device&_action=get',
					data: {id: vm.networkDevice.binding_device_id}
				}).then(function(rs) {
					var bindingDevice = rs.returndata.data[0];
					Vue.set(vm.networkDevice, 'bindingDevice', bindingDevice);
				});
			}
			
		});
		
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=network_device_ip ndi,assigned_ip ai&_joincols=ndi.assignedip_id ai.id&_action=get',
			data: {'ndi.network_device_id': params.id}
		}).then(function(rs) {
			vm.deviceAssignedips = cmdbUtils.fmData(rs.returndata.data);
		});
		
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=network_device_external_ip ndi,assigned_ip ai&_joincols=ndi.assignedip_id ai.id&_action=get',
			data: {'ndi.network_device_id': params.id}
		}).then(function(rs) {
			vm.externalAssignedips = cmdbUtils.fmData(rs.returndata.data);
		});
		
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=network_device_internal_ip ndi,assigned_ip ai&_joincols=ndi.assignedip_id ai.id&_action=get',
			data: {'ndi.network_device_id': params.id}
		}).then(function(rs) {
			vm.internalAssignedips = cmdbUtils.fmData(rs.returndata.data);
		});
		
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=network_device_loopback_ip ndi,assigned_ip ai&_joincols=ndi.assignedip_id ai.id&_action=get',
			data: {'ndi.network_device_id': params.id}
		}).then(function(rs) {
			vm.loopbackAssignedips = cmdbUtils.fmData(rs.returndata.data);
		});
	}
	
	return {
		init : init
	};
	
});