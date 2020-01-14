define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.business_id;
		
		var vm = new Vue({
			el : '#business-content-' + mountId,
			data: {
				business: []
			}
		});
		
		initBusiness({
			business_id: params.business_id,
			_pagesize: 10
		}, vm);

	}
	
	
	function initBusiness(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/itil?_tablename=view_business_list&_action=get',
			data: params
		}).then(function(rs) {
			vm.business = rs.returndata.data[0];
		});
	}

	return {
		init : init
	};
	
});