define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.company_id;
		
		var vm = new Vue({
			el : '#company-content-' + mountId,
			data: {
				company: {},
				records: [],
				services: [],
				requires: []
			}
		});
		
		initCompany({
			company_id: params.company_id,
			_pagesize: 10
		}, vm);

		initScore({
			company_id: params.company_id,
			_pagesize: 10,
			_sortcol:'create_time',
			_sortdir:'desc'
		}, vm);

		initRequire({
			company_id: params.company_id,
			_pagesize: 10,
			_sortcol:'create_time',
			_sortdir:'desc'
		}, vm);

		initService({
			ser_company_id: params.company_id,
			_pagesize: 10,
			_sortcol:'assign_time',
			_sortdir:'desc'
		}, vm);
	}
	
	
	function initCompany(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/itil?_tablename=view_company_list&_action=get',
			data: params
		}).then(function(rs) {
			vm.company = cmdbUtils.fmData(rs.returndata.data[0]||{});
		});
	}

	function initScore(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/itil?_tablename=t_scorerecord&_action=get',
			data: params
		}).then(function(rs) {
			vm.records = cmdbUtils.fmData(rs.returndata.data||[]);
		});
	}

	function initRequire(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/itil?_tablename=view_requirement_list&_action=get',
			data: params
		}).then(function(rs) {
			vm.requires = cmdbUtils.fmData(rs.returndata.data||[]);
		});
	}

	function initService(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/itil?_tablename=view_requirement_detail&_action=get',
			data: params
		}).then(function(rs) {
			vm.services = cmdbUtils.fmData(rs.returndata.data||[]);
		});
	}
	return {
		init : init
	};
	
});