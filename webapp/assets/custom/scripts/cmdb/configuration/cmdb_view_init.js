define(['plugins/vue/vue.min', './utils'], function(Vue, cmdbUtils) {
	
	function init(params) {
		
		var loadDatas = params.loadDatas;
		var eventBus = $('#el-' + params.uuid);
		
		var data = {};
		for (var i = 0; i < loadDatas.length; i++) {
			var loadData = loadDatas[i];
			
			var name = loadData.name;
			if (name.endsWith('s')) {
				data[name] = [];
			} else {
				data[name] = {};
			}
			
			(function(loadData) {
				var eventName = loadData.dept ? loadData.dept: 'init';
				
				eventBus.on(eventName, function(a, b) {
					var params = loadData.params;
					for (var i in params) {
						var pValue = b[params[i]];
						if (!pValue) {
							return;
						}
						params[i] = pValue;
					}
					
					var dataAjax = cmdbUtils.getcmdbData({
						url: loadData.url,
						data: params
					});
					
					var codes = loadData.codes;
					var dict = getDictionary(codes);
					
					$.when(dataAjax, dict).then(function(a, dicts) {
						var data = a[0].returndata.data;
						
						if (loadData.codes) {
							for (var j = 0; j < data.length; j++) {
								var o = data[j];
								for (var k in codes) {
									var kind = codes[k];
									o[k + '_value'] = dicts[kind][o[k]] || o[k];
								}
							}
						}
							
						var name = loadData.name;
						if (name.endsWith('s')) {
							vm[name] = data;
						} else {
							vm[name] = data[0];
							eventBus.trigger(name, [data[0]]);
						}
						
					});
					
				});
				
			})(loadData);

		}
				
		var vm = new Vue({
			el : '#el-' + params.uuid,
			data: data
		});
		
		eventBus.trigger('init', [{id: params.id}]);
		
	}
	
	
	var dCache = {};
	function getDictionary(codes) {
		codes || (codes = {});
		
		var result = new $.Deferred;
		
		var map = {};
		var kinds = [];
		var key = {};
		var codeValue = {};
		
		for (var code in codes) {
			var kind = codes[code];
			
			if (key[kind]) {
				continue;
			} else {
				key[kind] = true;
			}
			
			
			var cache = dCache[kind];
			if (cache) {
				codeValue[kind] = cache;
			} else {
				kinds.push(kind);
			}
		}
		
		
		if (kinds.length > 0) {
			var prom = $.get('getDictionary?keys=' + kinds.join(','));
			prom.then(function(rs) {
				for (var i in rs) {
					codeValue[i] = rs[i];
					dCache[i] = rs[i];
				}
				
				result.resolve(codeValue);
			});
		} else {
			result.resolve(codeValue);
		}
		
		return result;
	}
	
	return {
		init : init
	};
	
});