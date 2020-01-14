requirejs.config({
	baseUrl : "assets/global",
    waitSeconds: 0,
	paths : {
		"bootstrap_datepicker" : "plugins/bootstrap-datepicker/js/bootstrap-datepicker",
		"jquery_validate" : "plugins/jquery-validation/js/jquery.validate.min",
		"jquery_validator_messages" : "scripts/jquery.validator.messages",
		"select2": "plugins/select2/select2.min"
		//"moment": "plugins/moment/moment"
	},
	shim : {
		"jquery_validator_messages" : {
			deps : ["jquery_validate"]
		}
	}
});

define(['plugins/vue/vue.min',
        './utils',
        './v_form_component',
        'jquery_validator_messages',
        'select2',
        'bootstrap_datepicker'], function(Vue, cmdbUtils, vFormComponent) {

	
	function init(params) {
		params.extParams = JSON.parse($('<div/>').html(params.extParams).text());
		var mountId = '#configuration-form-' + params.uuid;

		cmdbUtils.getcmdbData({
			url: '/itil?_action=tableinfo&_tablename=' + params.tableName
		}).then(function(rs) {
			if (rs.returncode == '200') {
				var columns = rs.returndata.columns;
				var promise = syncRequireData(columns, params.id == 'add');
				if (params.id != 'add') {
					var key = rs.returndata.key;
					var data = {};
					data[key] = params.id
					cmdbUtils.getcmdbData({
						url: '/itil?_action=get&_pagesize=0&_tablename=' + params.tableName,
						data: data
					}).then(function(rsData) {
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


	function initForm(params, columns, mountId, data) {
		var isAdd = params.id == 'add';
		
		var setData = data || params.extParams;
		if (setData) {
			for (var i = 0; i < columns.length; i++) {
				var column = columns[i];
				column.value = setData[column.aliasName];
			}
		}
		var isSubmit = false;
		new Vue({
			el: mountId,
			data: {
				columns: columns,
				isAdd: isAdd,
				addMore: !!params._addMore
			},
			components: {
			    'v-form-component': vFormComponent
			},
			methods: {
				submit: function(isAddMore) {
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
					
					var data = form.serializeObjectQuery();
					var action = 'update';
					if (isAdd) {
						action = 'add';
					}
					if("cmdb_flow_config"==params.tableName){
						var userId=$("input[name='belong_to_user_id']").val();
						data=data.toString()+"&belong_to_user_id="+userId;

					}
					cmdbUtils.getcmdbData({
						url: '/itil?_action=' + action + '&_tablename=' + params.tableName,
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

							setTimeout(function() {
								isSubmit = false;
								isAddMore || Layout.closeTabAndBack();
		                   		params.extParams.fromId &&
		                   			$('#' + params.extParams.fromId + ' .filter-submit').trigger('reload');
		                   	}, 1500);

						} else {
							Metronic.alert({
		                		type: 'danger',
		                		icon: 'warning',
		                		message: msg + '失败',
		                		container: $(formId),
		                		place: 'prepend'
		                	});

							isSubmit = false;
						}
					}, function(resp) {
						Metronic.unblockUI(formId);
						Metronic.alert({
	                		type: 'danger',
	                		icon: 'warning',
	                		message: resp.status == 403 ? '未授权': '内部错误...',
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
			if (column.formcontrol != 'select' && column.formcontrol != 'select2') continue;
			
			if (column.dictionaryname && column.dictionaryname.indexOf(':') <= -1) {
				if (dCache[column.dictionaryname]) {
					column.codes = dCache[column.dictionaryname];
				} else {
					keys.push(column.dictionaryname);
					var dList = map[column.dictionaryname] || (map[column.dictionaryname] = [])
					dList.push(column);
				}
			} else if (column.dictionaryname) {
				(function(dictionaryname, column) {
					var splits = dictionaryname.split(':');
					var tableName = splits[0];
					var id = splits[1];
					var name = splits[2];
					
					selectCols = {};
					for (var i = 1; i < splits.length; i++) {
						var splitName = splits[i];
						if (splitName.indexOf('+')) {
							var splitNames = splitName.split('+');
							for (var sn in splitNames) {
								var selectCol = splitNames[sn].trim();
								if (selectCol.indexOf('\'') != -1) continue;
								selectCols[selectCol] = true;
							}
						} else {
							selectCols[splitName] = true;
						}
					}
					
					var selectColArr = [];
					for (var selectCol in selectCols) {
						selectColArr.push(selectCol);
					}
					
					if (column.format) {
						try {
							var returnData = JSON.parse(column.format).returnData || {};
							for (var k in returnData) {
								selectColArr.push(k);
							}
						} catch (ex) {
							console.log(ex);
						}
					}
					
					var prom = tableMap[dictionaryname] || (tableMap[dictionaryname] = cmdbUtils.getcmdbData({
						url: '/itil?_action=get&_pagesize=0&_tablename=' + tableName + '&_selectcol=' + selectColArr.join(','),
					}));
					
					prom.then(function(rs) {
						var list = rs.returndata.data;
						var map = {};
						var map2 = {};
						for (var i = 0; i < list.length; i++) {
							var o = list[i];
							var code = '', value = '';
							
							if (id.indexOf('+')) {
								var _ids = id.split('+');
								for (var j in _ids) {
									var _id = _ids[j];
									code += o[_id.trim()] || eval(_id.trim());
								}
							} else {
								code = o[id];
							}
							
							if (name.indexOf('+')) {
								var _names = name.split('+');
								for (var j in _names) {
									var _name = _names[j];
									value += o[_name.trim()] || eval(_name.trim());
								}
							} else {
								value = o[name];
							}
							
							map[code] = value;
							map2[code] = o;
						}
						column.codes = map;
						column.codeDatas = map2;
					});
					
					tableDictProm.push(prom);
				})(column.dictionaryname, column);
				
			}
		}
		
		if (keys.length > 0) {
			var prom = $.get('getDictionary?keys=' + keys.join(','));
			prom.then(function(rs) {
				for (var i in rs) {
					var dList = map[i];
					for (var j = 0; j < dList.length; j++) {
						dList[j].codes = rs[i];
					}
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
			//column.maynull || column.type == 'varchar' || (rule.required = true);
			column.maynull || (rule.required = true);
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
	
	
	$.fn.serializeObjectQuery = function () {
		var result = {};
		var extend = function (i, element) {
			var node = result[element.name]

			if ('undefined' !== typeof node && node !== null) {
				if ($.isArray(node)) {
					node.push(element.value);
				} else {
					result[element.name] = [node, element.value];
				}
			} else {
				result[element.name] = element.value;
			}
		};

		$.each(this.serializeArray(), extend);
		
		for (var i in result) {
			if ($.isArray(result[i])) {
				result[i] = result[i].join(',')
			}
		}
		return $.param(result);
	};
	
	
	return {
		init : init
	};
	
});