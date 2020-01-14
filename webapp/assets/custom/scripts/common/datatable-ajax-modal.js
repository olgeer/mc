define(function() {

	var handleRecords = function(tableId, AjaxURL, aoColumns) {
		$(tableId).dataTable().fnDestroy();
		var grid = new Datatable();
		
		grid.init({
			src : $(tableId),
			onSuccess : function(grid) {
				// execute some code after table records loaded
			},
			onError : function(grid) {
				// execute some code on network or other general error
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
				 //"sDom" : "<'row'<'col-md-9 col-sm-12'pli><'col-md-3 col-sm-12'<'table-group-actions pull-right'>>r>t<'row'<'col-md-9 col-sm-12'pli><'col-md-3 col-sm-12'>r>>",
				"aLengthMenu" : [ [ 5, 10, 20, 50, 100/*-1*/ ],
						[ 5, 10, 20, 50, 100/*"All"*/ ]
				],
				"iDisplayLength" : 5, // default record count per page
				"bServerSide" : true, // server side processing
				"sAjaxSource" : AjaxURL, // ajax source
				// "aaSorting" : [ [ 1, "asc" ] ], //set first column as a
				// default sort by asc

				"aoColumns" : aoColumns
			}
		});
		return grid;
	};

	return {

		// main function to initiate the module
		init : function(tableId, AjaxURL, aoColumns) {
			return handleRecords(tableId, AjaxURL, aoColumns);
		}

	};

});