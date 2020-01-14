define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.industry_id;
		
		var vm = new Vue({
			el : '#industry-content-' + mountId,
			data: {
				industry: []
			}
		});
		
		initIndustry({
			industry_id: params.industry_id,
			_pagesize: 10
		}, vm);

	}
	
	
	function initIndustry(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/itil?_tablename=view_industry_list&_action=get',
			data: params
		}).then(function(rs) {
			vm.industry = rs.returndata.data[0];
		});
	}

	return {
		init : init
	};
	
});