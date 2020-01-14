define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		var vm = new Vue({
			el : '#cmdbDeviceModel-' + mountId,
			data: {
				cmdbDeviceModel: {}

			}
		});
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_device_model&_action=get',
			data: params
		}).then(function(rs) {

			var cmdbDeviceModel = rs.returndata.data[0];
			vm.cmdbDeviceModel = cmdbDeviceModel;

		});

	}

	return {
		init : init
	};
	
});