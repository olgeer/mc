define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.ip_segment;
		
		var vm = new Vue({
			el : '#ippool-' + mountId,
			data: {
				ippool: {},
				platform: {},
				ipType: {}
			}
		});
		
		initIppool(params, vm);
	}
	
	
	function initIppool(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=ip_pool&_action=get',
			data: params
		}).then(function(rs) {
			var ippool = rs.returndata.data[0];
			ippool && (vm.ippool = ippool);
			initPlatform({id: ippool.ip_pool_belongs_to_platform_id}, vm);
			ippool.belongs_to_iptype_id && initIpType({id: ippool.belongs_to_iptype_id}, vm)
		});
	}
	
	
	function initPlatform(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=platform&_action=get',
			data: params
		}).then(function(rs) {
			vm.platform = rs.returndata.data[0] || {};
		});
	}
	
	
	function initIpType(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=iptype&_action=get',
			data: params
		}).then(function(rs) {
			vm.ipType = rs.returndata.data[0] || {};
		});
	}
	
	
	return {
		init : init
	};
	
});