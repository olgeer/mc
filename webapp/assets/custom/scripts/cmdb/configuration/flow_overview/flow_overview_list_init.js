define(['datatable_ajax_jsonp', 'plugins/vue/vue.min'], 
		function(DataTableAjaxJsonp, Vue) {

	
	var initDataTableAjax = function(params, vm) {
		var p = {};
		
		p._start = params._start;
		p._end = params._end;
		
		$.ajax({
			url: 'report?_action=overview',
			data: p,
			dataType: 'json',
			success: function(rs) {
				var data = rs.returndata.data[0];
				vm.item = data;
			}
		});
		
	};
	
	
	return {
		init : function(params) {
			
			var vm = new Vue({
				el: $('#' + params.uuid)[0],
				template: $('#' + params.uuid + '_tpl')[0],
				data: {item: {}}
			});
			
			initDataTableAjax(params, vm);
			
			
			var search = $('#' + params.uuid + '_search');
			search.find('.filter-submit').on('click', function() {
				var start = search.find('[name=_start]').val();
				var end = search.find('[name=_end]').val();
				
				if (!start || !end) {
					//alert("请输入开始和结束时间!")
					//return;
				}
				
				params._start = start;
				params._end = end;
				
				initDataTableAjax(params, vm);
			});
		}
	};
	
	
});