define(['plugins/vue/vue.min',
        '../configuration/utils',
        '../configuration/v_form_component',
        'jquery_validator_messages',
        'select2'], function(Vue, cmdbUtils, vFormComponent) {

	function init(params) {
		var mountId = '#cmdb-' + params.uuid;
			
		var isSubmit = false;
		new Vue({
			el: mountId,
			data: {
				columns: params.columns
			},
			components: {
			    'v-form-component': vFormComponent
			},
			methods: {
				submit: function() {
					var form = $(mountId);
					if (!form.valid() || isSubmit) {
						return;
					}
					
					isSubmit = true;
					var formId = mountId;
					Metronic.blockUI({
		                target: formId,
		                boxed: true,
		                message: '请稍候...'
		            });
					
					var data = form.serialize();
					cmdbUtils.getcmdbData({
						url: '/cmdb/insertOrUpdateCmdbDeviceLogicInterface?1=1',
						data: data
					}).then(function(rs) {
						Metronic.unblockUI(formId);						
						var msg = '修改';
						
						Metronic.alert({
	                   		type: 'success',
	                   		icon: 'check',
	                   		message: msg + '成功',
	                   		container: $(formId),
	                   		place: 'prepend'
	                   	});

						setTimeout(function() {
							isSubmit = false;
							Layout.closeTabAndBack();
	                   		params.fromId &&
	                   			$('#' + params.fromId + ' .filter-submit').trigger('reload');
	                   	}, 1500);
							
						
					}, function() {
						Metronic.unblockUI(formId);
						Metronic.alert({
	                		type: 'danger',
	                		icon: 'warning',
	                		message: '内部错误...',
	                		container: $(formId),
	                		place: 'prepend'
	                	});
						isSubmit = false;
					});
				},
				
				
				submitAndAdd: function() {
					this.submit(true);
				},
				
				closeTab: function() {
					Layout.closeTabAndBack();
				}
			}
		});
		
	}
	
	
	return {
		init : init
	};
	
});