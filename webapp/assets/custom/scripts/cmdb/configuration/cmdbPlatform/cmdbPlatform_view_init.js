define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		var vm = new Vue({
			el : '#cmdbPlatform-' + mountId,
			data: {
				cmdbPlatform: {},
				cmdbChiefPlatform: {}
			}
		});

		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_platform&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbPlatform = rs.returndata.data[0];
			vm.cmdbPlatform = cmdbPlatform;
			cmdbPlatform.belongs_to_chief_id && initcmdbChiefPlatform({id:cmdbPlatform.belongs_to_chief_id}, vm);
		});

	}


	function initcmdbChiefPlatform(params, vm) {
	 cmdbUtils.getcmdbData({
	 url: '/mc?_tablename=cmdb_chief_platform&_action=get',
	 data: params
	 }).then(function(rs) {
	 var cmdbChiefPlatform = rs.returndata.data[0];
	 vm.cmdbChiefPlatform = cmdbChiefPlatform;

	     });
	 }
	

	return {
		init : init
	};
	
});