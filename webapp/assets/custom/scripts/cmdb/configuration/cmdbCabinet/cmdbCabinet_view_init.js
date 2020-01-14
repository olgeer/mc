define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		var mountId = params.id;
		var vm = new Vue({
			el : '#cmdbCabinet-' + mountId,
			data: {
				chiefPlatform: {},
				platforms: []
			}
		});

		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_device_information&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbDeviceInformation = rs.returndata.data[0];
			vm.cmdbDeviceInformation = cmdbDeviceInformation;
			cmdbDeviceInformation.belong_to_device_model_id && initcmdbDeviceModel({id:cmdbDeviceInformation.belong_to_device_model_id}, vm);
			cmdbDeviceInformation.belong_to_supplier_id && initcmdbSupplier({id:cmdbDeviceInformation.belong_to_supplier_id}, vm);
			cmdbDeviceInformation.belong_to_logic_area_id && initcmdbLogicArea({id:cmdbDeviceInformation.belong_to_logic_area_id}, vm);
			cmdbDeviceInformation.belong_to_room_id && initcmdbRoom({id:cmdbDeviceInformation.belong_to_room_id}, vm);
		});

	}


	function initcmdbDeviceModel(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_device_model&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbDeviceModel = rs.returndata.data[0];
			vm.cmdbDeviceModel = cmdbDeviceModel;

		});
	}
	function initcmdbSupplier(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_supplier&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbSupplier = rs.returndata.data[0];
			vm.cmdbSupplier = cmdbSupplier;

		});
	}
	function initcmdbLogicArea(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_logic_area&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbLogicArea = rs.returndata.data[0];
			vm.cmdbLogicArea = cmdbLogicArea;

		});
	}
	function initcmdbRoom(params, vm) {
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