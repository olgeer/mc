define(['plugins/vue/vue.min', '../utils','plugins/moment/moment'], function(Vue, cmdbUtils, moment) {
	
	function init(params) {
		var mountId = params.grol_obj_detail_id;
		var vm = new Vue({
			el : '#globRemObjList-content-'+mountId,
			data: {
				globRemind: {},
				globRemObjList:{},
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
			url: '/mc?_tablename=glob_rem_obj_list grol,glob_obj go,glob_remind gr&_joincols=grol.grol_obj_id go.glob_id ,grol.grol_rem_id gr.glre_id&go.glob_table_name=cmdb_licence&_action=get',
			data: params
		}).then(function(rs) {
			var data = cmdbUtils.fmData(rs.returndata.data[0]);
			vm.globRemObjList = data.grol;
			vm.globRemind = data.gr;
			vm.globRemObjList.grol_obj_detail_id && initCmdbLicence({licence_id: vm.globRemObjList.grol_obj_detail_id}, vm);

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