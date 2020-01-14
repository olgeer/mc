define(['datatable_ajax'], function(DataTableAjax) {

	var aoData = [
				{
				"sTitle" : '<input type="checkbox" class="group-checkable">',
				"mData" : "grad_id",
				"bSortable" : false,
				"mRender" : function(data, type, full){
					return '<input type="checkbox" name="grad_id" value="'+ full.grad_id +'">';
				}},
				{
					"sTitle" : "級別名称",
					"mData" : "grad_grade_name"
				},{
					"sTitle" : "級別编号",
					"mData" : "grad_grade_no"
				},{
					"sTitle" : "级别類型",
					"mData" : "grad_type",
					"mRender" : function(data, type, full){
						if(data === 0) {
							return '<span class="label label-primary"> 部门管理 </span>';
						}
						else {
							return '<span class="label label-success"> 岗位管理 </span>';
						}
					}
				},{
					"sTitle" : "操作",
					"mData" : "grad_id",
					"bSortable" : false,
					"mRender" : function(data, type, full){
							return '<a grad_id="'+full.grad_id+'" class="btn btn-xs blue toModel">编辑</a>';
					}
				}];
	var dataTable;
	var initDataTableAjax = function(){
		dataTable=DataTableAjax.init("#adminGradeList", "admin/grade/getGradeList", aoData);
	};

	var gradeInfoModel =function(grad_id) {
		var $modal = $('#gradeInfo');
		$('body').modalmanager('loading');
		if(typeof(grad_id)=="undefined"){
			$modal.load("admin/grade/toAddOrEidtGrade?"+Math.random(), '', function(){
				$modal.modal();
			});
		}else{
			
			$modal.load("admin/grade/toAddOrEidtGrade?"+Math.random()+"&gradId="+grad_id, '', function(){
				$modal.modal();
			});
		
			
		}
    };
    
    var initEvent = function() {
    	
	  	//添加级别
    	$('#addGrade').on('click', function(e){
    		gradeInfoModel();
    	});
    	//注册编辑事件
    	$("#adminGradeList").on("click", '.toModel', function(){
    		var gradId = $(this).attr('grad_id');
	 		gradeInfoModel(gradId);
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