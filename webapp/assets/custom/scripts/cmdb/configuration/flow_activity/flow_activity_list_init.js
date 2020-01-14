define(['datatable_ajax_jsonp', 'plugins/vue/vue.min'], 
		function(DataTableAjaxJsonp, Vue) {
	
	function formatSeconds(value) { 
		var theTime = parseInt(value);// 秒 
		var theTime1 = 0;// 分 
		var theTime2 = 0;// 小时 
		// alert(theTime); 
		if(theTime > 60) { 
		theTime1 = parseInt(theTime/60); 
		theTime = parseInt(theTime%60); 
		// alert(theTime1+"-"+theTime); 
		if(theTime1 > 60) { 
		theTime2 = parseInt(theTime1/60); 
		theTime1 = parseInt(theTime1%60); 
		} 
		} 
		var result = ""+parseInt(theTime)+"秒"; 
		if(theTime1 > 0) { 
		result = ""+parseInt(theTime1)+"分"+result; 
		} 
		if(theTime2 > 0) { 
		result = ""+parseInt(theTime2)+"小时"+result; 
		} 
		return result; 
	} 
	
	
	var initDataTableAjax = function(params, vm){
		var p = {_flowname: params.processName};
		
		if (params._start && params._end) {
			p._start = params._start;
			p._end = params._end;
		}
		
		
		$.ajax({
			url: 'report?_action=taskstatistics',
			data: p,
			dataType: 'json',
			success: function(rs) {
				var data = rs.returndata.data;
				vm.items = data;
			}
		});
		
		
	};
	
	
	return {
		init : function(params) {
			var vm = new Vue({
				el: $('#' + params.uuid)[0],
				template: $('#' + params.uuid + '_tpl')[0],
				data: {items: []},
				methods: {
					formatSeconds: formatSeconds
				}
			});
			
			initDataTableAjax(params, vm);
			
			
			var search = $('#' + params.uuid + '_search');
			search.find('.filter-submit').on('click', function() {
				var start = search.find('[name=_start]').val();
				var end = search.find('[name=_end]').val();
				
				if ((!start && end) ||(start && !end)) {
					alert("请输入开始和结束时间!")
					return;
				}
				
				params._start = start;
				params._end = end;
				
				initDataTableAjax(params, vm);
			});
			
			search.find('.filter-cancel').on('click', function() {
				search.find('[name=_start]').val('');
				search.find('[name=_end]').val('');
				
				delete params._start;
				delete params._end;
				
				search.find('.filter-submit').click();
			});
		}
	};
	
	
});