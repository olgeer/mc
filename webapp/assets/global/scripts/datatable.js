/***
Wrapper/Helper Class for datagrid based on jQuery Datatable Plugin
***/

var Datatable = function () {

    var tableOptions;  // main options
    var dataTable; // datatable object
    var table;    // actual table jquery object
    var tableContainer;    // actual table container object
    var tableWrapper; // actual table wrapper jquery object
    var tableInitialized = false;
    var ajaxParams = {}; // set filter mode
    var aaData;
    
    //服务器传回的总记录数
    var iTotalRows;
    
    var countSelectedRecords = function() {
        var selected = $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        var text = tableOptions.dataTable.oLanguage.sGroupActions;
        if (selected > 0) {
            $('.table-group-actions > span', tableWrapper).text(text.replace("_TOTAL_", selected));
        } else {
            $('.table-group-actions > span', tableWrapper).text("");
        }
    }

    return {

        //main function to initiate the module
        init: function (options,callbackFun,extendData) {
            
            if (!$().dataTable) {
                return;
            }

            var the = this;

            // default settings
            options = $.extend(true, {
                src: "",  // actual table 
                filterApplyAction: "filter",
                filterCancelAction: "filter_cancel",
                resetGroupActionInputOnSuccess: true,
                dataTable: {
                    "sDom" : "<'row'<'col-md-7 col-sm-12'pli><'col-md-5 col-sm-12'<'table-group-actions pull-right'>>r><'table-responsive't><'row'<'col-md-7 col-sm-12'pli><'col-md-5 col-sm-12'>r>>", // datatable layout
                    
                    "aLengthMenu": [ // set available records per page
                        [10, 25, 50, 100, -1],
                        [10, 25, 50, 100, "All"]
                    ],
                    "iDisplayLength": 10, // default records per page
                    "oLanguage": {  // language settings
                        "sProcessing": '<img src="' + Metronic.getGlobalImgPath() + 'loading-spinner-grey.gif"/><span>&nbsp;&nbsp;加载中...</span>',
                        "sLengthMenu": "&nbsp;<span class='seperator'>|</span>&nbsp;每页显示  _MENU_ 条记录，",
                        "sInfo": "共有  _TOTAL_ 条记录",//
                        "sInfoEmpty": "数据为空",
                        "sGroupActions": "已选中 _TOTAL_ 条记录：",
                        "sAjaxRequestGeneralError": "请求失败， 请稍后再试...",
                        "sEmptyTable":  "没有数据",
                        "sZeroRecords": "没有相应记录",
                        "oPaginate": {
                            "sPrevious": "上一页",
                            "sNext": "下一页",
                            "sFirst": "首页",
                            "sLast": "尾页",
                            "sPage": "页码",
                            "sPageOf": "/"
                        }
                    },

                    "bAutoWidth": false,   // disable fixed width and enable fluid table
                    "bSortCellsTop": true, // make sortable only the first row in thead
                    "sPaginationType": "bootstrap_extended", // pagination type(bootstrap, bootstrap_full_number or bootstrap_extended)
                    "bProcessing": true, // enable/disable display message box on record load
                    "bServerSide": true, // enable/disable server side ajax loading
                    "sAjaxSource": "", // define ajax source URL 
                    "sServerMethod": "POST",
					"aoColumnDefs": [
				    {
				  		"mData": null,
				   		"sDefaultContent": "",	//设置数据如果为null,则显示""
						"sClass":"text-center", //列内容居中
				   		"aTargets": [ "_all" ]		//所有列配置生效
				   	}],
                    // handle ajax request
                    "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
                      oSettings.jqXHR = $.ajax( {
                        "dataType": 'json',
                        "type": "post",
                        "url": sSource,
                        "data": aoData,
                        "success": function(response, textStatus, jqXHR) {
                        	//取得服务器传回的总记录数，用于请求时传给服务器
                        	iTotalRows = response.iTotalRecords;
                        	if(iTotalRows){
                    			//把总记录数传给服务器
                    			ajaxParams["iTotalRows"] = iTotalRows;
                    		}
                        	
                        	aaData = response.aaData;
                        	
                    		if (response.sMessage) {
                                Metronic.alert({type: (response.sStatus == 'OK' ? 'success' : 'danger'), icon: (response.sStatus == 'OK' ? 'check' : 'warning'), message: response.sMessage, container: tableWrapper, place: 'prepend'});
                            } 
                            if (response.sStatus) {
                                if (tableOptions.resetGroupActionInputOnSuccess) {
                                    $('.table-group-action-input', tableWrapper).val("");
                                }
                            }
                            $('select.table-group-action-input').each(function(){
                            	$(this).val("");
        	                    $(this).selectpicker('render');
        	                });
                            if ($('.group-checkable', table).size() === 1) {
                                $('.group-checkable', table).attr("checked", false);
                                $.uniform.update($('.group-checkable', table));
                            }
                            if (tableOptions.onSuccess) {
                                tableOptions.onSuccess.call(undefined, the);
                            }
                            fnCallback(response, textStatus, jqXHR);
                            Metronic.stopPageLoading();
                        },
                        "error": function(XMLHttpRequest, textStatus, errorThrown){
                        	//add by qinguidong 20160615 如果session超时  会返回的内容返回，则这里需要重新处理。跳转到登录页面
                        	if (XMLHttpRequest.responseText.indexOf('window.parent.location.href')>0) {
                        		PageEvent.toLoginPage();
                        		return;
							}
                        	//end by qinguidong 20160615
                        	
                            if (tableOptions.onError) {
                                tableOptions.onError.call(undefined, the);
                            }
                            Metronic.alert({type: 'danger', icon: 'warning', message: tableOptions.dataTable.oLanguage.sAjaxRequestGeneralError, container: tableWrapper, place: 'prepend'});
                            $('.dataTables_processing', tableWrapper).remove();
                        }
                      } );
                    },

                    // pass additional parameter
                    "fnServerParams": function ( aoData ) {
                        //here can be added an external ajax request parameters.

                        $.each(ajaxParams, function( key, value ) {
                            aoData.push({"name" : key, "value": value});  
                        });
                        //add by qinguidong 添加查询参数  返回值：{type:'init',data:{key1:val1,key2:val2}}
                        if(typeof extendData == 'function'){
                        	var extendData = extendData();
                        	if(extendData.type==null||extendData.type=='init'){
                        		$.each(extendData.data, function( key, value ) {
                        			aoData.push({"name" : key, "value": value});  
                        		});
                        	}
                        }	
                        //end by qinguidong 添加查询参数
                    },
                   
                    "fnDrawCallback": function( oSettings ) { // run some code on table redraw
                        if (tableInitialized === false) { // check if table has been initialized
                            tableInitialized = true; // set table initialized
                            table.show(); // display table
                        }
                        Metronic.initUniform($('input[type="checkbox"]', table));  // reinitialize uniform checkboxes on each table reload
                        countSelectedRecords(); // reset selected records indicator
                        if(typeof callbackFun == 'function'){
                        	callbackFun();
                        }	
                    }
                }
            }, options);

            tableOptions = options;
                       
            // create table's jquery object
            table = $(options.src);
            tableContainer = table.parents(".table-container");

            // apply the special class that used to restyle the default datatable
            $.fn.dataTableExt.oStdClasses.sWrapper = $.fn.dataTableExt.oStdClasses.sWrapper + " dataTables_extended_wrapper";

            // initialize a datatable
            dataTable = table.dataTable(options.dataTable);
    
            tableWrapper = table.parents('.dataTables_wrapper');

            // modify table per page dropdown input by appliying some classes
            $('.dataTables_length select', tableWrapper).addClass("form-control input-xsmall input-sm");

            // build table group actions panel
            if ($('.table-actions-wrapper', tableContainer).size() === 1) {
            	
                $('.table-group-actions', tableWrapper).html($('.table-actions-wrapper', tableContainer).html()); // place the panel inside the wrapper
                $('.table-actions-wrapper', tableContainer).remove(); // remove the template container
                
                //在datatable右上角的select添加样式
                $('.table-group-action-input',tableWrapper).addClass("bs-select-datatable");
                
                //移除在datatable右上角的操作按钮样式
                $('.table-group-actions .table-group-action-submit').addClass("btn-sm");
            }
            
            //修改datatable分页栏样式
            //start
            $('.dataTables_length label select').addClass("bs-select-datatable");
	        $('.bs-select-datatable').selectpicker();
            $('.dataTables_length label .bootstrap-select button').css("padding","3px 13px");
            //$('.dataTables_length label .bootstrap-select button').css("margin-top","3px");
            $('.dataTables_length label .bootstrap-select button').css("margin-right","4px");
	        $('.table-group-actions div button').css("padding","3px 13px");
	        $('.table-group-actions div button').css("margin-top","3px");
	        $('.table-group-actions div button').css("margin-right","4px");
            //end
	        
	        //添加每页显示Select监听,实现datatable每页显示上下Select一致
	        $('.dataTables_length label select.bs-select-datatable').change(function(){
	        	$('.dataTables_length label select.bs-select-datatable').each(function(){
	                    $(this).selectpicker('render');
	              });
	        });
	        
            //修改datatable查询条件Select样式
            $('table thead tr select').selectpicker();
            $('table thead tr td .bootstrap-select button').css("padding","3px 13px");

	        
            // handle group checkboxes check/uncheck
            $('.group-checkable', table).change(function () {
                var set = $('tbody > tr > td:nth-child(1) input[type="checkbox"]', table);
                var checked = $(this).is(":checked");
                $(set).each(function () {
                    $(this).attr("checked", checked);
                });
                $.uniform.update(set);
                countSelectedRecords();
            });

            // handle row's checkbox click
            table.on('change', 'tbody > tr > td:nth-child(1) input[type="checkbox"]', function(){
                countSelectedRecords();
            });

            // handle filter submit button click
            table.on('click', '.filter-submit', function(e){
                e.preventDefault();

                the.setAjaxParam("sAction", tableOptions.filterApplyAction);

                // get all typeable inputs
                $('textarea.form-filter, select.form-filter, input.form-filter:not([type="radio"],[type="checkbox"])', table).each(function(){
                    the.addAjaxParam($(this).attr("name"), $(this).val());
                });

                // get all checkable inputs
                $('input.form-filter[type="checkbox"]:checked, input.form-filter[type="radio"]:checked', table).each(function(){
                    the.addAjaxParam($(this).attr("name"), $(this).val());
                    alert($(this).attr("name"));
                });
                the.setAjaxParam("iTotalRows", 0);
                dataTable.fnDraw();
            });
            
			//监听Datatable回车事件
			table.keydown(function(){
	          if(event.keyCode==13){
	          	$('.filter-submit',table).click();
	          }
		    });
		      
            // handle filter cancel button click
            table.on('click', '.filter-cancel', function(e){
                e.preventDefault();
                
                $('textarea.form-filter,select.form-filter, input.form-filter', table).each(function(){
                    $(this).val("");
                });
                //重置selectpicker
                $('select.form-filter', table).each(function(){
                    $(this).selectpicker('render');
                });
                $('input.form-filter[type="checkbox"]', table).each(function(){
                    $(this).attr("checked", false);
                });                 
                the.clearAjaxParams();      
                the.setAjaxParam("sAction", tableOptions.filterCancelAction);
                dataTable.fnDraw();
            });
        },

        getSelectedRowsCount: function() {
            return $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        },

        getSelectedRows: function() {
            var rows = [];
            $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).each(function(){
                rows.push({name: $(this).attr("name"), value: $(this).val()});
            });

            return rows;
        },
        
        getRowsContent: function() {
            return aaData;
        },
        
        getSelectedRowsContent: function() {
        	var selectRows = [];
            $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).each(function(){
            	selectRows.push({name: $(this).attr("name"), value: $(this).val()});
            });
			var selectRowsContent = [];
			var isError = false;
			for	(var i in aaData){
				for	(var j in selectRows){
					var name = selectRows[j]["name"];
					var id = selectRows[j]["value"];
					if(name==undefined || name == null){	
						isError = true;
						break;
					}
					if(id == aaData[i][name]){
						selectRowsContent.push(aaData[i]);
						break;
					}
				}
				if(isError){
					break;
				}
			}
			if(isError){
				alert("checkbox未定义name属性");
				//console.log("checkbox未定义name属性");
			}
			return selectRowsContent;
        },

        addAjaxParam: function(name, value) {
           ajaxParams[name] = value;
        },

        setAjaxParam: function(name, value) {
           ajaxParams[name] = value;
        },

        clearAjaxParams: function(name, value) {
           ajaxParams = {};
        },

        getDataTable: function() {
            return dataTable;
        },

        getTableWrapper: function() {
            return tableWrapper;
        }, 

        gettableContainer: function() {
            return tableContainer;
        }, 

        getTable: function() {
            return table;
        }        

    };

};