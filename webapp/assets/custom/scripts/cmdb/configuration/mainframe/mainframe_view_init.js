define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		var vm = new Vue({
			el : '#mainframe-' + mountId,
			data: {
				host: {},
				osType: {},
				device: {},
				deviceModel: {},
				deviceType: null,
				applicationHref: true,
				applications: [],
				platform: {},
				chiefPlatform: {},
				assignedips: []
			}
		});
		
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=host&_action=get',
			data: params
		}).then(function(rs) {
			var host = rs.returndata.data[0];
			vm.host = host;
			
			host.belongs_to_ostype_id && initOs({id: host.belongs_to_ostype_id}, vm);
			host.belongs_to_device_id && initDevice({'d.id': host.belongs_to_device_id}, vm);
			host.id && initApplication({host_id: host.id}, vm);
			host.belongs_to_PlatForm_id && initPlatform({id: host.belongs_to_PlatForm_id}, vm);

		});
		
		initAssignedip({belongs_to_host_id: params.id}, vm);
	}
	
	
	function initOs(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=os_type&_action=get',
			data: params
		}).then(function(rs) {
			var osType = rs.returndata.data[0];
			vm.osType = osType;
		});
	}
	
	
	function initDevice(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=device d, device_model dm&_joincols=d.belongs_to_model_id dm.id&_action=get',
			data: params
		}).then(function(rs) {
			var data = cmdbUtils.fmData(rs.returndata.data[0]);
			vm.device = data.d;
			vm.deviceModel = data.dm;
		});
	}


	function initApplication(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=host_applications ha,application a&_joincols=ha.application_id a.id&_action=get&_pagesize=10',
			data: params
		}).then(function(rs) {
			var applications = rs.returndata.data;
			vm.applications = cmdbUtils.fmData(applications);
		});
	}


	function initPlatform(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=platform&_action=get',
			data: params
		}).then(function(rs) {
			var platform = rs.returndata.data[0];
			vm.platform = platform;
			initChiefPlatform({id: platform.belongs_to_chief_id}, vm)
		});
	}
	
	
	function initChiefPlatform(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=chief_platform&_action=get',
			data: params
		}).then(function(rs) {
			vm.chiefPlatform = rs.returndata.data[0];
		});
	}
	
	
	function initAssignedip(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=assigned_ip&_action=get',
			data: params
		}).then(function(rs) {
			vm.assignedips = rs.returndata.data;
		});
	}
	
	
	return {
		init : init
	};
	
});