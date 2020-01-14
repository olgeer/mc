define(function() {

	var handleRecords = function(tableId, AjaxURL, aoColumns, callback,callbackFun) {
		$(tableId).dataTable().fnDestroy();
		var grid = new Datatable();

		grid.init({
			src : $(tableId),
			onSuccess : callback,
				//callback();
				// execute some code after table records loaded
				// alert("table加载成功");
			
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
				 //"sDom" : "<'row'<'col-md-4 col-sm-12'>r>t<'row'<'col-md-4 col-sm-12'>r>>",
				"sDom" : "<'row'<'col-md-5 col-sm-12'>r><'table-responsive't><'row'<'col-md-5 col-sm-12'>r>>", // datatable layout
				"aLengthMenu" : [ [ 10, 20, 50, 100, 200/*-1*/ ],
						[ 10, 20, 50, 100, 200/*"All"*/ ]
				],
				"iDisplayLength" : 1000, // default record count per page
				"bServerSide" : true, // server side processing
				"sAjaxSource" : AjaxURL, // ajax source
				// "aaSorting" : [ [ 1, "asc" ] ], //set first column as a
				// default sort by asc

				"aoColumns" : aoColumns,
				"fnDrawCallback": function ( oSettings ) {
					/* Need to redo the counters if filtered or sorted */
					if ( oSettings.bSorted || oSettings.bFiltered )
					{
						for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )
						{
							$('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+1 );
						}
					}
					if(typeof callbackFun == 'function'){
                    	callbackFun();
                    }	
				}
			}
		});
		return grid;
	};
	
	return {

		// main function to initiate the module
		init : function(tableId, AjaxURL, aoColumns, callback) {
			return handleRecords(tableId, AjaxURL, aoColumns, callback);
		},
		//加载完成回调
		initfnCallback : function(tableId, AjaxURL, aoColumns,callbackFun) {
			return handleRecords(tableId, AjaxURL, aoColumns,null,callbackFun);
		}

	};

});