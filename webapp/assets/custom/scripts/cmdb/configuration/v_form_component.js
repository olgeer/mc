define(['plugins/moment/moment'], function(moment) {
	
	var vFormComponent = {
		props: ['column'],
		data: function() {
			return {
				cMap: {
					'int': vText,
					'varchar': vText,
					'date': vDate,
					'select': vSelect,
					'select2': vselect2,
					'search': vselect2Search,
					'generate': vGenerate,
					'textarea': vTextarea
				}
			};
		},
		components: {
			'v-form-component-map': {
				props: ['column', 'cMap', 'isAdd'],
				render: function(createElement) {
					var column = this.column;
					var cMap = this.cMap;
					var component = cMap[column.formcontrol] || cMap[column.type] || vText;
			    	return createElement(
			    		component,
			      		{
			        		props: {
			          			column: column,
			          			isAdd: this.isAdd
			        		}
			        	}
		        	);
			    }
			}
		},
		computed: {
			size: function() {
				var column = this.column;
				if (column.ui_sort_length) {
					return column.ui_sort_length == 1;
				}
				return column.type == 'varchar' && column.size >= 50;
			},
			isAdd: function() {
				return this.$parent.isAdd;
			},
			isHide: function() {
				if (this.column.in_use == 0) return true;
				return this.$parent.isAdd && this.column.autofill && !this.column.autoformula;
			},
			isShow: function() {
				if (!this.$parent.isAdd && this.column.key) return true;
				return this.$parent.isAdd && this.column.autofill && this.column.autoformula;
			},
			required: function() {
				return !this.column.maynull;
			}
		},
		template: '<div v-if="!isHide" v-show="!isShow" :class="{\'col-md-6\': !size, \'col-md-12\': size}">\
						<div class="form-group">\
							<label class="control-label" :class="{\'col-md-4\': !size, \'col-md-2\': size}">\
							 	{{column.showname || column.comment || column.aliasName}}\
								<span v-if="required" class="required" aria-required="true">*</span>\
							</label>\
							<div :class="{\'col-md-8\': !size, \'col-md-10\': size}">\
								<v-form-component-map :column="column" :cMap="cMap" :isAdd="isAdd"/>\
							</div>\
						</div>\
					</div>'
	}


	var vText = {
		props: ['column', 'isAdd'],
		template: '<input type="text" :name="column.aliasName" :value="value" :readonly="readonly" class="form-control">',
		computed: {
			readonly: function() {
				return !this.isAdd && this.column.autofill;
			},
			value: function() {
				var column = this.column;
				column.value = $.trim(column.value);
				if (!column.value && column.autoformula == 'cur_date' && column.format) {
					return moment(column.autoformulaVal).format(column.format);
				}
				if (column.value === 0) return column.value;
				return column.value || column.autoformulaVal || column.defaultvalue;
			}
		}
	}
	
	var vTextarea = {
			props: ['column', 'isAdd'],
			template: '<textarea :name="column.aliasName" :value="value" :rows="settings.rows" :readonly="readonly" class="form-control"></textarea>',
			computed: {
				readonly: function() {
					return !this.isAdd && this.column.autofill;
				},
				settings: function() {
					var settings = null;
					if (this.column.format) {
						try {
							settings = JSON.parse(this.column.format);
						} catch (ex) {
							console.log(ex);
						}
					}
					
					return settings || (settings = {rows: 10});
				},
				value: function() {
					var column = this.column;
					if (column.value === 0) return column.value;
					return column.value || column.autoformulaVal || column.defaultvalue;
				}
			}
		}
	
	
	var vGenerate = {
			props: ['column', 'isAdd'],
			template: '<div class="input-group">\
							<input type="text" :name="column.aliasName" :value="value" :readonly="readonly" class="form-control">\
							<span class="input-group-btn">\
								<button class="btn blue" style="border-width:0;" type="button" @click=generate>\
									<i class="fa fa-arrow-left fa-fw"></i> 生成</button>\
							</span>\
						</div>',
			computed: {
				readonly: function() {
					return !this.isAdd && this.column.autofill;
				},
				value: function() {
					var column = this.column;
					if (!column.value && column.autoformula == 'cur_date' && column.format) {
						return moment(column.autoformulaVal).format(column.format);
					}
					if (column.value === 0) return column.value;
					return column.value || column.autoformulaVal || column.defaultvalue;
				}
			},
			methods: {
				generate: function() {
					var data = $(this.$el).closest('form').serialize();
					var vm = this;
					var host_names="";
					//主机表
					var _company;
					var _room;
					var _resourcearea;
					var _os;
					var _apptype;
							_company=	$("select[name='belong_to_platform_id']").select2('data').text;
							_company=_company.toString().replace(/(^\s+)|(\s+$)/g,"");

						_os=$("select[name='belong_to_os_id']").select2('data').text;
						_os=_os.toString().replace(/(^\s+)|(\s+$)/g,"");
						_resourcearea=	$("select[name='belong_to_logic_area_id']").select2('data').text;
						_resourcearea=_resourcearea.toString().replace(/(^\s+)|(\s+$)/g,"");


						_room=	$("select[name='belong_to_room_id']").select2('data').text;
						_room=_room.toString().replace(/(^\s+)|(\s+$)/g,"");


						_apptype=	$("select[name='application_type']").select2('data').text;
						_apptype=_apptype.toString().replace(/(^\s+)|(\s+$)/g,"");

					//
					$.ajax({
						url: '/auto?_action=gethostname&_callback=?&_company=' + _company + '&_room=' + _room + '&_resourcearea=' + _resourcearea + '&_os=' + _os + '&_apptype=' + _apptype + '',
						dataType: "jsonp",
						jsonp: "callback",
						jsonpCallback: "success_jsonpCallback",
						success: function (rs) {
							host_names = rs.returndata.hostname;
							vm.column.value = host_names;
						}
					})
					//ajax请求


				}
			}
		}
	
	
	
	
	
	var vDate = {
		props: ['column'],
		template: '<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">\
						<input type="text" :name="column.aliasName" :value="column.value" class="form-control">\
						<span class="input-group-btn">\
							<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>\
						</span>\
					</div>',
		mounted: function () {
	   		$(this.$el).datepicker({
				rtl: Metronic.isRTL(),
				autoclose : true,
				format: 'yyyy-mm-dd',
				language: 'zh_CN'
			});
		}
		
	}
	
	
	var vSelect = {
		props: ['column'],
		template: '<select :name="column.aliasName" :value="value" class="form-control">\
						<option v-for="(value, key) in column.codes" :value="key">\
			    			{{value}}\
			  			</option>\
					</select>',
		computed: {
			value: function() {
				var column = this.column;
				if (column.value || column.value == 0) return column.value;
				if (column.defaultvalue || column.defaultvalue == 0) return column.defaultvalue;
				if (column.codes) {
					for (var i in column.codes) {
						return i;
					}
				}
				return null;
			}
		}
	}
	
	
	var vselect2 = {
		props: ['column'],
		template: '<select :name="column.aliasName" class="form-control select" :multiple="multiple">\
					<option v-if="!multiple" value=""> --请选择-- </option>\
					<option v-for="(value, key) in column.codes" :value="key">\
		    			{{value}}\
		  			</option>\
				</select>',
		computed: {
			value: function() {
				var column = this.column;
				if (column.value || column.value == 0) return column.value;
				if (column.codes) {
					return "";
					for (var i in column.codes) {
						return i;
					}
				}
				return null;
			},
			multiple: function() {
				if (!this.column.format) return false;
				try {
					return JSON.parse(this.column.format).multiple === true;
				} catch (ex) {
					console.log(ex);
				}
				return false;
			}
		},
		mounted: function () {
			var column = this.column;
			var vm = this;
			var el = $(this.$el);
			
			(this.value || this.value == 0) && el.val(new String(this.value).split(','));
			// init select2
			el.select2()
			// emit event on change.
			.on('change', function () {
				//vm.$emit('input', this.value);
				if (column.format) {
					try {
						var returnData = JSON.parse(column.format).returnData || {};
						var o = column.codeDatas[this.value] || {};
						var $form = el.closest('form');
						for (var k in returnData) {
							$form.find('[name="' + returnData[k] + '"]').val(o[k])
						}
					} catch (ex) {
						console.log(ex);
					}
				}
				
			});
		},
		watch: {
			
		},
		destroyed: function () {
			$(this.$el).off().select2('destroy')
		}
	}


	var vselect2Search = {
			props: ['column'],
			template: '<input :name="column.aliasName" class="form-control" :value="value" />',
			computed: {
				value: function() {
					var column = this.column;
					if (column.value || column.value == 0) return column.value;
					if (column.codes) {
						return "";
						for (var i in column.codes) {
							return i;
						}
					}
					return null;
				},
				multiple: function() {
					if (!this.column.format) return false;
					try {
						return JSON.parse(this.column.format).multiple || false;
					} catch (ex) {
						console.log(ex);
					}
					return false;
				}
			},
			mounted: function () {
				var vm = this;
				var el = $(this.$el);
				//this.value && el.val(new String(this.value).split(','));
				var column = this.column;
				var dictionaryname = column.dictionaryname;
				var splits = dictionaryname.split(':');
				var talbeName = splits[0];
				var idKey = splits[1];
				var valueKey = splits[2];
				
				var selectColArr = [idKey, valueKey];
				
				var display = valueKey;
				var pageSize = 15;
				
				
				var queryParams = {};
				var formParams = {};
				if (column.format) {
					try {
						queryParams = JSON.parse(column.format).queryParams || {};
						formParams = JSON.parse(column.format).formParams || {};
					} catch (ex) {
						console.log(ex);
					}
				}
				
				var form = el.closest('form');
				
				// init select2
				el.select2({
		            ajax: {
		                url: 'itil?_action=get&_tablename=' + talbeName + '&_selectcol=' + selectColArr.join(','),
		                dataType: 'json',
		                quietMillis: 500,
		                data: function (query, pageNum) {
		                	
		                	var params = {
		                            _pageindex: pageNum,
		                            _pagesize: pageSize
		                        };
		                	
		                	
		                	params[display] = query;
		                	
		                	$.extend(params, queryParams);
		                	
	                		for (var i in formParams) {
	                			var val = form.find('[name="' + formParams[i] + '"]').val();
	                			params[i] = val;
	                		}
	                		
		                    return params;
		                },
		                results: function (data, pageNum) {
		                	var datas = data.returndata.data;
		                	
		                	for (var i = 0; i < datas.length; i++) {
		                        var o = datas[i];
		                        o.id = o[idKey];
		                        o.text = o[display];
		                    }
		                	
		                	if (!vm.multiple && pageNum == 1) {
		                		datas.unshift({id: '', text: '--请选择--'});
		                	}
		                	
		                    return {
		                        results: datas,
		                        more: (pageNum * 15) < data.returndata.resultsetparameter.total,
		                    };
		                },
		                cache: true
		            },
		            initSelection: function(element, callback) {
		                if (vm.value) {
		                	var valStr = vm.value + '';
		                	var ids = valStr.split(',');
		                	
		                	var added = [];
		                	var promises = [];
		                	
		                	for (var k = 0; k < ids.length; k++) {
			                	
			                	(function(id) {
			                		var params = {};
				                	params[idKey] = id;
				                	
				                    var ajax = $.ajax({
						                url: 'itil?_action=get&_tablename=' + talbeName + '&_selectcol=' + selectColArr.join(','),
						                dataType: 'json',
						                data: params,
						                success: function(data) {
						                	var datas = data.returndata.data;
						                	for (var i = 0; i < datas.length; i++) {
						                        var o = datas[i];
						                        o.id = o[idKey];
						                        o.text = o[display];
						                        if (o.id == id) {
						                        	added.push(o);
						                        }
						                    }
						                }
						            });
				                    
				                    promises.push(ajax);
				                    
			                	})(ids[k]);
			                    
		                	}
		                	
		                	$.when.apply(null, promises).then(function() {
		                		if (added.length > 1) {
		                			callback(added);
		                		} else {
		                			callback(added[0]);
		                		}
		                	});
		                }
		            },
		            minimumInputLength: 0,
		            placeholder: vm.value ? null: '-- 请选择 --',
		            multiple: vm.multiple,
		            allowClear: true
		        })
				// emit event on change.
				.on('change', function () {
					//vm.$emit('input', this.value);
				});
			},
			watch: {
				
			},
			destroyed: function () {
				$(this.$el).off().select2('destroy')
			}
		}


	return vFormComponent;
	
});

