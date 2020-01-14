define(['datatable_ajax'], function(DataTableAjax) {

	var aoData = [
				{
				"sTitle" : '<input type="checkbox" class="group-checkable">',
				"mData" : "depa_id",
				"bSortable" : false,
				"mRender" : function(data, type, full){
					return '<input type="checkbox" name="depa_id" value="'+ data +'">';
				}},
				{
					"sTitle" : "部门名称",
					"mData" : "depa_name"
				},{
					"sTitle" : "部门编号",
					"mData" : "depa_no"
				},{
					"sTitle" : "级别",
					"mData" : "grad_grade_name"
				},{
					"sTitle" : "父部门名称",
					"mData" : "depa_parent_name"
				},{
					"sTitle" : "父部门编号",
					"mData" : "depa_parent_no"
				},{
					"sTitle" : "操作",
					"mData" : "depa_id",
					"bSortable" : false,
					"mRender" : function(data, type, full){
							return '<a depa_id="'+full.depa_id+'" class="btn btn-xs blue toModel">编辑</a>';
					}
				}];
	var dataTable;
	var initDataTableAjax = function(){
		dataTable=DataTableAjax.init("#adminDepartmentList", "admin/department/getDepartmentList", aoData);
	};

	var departmentInfoModel =function(depa_id) {
		var $modal = $('#departmentInfo');
		$('body').modalmanager('loading');
		if(typeof(depa_id)=="undefined"){
			$modal.load("admin/department/toAddOrEidtDepartment?"+Math.random(), '', function(){
				$modal.modal();
			});
		}else{
			
			$modal.load("admin/department/toAddOrEidtDepartment?"+Math.random()+"&depaId="+depa_id, '', function(){
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
    		var depa_id = $(this).attr('depa_id');
	 		departmentInfoModel(depa_id);
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