define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.user_id;
		
		var vm = new Vue({
			el : '#user-content-' + mountId,
			data: {
				user: {}
			}
		});
		
		initUser({
			user_id: params.user_id,
			_pagesize: 10
		}, vm);
	}
	
	
	function initUser(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/itil?_tablename=view_user_list&_action=get',
			data: params
		}).then(function(rs) {
			vm.user = cmdbUtils.fmData(rs.returndata.data[0]||{});
		});
	}

	
	return {
		init : init
	};
	
});