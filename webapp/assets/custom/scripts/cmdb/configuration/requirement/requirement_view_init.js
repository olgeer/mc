define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.req_id;
		
		var vm = new Vue({
			el : '#requirement-content-' + mountId,
			data: {
				requirement:{},
				details:[]
			}
		});
		
		initRequirement({
			req_id: params.req_id,
			_pagesize: 10,
			_sortcol:'create_time',
			_sortdir:'desc'
		}, vm);

		initDetail({
			req_id: params.req_id,
			//_pagesize: 10,
			_sortcol:'assign_time',
			_sortdir:'desc'
		}, vm);
	}
	
	
	function initRequirement(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/itil?_tablename=view_requirement_list&_action=get',
			data: params
		}).then(function(rs) {
			vm.requirement = cmdbUtils.fmData(rs.returndata.data[0]||{});
		});
	}

	function initDetail(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/itil?_tablename=view_requirement_detail&_action=get',
			data: params
		}).then(function(rs) {
			vm.details = cmdbUtils.fmData(rs.returndata.data||[]);
		});
	}

	return {
		init : init
	};
	
});