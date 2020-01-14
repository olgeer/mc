define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		var vm = new Vue({
			el : '#platform-' + mountId,
			data: {
				platform: {},
				chiefPlatform: {},
				ippool: {},
				hosts: []
			}
		});
		
		initPlatform(params, vm);
		initIppool({ip_pool_belongs_to_platform_id: params.id}, vm);
		initHost({belongs_to_PlatForm_id: params.id, _pagesize: 10}, vm);
	}

	
	function initPlatform(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=platform&_action=get',
			data: params
		}).then(function(rs) {
			var platform = rs.returndata.data[0];
			vm.platform = platform || {};
			initChiefPlatform({id: platform.belongs_to_chief_id}, vm)
		});
	}


	function initChiefPlatform(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=chief_platform&_action=get',
			data: params
		}).then(function(rs) {
			vm.chiefPlatform = rs.returndata.data[0] || {};
		});
	}
	
	
	function initIppool(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=ip_pool&_action=get',
			data: params
		}).then(function(rs) {
			vm.ippool = rs.returndata.data[0] || {};
		});
	}
	
	function initHost(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=host&_action=get',
			data: params
		}).then(function(rs) {
			vm.hosts = rs.returndata.data;
		});
	}

	
	return {
		init : init
	};
	
});