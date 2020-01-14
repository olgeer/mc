define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.record_id;
		
		var vm = new Vue({
			el : '#score-content-' + mountId,
			data: {
				record: []
			}
		});

		initScore({
			record_id: params.record_id,
			_pagesize: 10
		}, vm);

	}
	
	
	function initScore(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/itil?_tablename=view_scorerecord_list&_action=get',
			data: params
		}).then(function(rs) {
			vm.record = cmdbUtils.fmData(rs.returndata.data[0]||{});
		});
	}

	return {
		init : init
	};
	
});