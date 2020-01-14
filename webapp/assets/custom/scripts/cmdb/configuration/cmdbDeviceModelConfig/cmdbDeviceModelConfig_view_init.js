define(['plugins/vue/vue.min', '../utils','plugins/moment/moment'], function(Vue, cmdbUtils, moment) {
	
	function init(params) {
		var mountId = params.licence_id;
		var vm = new Vue({
			el : '#cmdbLicence-view-content-'+mountId,
			data: {
				cmdbLicence: {},
				deviceBrand: {},
				cmdbSupplier: {},
				globCapacity: null,
				globRemind: {},
				globRemObjList:{},
				userBasic:{}
			},
			 methods:{
				 redWarning:function(red,endDate){
						var redWarning= moment().add(red,'day');
						var result=	redWarning.isBefore(""+endDate+"");
						return result;
						
				 }
			 }
		});
		
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_licence&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbLicence = rs.returndata.data[0];
			vm.cmdbLicence = cmdbLicence;
			
			cmdbLicence.belong_to_device_brand_id && initBrand({id: cmdbLicence.belong_to_device_brand_id}, vm);
			cmdbLicence.belong_to_glob_cap_id && initCapacity({'gc.glob_cap_id': cmdbLicence.belong_to_glob_cap_id}, vm);
			cmdbLicence.belong_to_supplier_id && initSupplier({supplier_id: cmdbLicence.belong_to_supplier_id}, vm);
			cmdbLicence.licence_id && initGlobRemObjList({'grol.grol_obj_detail_id': cmdbLicence.licence_id}, vm);

		});
	}
	
	function initBrand(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=device_brand&_action=get',
			data: params
		}).then(function(rs) {
			var deviceBrand = rs.returndata.data[0];
			vm.deviceBrand = deviceBrand;
		});
	}
	
	
	function initSupplier(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_supplier&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbSupplier = rs.returndata.data[0];
			vm.cmdbSupplier = cmdbSupplier;
		});
	}

	function initCapacity(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=glob_capacity gc,user_basic ub&_joincols=gc.belong_to_usba_id ub.usba_id&gc.data_valid=1&_action=get',
			data: params
		}).then(function(rs) {
			var data = cmdbUtils.fmData(rs.returndata.data[0]);
			vm.globCapacity = data.gc;
			vm.userBasic = data.ub;
			
		});
	}
	function initGlobRemObjList(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=glob_rem_obj_list grol,glob_obj go,glob_remind gr&_joincols=grol.grol_obj_id go.glob_id ,grol.grol_rem_id gr.glre_id&go.glob_table_name=cmdb_licence&_action=get',
			data: params
		}).then(function(rs) {
			var data = cmdbUtils.fmData(rs.returndata.data[0]);
			vm.globRemObjList = data.grol;
			vm.globRemind = data.gr;
			//console.log(vm.globRemind);
			
		});
		
	}
	
	return {
		init : init
	};
	
});