define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		var vm = new Vue({
			el : '#application-' + mountId,
			data: {
				application: {},
				hosts: [],
				vips: []
			}
		});
		
		
		initApplication(params, vm);
		initHost({application_id: params.id}, vm);
		initVip({application_id: params.id}, vm)
	}
	
	
	function initApplication(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=application&_action=get',
			data: params
		}).then(function(rs) {
			var application = rs.returndata.data[0];
			application && (vm.application = application);
		});
	}


	function initHost(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=host_applications ha,host h&_joincols=ha.host_id h.id&_action=get',
			data: params
		}).then(function(rs) {
			var host = rs.returndata.data;
			vm.hosts = cmdbUtils.fmData(host);
		});
	}
	
	
	function initVip(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=application_with_vip awv,vip v&_joincols=awv.vip_id v.id&_action=get',
			data: params
		}).then(function(rs) {
			var vips = rs.returndata.data;
			vm.vips = cmdbUtils.fmData(vips);
		});
	}
	
	return {
		init : init
	};
	
});