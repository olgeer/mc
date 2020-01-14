define(['plugins/vue/vue.min'], function(Vue) {
	
	function init() {
		$.ajax({
			url: 'assets/custom/scripts/cmdb/configuration/index_menu.json',
			dataType: "json",
			success: function(rs) {
				new Vue({
					el : '#cmdb-content',
					data: {
						deviceTypes: rs
					},
					filters: {
					    moduleId: function (value) {
					    	if (!value) return '';
					     	value = value.toString()
					     	return 'module_' + value.split('/')[1];
					    },
					    custUrl: function (device) {
					     	return device.url;
					    }
					   
					}
					
				});
			}
		});
	}

	return {
		init : init
	};
});