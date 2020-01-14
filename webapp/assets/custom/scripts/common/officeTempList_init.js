define(['datatable_ajax'], 
		function(DataTableAjax) {

	var aoData = [{
		"sTitle" : "<input type=\"checkbox\" class=\"group-checkable\">",
		"mData" : "id",
		"bSortable" : false,
		"mRender" : function(data, type, full){
				return '<input type="checkbox" value="'+ full.id +'">';
		}
	}, {
		"sTitle" : "主题",
		"mData" : "subject",
		"bSortable" : false
	}, {
		"sTitle" : "类型",
		"mData" : "subModule",
		"bSortable" : false
	}, {
		"sTitle" : "格式",
		"mData" : "formats",
		"bSortable" : false
	}, {
		"sTitle" : "文件大小",
		"mData" : "filesize",
		"bSortable" : false,
		"mRender" : function(data,type,full){
			return (data/1024)+'kb';
			
		}
	}, {
		"sTitle" : "操作",
		"mData" : "id",
		"bSortable" : false,
		//"bVisible":false,
		"mRender" : function(data,type,full){
			var str ='<a class="btn btn-xs default ajaxify"  href="module/oas/template/toEditOfficeTemplate?formats=&id='+full.id+'" >编辑</a>';
			return str;
			
		}
	}];
	

	var add_wadeDept =function() {
		var $modal = $('#add_wadeDept');
		$('body').modalmanager('loading');
		$modal.modal();
	};

	var initEvent=function()
	{
		$('#add_butt').on('click', function(e){
					var formats=$("#add_formatss").val();
					Layout.ajaxToPage("module/oas/template/toAddOfficeTemplate?id=&formats="+formats);
		});
	}
	
	var tableObj;
	var getTableOjbj= function()
	{
		return tableObj;
	}
	var initDataTableAjax = function(){
		tableObj=DataTableAjax.init("#datatable_ajax", "module/oas/template/getOfficeTemplateDatatable", aoData);
	};
	
	return {
		init : function(){
			initDataTableAjax();//查询
			initEvent();//事件
		},
		getTableOjbj:getTableOjbj
	};
});

