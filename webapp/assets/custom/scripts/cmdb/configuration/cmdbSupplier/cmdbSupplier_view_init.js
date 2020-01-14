define(['plugins/vue/vue.min', '../utils','plugins/moment/moment'], function(Vue, cmdbUtils, moment) {
	
	function init(params) {
		var mountId = params.id;
		var vm = new Vue({
			el : '#cmdbSupplier-view-content-'+mountId,
			data: {
				
				
				cmdbSupplier: {},
				
			}
		});
		
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_supplier&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbSupplier = rs.returndata.data[0];
			vm.cmdbSupplier = cmdbSupplier;
			

		});
	}
	
	
	return {
		init : init
	};
	
});