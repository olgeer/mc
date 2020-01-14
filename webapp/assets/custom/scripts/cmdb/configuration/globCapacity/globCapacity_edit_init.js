requirejs.config({
	baseUrl : "assets/global",
    waitSeconds: 0,
	paths : {
		"bootstrap_datepicker" : "plugins/bootstrap-datepicker/js/bootstrap-datepicker",
		"jquery_validate" : "plugins/jquery-validation/js/jquery.validate.min",
		"jquery_validator_messages" : "scripts/jquery.validator.messages",
		"select2": "plugins/select2/select2.min",
		"moment": "plugins/moment/moment"
	},
	shim : {
		"jquery_validator_messages" : {
			deps : ["jquery_validate"]
		}
	}
});

define(['plugins/vue/vue.min',
        '../utils',
        '../v_form_component',
        'jquery_validator_messages',
        'select2',
        'bootstrap_datepicker'], function(Vue, cmdbUtils, vFormComponent) {
	var globCapacity={};
	function init(params) {
		var mountId = '#configuration-form-' + params.uuid;
		cmdbUtils.getcmdbData({
			url: '/mc?_action=tableinfo&_tablename=' + params.tableName
		}).then(function(rs) {
			if (rs.returncode == '200') {
				var columns = rs.returndata.columns;
				var promise = syncRequireData(columns, params.id == 'add');
				if (params.id != 'add') {
					var key = rs.returndata.key;
					var data = {};
					data[key] = params.id
					cmdbUtils.getcmdbData({
						url: '/mc?_action=get&_pagesize=0&_tablename=' + params.tableName,
						data: data
					}).then(function(rsData) {
						globCapacity=rsData.returndata.data[0];
						promise.then(function() {
							initForm(params, columns, mountId, rsData.returndata.data[0]);
						});
					});
				} else {
					promise.then(function() {
						initForm(params, columns, mountId);
					});
				}
			}
		});
	
	}
	//编辑成功后需要保存原来的数据
   function editAndUpdate(msg){
	   
	   globCapacity['data_valid']=0;
	   delete globCapacity.glob_cap_id;
	   if(msg=='修改'){
			cmdbUtils.getcmdbData({
				url:'/mc?_action=add&_tablename=glob_capacity',
				 data:globCapacity
			})
	
	   }

    }
	function initForm(params, columns, mountId, data) {
		var isAdd = params.id == 'add';

		if (data) {
			for (var i = 0; i < columns.length; i++) {
				var column = columns[i];
				column.value = data[column.aliasName];
			}
		}
		
		new Vue({
			el: mountId,
			data: {
				columns: columns,
				isAdd: isAdd
			},
			components: {
			    'v-form-component': vFormComponent
			},
			methods: {
				submit: function() {
					var form = $(mountId);
					if (!form.valid()) {
						return;
					}
					
					var formId = mountId;
					Metronic.blockUI({
		                target: formId,
		                boxed: true,
		                message: '请稍候...'
		            });
					
					var data = form.serialize();
					var action = 'update';
					if (isAdd) {
						action = 'add';
					}
					
					cmdbUtils.getcmdbData({
						url: '/mc?_action=' + action + '&_tablename=' + params.tableName,
						data: data
					}).then(function(rs) {
						Metronic.unblockUI(formId);
						var msg = '修改';
						if (isAdd) {
							msg = '新增';
						}

						if (rs.returndata.result > 0) {
							Metronic.alert({
		                   		type: 'success',
		                   		icon: 'check',
		                   		message: msg + '成功',
		                   		container: $(formId),
		                   		place: 'prepend'
		                   	});
							editAndUpdate(msg);
		                   	isAdd && setTimeout(function() {
		                   		Layout.closeTab();
		                   	}, 2000);
						} else {
							Metronic.alert({
		                		type: 'danger',
		                		icon: 'warning',
		                		message: msg + '失败',
		                		container: $(formId),
		                		place: 'prepend'
		                	});
						}
					}, function() {
						Metronic.unblockUI(formId);
						Metronic.alert({
	                		type: 'danger',
	                		icon: 'warning',
	                		message: '内部错误...',
	                		container: $(formId),
	                		place: 'prepend'
	                	});
					});
				},
				
				closeTab: function() {
					Layout.closeTab();
				}
			}
		});
		
		addValidata(columns, mountId);
	}
	
	
	function syncRequireData(columns, isAdd) {
		var result = new $.Deferred;
		$.when(getDictionary(columns), 
				getCalcVals(columns)).then(function() {
			result.resolve();
		});
		return result;
	}
	
	
	var dCache = {};
	function getDictionary(columns) {
		var result = new $.Deferred;
		var map = {};
		keys = [];
		
		var tableDictProm = []; 
		var tableMap = {};
		for (var i = 0; i < columns.length; i++) {
			var column = columns[i];
			if (column.dictionaryname && column.dictionaryname.indexOf(':') <= -1) {
				if (dCache[column.dictionaryname]) {
					column.codes = dCache[column.dictionaryname];
				} else {
					keys.push(column.dictionaryname);
					map[column.dictionaryname] = column;
				}
			} else if (column.dictionaryname) {
				(function(dictionaryname, column) {
					var splits = dictionaryname.split(':');
					var tableName = splits[0];
					var id = splits[1];
					var name = splits[2];
					
					var prom = tableMap[dictionaryname] || (tableMap[dictionaryname] = cmdbUtils.getcmdbData({
						url: '/mc?_action=get&_pagesize=0&_tablename=' + tableName + '&_selectcol=' + id + ',' + name,
					}));
					
					prom.then(function(rs) {
						var list = rs.returndata.data;
						var map = {};
						for (var i = 0; i < list.length; i++) {
							var o = list[i];
							map[o[id]] = o[name]
						}
						column.codes = map;
					});
					
					tableDictProm.push(prom);
				})(column.dictionaryname, column);
				
			}
		}
		
		if (keys.length > 0) {
			var prom = $.get('getDictionary?keys=' + keys.join(','));
			prom.then(function(rs) {
				for (var i in rs) {
					map[i].codes = rs[i];
					dCache[i] = rs[i];
				}
			});
			tableDictProm.push(prom);
		}
		
		if (tableDictProm.length <= 0) {
			result.resolve();
		} else {
			$.when.apply(null, tableDictProm).then(function() {
				result.resolve();
			});
		}
		
		return result;
	}
	
	
	function getCalcVals(columns) {
		var map = {};
		var keys = [];
		for (var i = 0; i < columns.length; i++) {
			var column = columns[i];
			if (column.autoformula) {
				map[column.autoformula] || (keys.push(column.autoformula));
				var list = map[column.autoformula] || (map[column.autoformula] = []);
				list.push(column);
			}
		}
		
		var result = new $.Deferred;
		if (keys.length > 0) {
			$.get('getCalcVals?keys=' + keys.join(',')).then(function(rs) {
				for (var i in rs) {
					var val = rs[i];
					var list = map[i];
					for (var j = 0; j < list.length; j++) {
						list[j].autoformulaVal = val;
					}
				}
				result.resolve();
			});
		} else {
			result.resolve();
		}
		
		return result;
	}

	
	function addValidata(columns, mountId) {
		var form = $(mountId);
		
		var rules = {};
		for (var i = 0; i < columns.length; i++) {
			var column = columns[i];
			var rule = {};
			column.mayNull || (rule.required = true);
			(column.type == 'varchar') && (rule.maxlength = column.size);
			rules[column.aliasName] = rule;
		}
		
		// console.log(rules);
		form.validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: rules,
            messages: {},
            
			invalidHandler: function(event, validator) {
				var mg = '填写信息不完整或有误，请重新检查！';
				for (var obj in validator.errorMap) {
					mg=validator.errorMap[obj];
					break;
				}
				Metronic.alert({
					type: 'danger', 
					icon: 'warning', 
					message: mg, 
					container: form, 
					place: 'prepend'
				});
			},
			
			errorPlacement: function(error, element) {
				var icon = $(element).parent(".input-icon").children("i");
				icon.removeClass("fa-check").addClass("fa-warning");
				icon.attr("data-original-title", error.text()).tooltip({container: "body"});
			},
			
			highlight: function(element) {
				$(element).closest(".form-group")
				.removeClass("has-success")
				.addClass("has-error");
			},
			
			success: function(label, element) {
				var icon = $(element).parent(".input-icon").children("i");
				$(element).closest(".form-group").removeClass("has-error").addClass("has-success");
				icon.removeClass("fa-warning").addClass("fa-check");
			}
		});
	}
	
	
	return {
		init : init
	};
	
});