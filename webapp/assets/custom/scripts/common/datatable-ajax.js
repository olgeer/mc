define(function() {
	var isInitEvent = false;
	
	var handleRecords = function(tableId, AjaxURL, aoColumns,fnRowCallback,callbackFun) {
		$(tableId).dataTable().fnDestroy();
		var grid = new Datatable();
		//数据加载完成之后的回调函数
		var rowCallback=fnRowCallback;
		grid.init({
			src : $(tableId),
			onSuccess : function(grid) {
				// execute some code after table records loaded
				// alert("table加载成功");
			},
			onError : function(grid) {
				// execute some code on network or other general error
				// alert("table加载失败");
			},
			dataTable : { // here you can define a typical datatable settings
				// from http://datatables.net/usage/options

				/*
				 * By default the ajax datatable's layout is horizontally
				 * scrollable and this can cause an issue of dropdown menu is
				 * used in the table rows which. Use below "sDom" value for the
				 * datatable layout if you want to have a dropdown menu for each
				 * row in the datatable. But this disables the horizontal
				 * scroll.
				 */
				// "sDom" : "<'row'<'col-md-8 col-sm-12'pli><'col-md-4
				// col-sm-12'<'table-group-actions
				// pull-right'>>r>t<'row'<'col-md-8 col-sm-12'pli><'col-md-4
				// col-sm-12'>r>>",
				"aLengthMenu" : [ [ 10, 20, 50, 100, 200/*-1*/ ],
						[ 10, 20, 50, 100, 200/*"All"*/ ]
				],
				"iDisplayLength" : 100, // default record count per page
				"bServerSide" : true, // server side processing
				"sAjaxSource" : AjaxURL, // ajax source
				// "aaSorting" : [ [ 1, "asc" ] ], //set first column as a
				// default sort by asc
				/*"sScrollX": "100%",
				"sScrollXInner": "110%",
                "bScrollCollapse": true,*/
				"aoColumns" : aoColumns,
				"fnRowCallback":function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
					if (rowCallback != null){  
						rowCallback( nRow, aData, iDisplayIndex, iDisplayIndexFull );
					}
				}
			}
		},callbackFun);
		
		// handle group actionsubmit button click
		grid.getTableWrapper().on(
				'click',
				'.table-group-action-submit',
				function(e) {
					e.preventDefault();
					var action = $(".table-group-action-input", grid.getTableWrapper());
					if (action.val() !== "" && grid.getSelectedRowsCount() > 0) {
						$(".confirm-submit", grid.getTableWrapper()).modal();
					} else if (action.val() === "") {
						Metronic.alert({
							type : 'danger',
							icon : 'warning',
							message : '请选择提交事项',
							container : grid.getTableWrapper(),
							place : 'prepend'
						});
					} else if (grid.getSelectedRowsCount() === 0) {
						Metronic.alert({
							type : 'danger',
							icon : 'warning',
							message : '没有选择记录',
							container : grid.getTableWrapper(),
							place : 'prepend'
						});
					}
				});

		$(".sure-submit", grid.getTableWrapper()).on('click', 
				function() {
					var action = $(".table-group-action-input", grid.getTableWrapper());
					grid.addAjaxParam("sAction", "group_action");
					grid.addAjaxParam("sGroupActionName", action.val());
					// 获取选中的id
					var records = grid.getSelectedRows();
					var vals = [];
					for ( var i in records) {
						vals.push(records[i]["value"]);
					}
					grid.addAjaxParam("idArarry", vals);
					grid.setAjaxParam("iTotalRows", 0);
					grid.getDataTable().fnDraw();
					grid.clearAjaxParams();//清空参数，避免影响下次请求
					$('textarea.form-filter, select.form-filter, input.form-filter', grid.getTable()).each(function(){
	                    $(this).val("");
	                });
	                //重置selectpicker
	                $('select.form-filter', grid.getTable()).each(function(){
	                    $(this).selectpicker('render');
	                });
				});

		return grid;
	};

	return {

		// main function to initiate the module
		init : function(tableId, AjaxURL, aoColumns) {
			return handleRecords(tableId, AjaxURL, aoColumns,null,null);
		},
		//fnRowCallback数据加载完成之后的回调函数
		initfnRowCallback : function(tableId, AjaxURL, aoColumns,fnRowCallback) {
			return handleRecords(tableId, AjaxURL, aoColumns,fnRowCallback,null);
		},
		//加载完成回调
		initfnCallback : function(tableId, AjaxURL, aoColumns,callbackFun) {
			return handleRecords(tableId, AjaxURL, aoColumns,null,callbackFun);
		}
		
	};

});