define(['plugins/vue/vue.min', '../../../cmdb/configuration/utils','plugins/moment/moment'], function(Vue, cmdbUtils,moment) {
	
	function init(params) {
		var mountId = params.sylo_id;
		var vm = new Vue({
			el : '#systemLog-systemLog-content-'+mountId,
			data: {
				systemLog: {},
				systemLogExtend:{}
			}
		});
		
		cmdbUtils.getcmdbData({
			url: '/itil?_tablename=system_log syslog,system_log_extend syslogEx&_joincols=syslog.sylo_method_name syslogEx.method_name&_action=get',
			data: params
		}).then(function(rs) {
			var data = cmdbUtils.fmData(rs.returndata.data[0]);
			vm.systemLog = data.syslog;
			vm.systemLogExtend = data.syslogEx;

		});
	}
	
	
	return {
		init : init
	};
	
});