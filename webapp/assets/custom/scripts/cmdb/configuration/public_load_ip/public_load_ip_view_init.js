define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		initPublicLoadIp(params, mountId);
	}
	
	
	function initPublicLoadIp(params, mountId) {
		var vm = new Vue({
			el : '#public_load_ip-' + mountId,
			data: {
				publicLoadIp: {},
				publicIpRelevances: [],
				platform: {}
			},
			filters: {
				relevanceModuleId: function(data) {
					var target = data.target == 'assigned_ip' ? 'assignedip' : data.target;
					return 'module_' + target + '_view_' + data.relevant_id;
				},
				relevanceHref: function(data) {
					var target = data.target == 'assigned_ip' ? 'assignedip' : data.target;
					return 'configuration/' + target + '/view?id=' + data.relevant_id;
				}
			}
		});
		
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=public_load_ip&_action=get',
			data: params
		}).then(function(rs) {
			vm.publicLoadIp = rs.returndata.data[0] || {};
			vm.publicLoadIp.belongs_to_platform_id && initPlatform({id: vm.publicLoadIp.belongs_to_platform_id}, vm);
		});
		
		initPublicIpRelevance({publicAndloadip_id: params.id}, vm);
	}
	
	
	function initPublicIpRelevance(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=public_ip_relevance&_action=get',
			data: params
		}).then(function(rs) {
			vm.publicIpRelevances = rs.returndata.data;
		});
	}
	
	
	function initPlatform(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=platform&_action=get',
			data: params
		}).then(function(rs) {
			vm.platform = rs.returndata.data[0];
		});
	}
	
	
	return {
		init : init
	};
	
});