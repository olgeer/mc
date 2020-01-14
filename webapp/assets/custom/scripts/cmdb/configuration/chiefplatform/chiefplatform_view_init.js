define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		var vm = new Vue({
			el : '#chiefplatform-' + mountId,
			data: {
				chiefPlatform: {},
				platforms: []
			}
		});
		
		initChiefPlatform(params, vm);
		initPlatform({belongs_to_chief_id: params.id, _pagesize: 10}, vm);
	}

	
	function initPlatform(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=platform&_action=get',
			data: params
		}).then(function(rs) {
			vm.platforms = rs.returndata.data;
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
	
	
	return {
		init : init
	};
	
});