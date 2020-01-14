define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		initVip(params, mountId);
	}
	
	
	function initVip(params, mountId) {
		var vm = new Vue({
			el : '#vip-' + mountId,
			data: {
				vip: {},
				ipType: {},
				publicLoadIps: [],
				applicationHref: false,
				applications: []
			}
		});
		
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=vip&_action=get',
			data: params
		}).then(function(rs) {
			vm.vip = rs.returndata.data[0] || {};
			vm.vip.vip_belongs_to_iptype_id && initIpType({id: vm.vip.vip_belongs_to_iptype_id}, vm);
		});
		
		initPublicLoadIp({relevant_id: params.id, target: 'vip'}, vm);
		initApplication({'awv.vip_id': params.id}, vm);
	}
	
	
	function initIpType(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=iptype&_action=get',
			data: params
		}).then(function(rs) {
			vm.ipType = rs.returndata.data[0] || {};
		});
	}
	
	
	function initPublicLoadIp(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=public_load_ip pli,public_ip_relevance pir&_joincols=pli.id pir.publicAndloadip_id&_action=get',
			data: params
		}).then(function(rs) {
			var publicLoadIp = rs.returndata.data;
			vm.publicLoadIps = cmdbUtils.fmData(publicLoadIp);
		});
	}
	
	
	function initApplication(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=application_with_vip awv,application a&_joincols=awv.application_id a.id&_action=get&_pagesize=0',
			data: params
		}).then(function(rs) {
			var applications = rs.returndata.data;
			vm.applications = cmdbUtils.fmData(applications);
		});
	}
	
	
	return {
		init : init
	};
	
});