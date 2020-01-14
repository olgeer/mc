define(['plugins/vue/vue.min', '../utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		new Vue({
			el: $('#' + params.uuid)[0],
			template: '#template-' + params.uuid,
			data: {
				ip: '',
				rs: []
			},
			filters: {
				relevanceModuleId: function(data) {
					return 'module_' + data.type + '_view_' + data.id;
				},
				relevanceHref: function(data) {
					return 'configuration/' + data.type + '/view?id=' + data.id;
				}
			},
			methods: {
				submit: function () {
					var vm = this;
					$.when(
						cmdbUtils.getcmdbData({
							url: '/mc?_tablename=public_load_ip&_action=get',
							data: {ip: this.ip}
						}),
						cmdbUtils.getcmdbData({
							url: '/mc?_tablename=assigned_ip&_action=get',
							data: {ip: this.ip}
						}),
						cmdbUtils.getcmdbData({
							url: '/mc?_tablename=vip&_action=get',
							data: {ip: this.ip}
						})
					).then(function(rs1, rs2, rs3) {
						var rs = [];
						var list1 = rs1[0].returndata.data;
						for (var i = 0; i < list1.length; i++) {
							var o = list1[i];
							rs.push({id: o.id, ip: o.ip, type: 'public_load_ip', typeName: '公网'});
						}
						
						var list2 = rs2[0].returndata.data;
						for (var i = 0; i < list2.length; i++) {
							var o = list2[i];
							rs.push({id: o.id, ip: o.ip, type: 'assignedip', typeName: '物理'});
						}
						
						
						var list3 = rs3[0].returndata.data;
						for (var i = 0; i < list3.length; i++) {
							var o = list3[i];
							rs.push({id: o.id, ip: o.ip, type: 'vip', typeName: 'vip'});
						}
						vm.rs = rs;
					});
				}
			}
		});
	}
	
	
	return {
		init : init
	};
	
});