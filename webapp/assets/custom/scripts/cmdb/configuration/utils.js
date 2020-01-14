define(['plugins/vue/vue.min'], function(Vue) {
	
	Vue.filter('concat', function(val, p1) {
        return p1 + val;
    });
	
	
	function getcmdbData(settings) {
		if (settings.url.indexOf('http') <= -1) {
			settings.url = cmdbHost + settings.url;
		}
		
		var defaultSetting = {
			type: 'post',
        	dataType: 'json',
        	//jsonp: '_callback'
	    };
		
		
		/**
         * 登陆认证参数
         */
		var params = settings.data || (settings.data = {});
		if  (typeof params == 'object') {
			params._username = window.global_usba_account || '';
	        params._token    = window.global_usba_token_id || '';
		} else {
			var tokenParams = {
				_username: window.global_usba_account || '',
                _token: window.global_usba_token_id || ''
			};
			settings.url = settings.url + '&' + $.param(tokenParams)
		}
        
		
		return $.ajax($.extend(defaultSetting, settings));
	}
	
	
	function fmData(data) {
		if ($.isArray(data)) {
			var rs = [];
			for (var i = 0; i < data.length; i++) {
				rs.push(_fmData(data[i]));
			}
			return rs;
		} else {
			return _fmData(data);
		}
	}
	
	function _fmData(data) {
		var o = {};
		for (var key in data) {
			var val = data[key];
			if (key.indexOf('.') != -1) {
				var keys = key.split('.');
				var cur = o;
				for (var j = 0; j < keys.length - 1; j++) {
					var prop = keys[j];
					cur[prop] || (cur[prop] = {});
					cur = cur[prop];
				}
				cur[keys[keys.length - 1]] = val;
			} else {
				o[key] = val;
			}
		}
		return o;
	}
	
	
	return {
		getcmdbData: getcmdbData,
		fmData: fmData
	};
	
})