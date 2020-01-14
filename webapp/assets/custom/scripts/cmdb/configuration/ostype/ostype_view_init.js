define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		
		var vm = new Vue({
			el : '#ostype-content-' + mountId,
			data: {
				osType: {},
				hosts: []
			}
		});
		
		
		initOs(params, vm);
		initHost({
			belongs_to_ostype_id: params.id,
			_pagesize: 10
		}, vm);
	}
	
	
	function initOs(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=os_type&_action=get',
			data: params
		}).then(function(rs) {
			vm.osType = rs.returndata.data[0];
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