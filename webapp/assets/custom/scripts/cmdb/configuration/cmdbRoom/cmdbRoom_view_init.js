define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;

		var vm = new Vue({
			el : '#cmdbRoom-content-' + mountId,
			data: {
				cmdbRoom: {}
			}
		});


		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_room&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbRoom = rs.returndata.data[0];
			vm.cmdbRoom = cmdbRoom;
		});
	}
	
	return {
		init : init
	};
	
});