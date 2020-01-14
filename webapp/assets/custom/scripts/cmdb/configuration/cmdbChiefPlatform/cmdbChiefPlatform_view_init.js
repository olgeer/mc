define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		var vm = new Vue({
			el : '#cmdbChiefPlatform-' + mountId,
			data: {
				cmdbChiefPlatform: {}
				//cmdbPlatform: {}
			}
		});

		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_chief_platform&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbChiefPlatform = rs.returndata.data[0];
			vm.cmdbChiefPlatform = cmdbChiefPlatform;
			//params.id && initcmdbPlatform({belongs_to_chief_id:params.id}, vm);
		});
		
	}

	
	/*function initcmdbPlatform(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_platform&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbPlatform = rs.returndata.data[0];
			vm.cmdbPlatform = cmdbPlatform;
		});
	}*/


	return {
		init : init
	};
	
});