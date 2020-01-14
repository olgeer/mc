define(['datatable_ajax'], function(DataTableAjax) {

	var aoData = [
				{
				"sTitle" : '<input type="checkbox" class="group-checkable">',
				"mData" : "id",
				"bSortable" : false,
				"mRender" : function(data, type, full){
					return '<input type="checkbox" name="id" value="'+ data +'">';
				}},
				{
					"sTitle" : "ID",
					"mData" : "id"
				},{
					"sTitle" : "名称",
					"mData" : "iname"
				},{
					"sTitle" : "标识名",
					"mData" : "imark"
				},{
					"sTitle" : "状态",
					"mData" : "status"
				},{
					"sTitle" : "告警级别",
					"mData" : "ilevel"
				},{
					"sTitle" : "操作",
					"mData" : "id",
					"bSortable" : false,
					"mRender" : function(data, type, full){
							return '<a id="'+full.id+'" class="btn btn-xs blue toModel">编辑</a>';
					}
				}];
	var dataTable;
	var initDataTableAjax = function(){
		// dataTable=DataTableAjax.init("#adminDepartmentList", "admin/department/getDepartmentList", aoData);
		dataTable=DataTableAjax.init("#adminDepartmentList", "idx/getIdxList", aoData);
	};

	var departmentInfoModel =function(depa_id) {
		var $modal = $('#departmentInfo');
		$('body').modalmanager('loading');
		if(typeof(depa_id)=="undefined"){
			$modal.load("idx/toAddOrEidtIdx?"+Math.random(), '', function(){
				$modal.modal();
			});
		}else{
			
			$modal.load("idx/toAddOrEidtIdx?"+Math.random()+"&id="+depa_id, '', function(){
				$modal.modal();
			});
		
			
		}
    };
    
    var initEvent = function() {
    	
	  	//添加部门
    	$('#addDepartment').on('click', function(e){
    		departmentInfoModel();
    	});
    	//注册编辑事件
    	$("#adminDepartmentList").on("click", '.toModel', function(){
    		var id = $(this).attr('id');
	 		departmentInfoModel(id);
	 	});
	};
	
	
	return {
		init : function(){
			initDataTableAjax();
			initEvent();
		},
		getDataTableObject: function(){
			return  dataTable;
		}
	};
});