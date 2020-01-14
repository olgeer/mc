define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		initAssignedIp(params, mountId);
	}
	
	
	function initAssignedIp(params, mountId) {
		var vm = new Vue({
			el : '#assignedip-' + mountId,
			data: {
				assignedip: {},
				ipType: {},
				host: {},
				publicLoadIps: []
			}
		});
		
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=assigned_ip&_action=get',
			data: params
		}).then(function(rs) {
			vm.assignedip = rs.returndata.data[0] || {};
			vm.assignedip.belongs_to_iptype_id && initIpType({id: vm.assignedip.belongs_to_iptype_id}, vm);
			vm.assignedip.belongs_to_host_id && initHost({id: vm.assignedip.belongs_to_host_id}, vm);
		});
		
		initPublicLoadIp({relevant_id: params.id, target: 'assigned_ip'}, vm);
	}
	
	
	function initIpType(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=iptype&_action=get',
			data: params
		}).then(function(rs) {
			vm.ipType = rs.returndata.data[0] || {};
		});
	}
	
	
	function initHost(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=host&_action=get',
			data: params
		}).then(function(rs) {
			vm.host = rs.returndata.data[0] || {};
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
	
	
	return {
		init : init
	};
	
});