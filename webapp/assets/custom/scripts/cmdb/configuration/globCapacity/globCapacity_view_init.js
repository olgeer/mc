define(['plugins/vue/vue.min', '../utils','plugins/moment/moment'], function(Vue, cmdbUtils, moment) {
	
	function init(params) {
		var mountId = params.belong_to_detail_object_id;
		var vm = new Vue({
			el : '#globCapacity-main-content-'+mountId,
			data: {
				globCapacity: {},
				userBasic:{},
				cmdbLicence:{}
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
			url: '/mc?_tablename=glob_capacity&data_valid=1&_action=get',
			data: params
		}).then(function(rs) {
			var globCapacity = rs.returndata.data[0];
			vm.globCapacity = globCapacity;
			globCapacity.belong_to_usba_id && initUserBasic({usba_id: globCapacity.belong_to_usba_id}, vm);
			globCapacity.belong_to_detail_object_id && initCmdbLicence({licence_id: globCapacity.belong_to_detail_object_id}, vm);

		});
	}
	
	function initUserBasic(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=user_basic&_action=get',
			data: params
		}).then(function(rs) {
			var userBasic = rs.returndata.data[0];
			vm.userBasic = userBasic;
		});
	}
	
	function initCmdbLicence(params, vm) {
		cmdbUtils.getcmdbData({
			url: '/mc?_tablename=cmdb_licence&_action=get',
			data: params
		}).then(function(rs) {
			var cmdbLicence = rs.returndata.data[0];
			vm.cmdbLicence = cmdbLicence;
		});
	}
	
	return {
		init : init
	};
	
});