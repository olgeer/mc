define(['datatable_ajax_view'], function(DatatableAjaxView) {
	var viewData = [
		{
			"sTitle" : "编号",
			"mData" : "id"
		},{
			"sTitle" : "任务名称",
			"mData" : "name"
		},{
			"sTitle" : "执行人",
			"mData" : "assignee"
		},{
			"sTitle" : "角色",
			"mData" : "assigneeRole"
		},{
			"sTitle" : "执行时间",
			"mData" : "endTime"
		},{
			"sTitle" : "处理结果",
			"mData" : "deleteReason"
		}
	];
	
	//fnDraw回调函数
/*	var isShow = false;
	var callback = function() {
		if(isShow) {
			
		}
		isShow = true;
	};*/
	
	//初始化dataTable
	var dataTableView = null;
	var initDatatableAjaxView = function() {
		dataTableView = DatatableAjaxView.init("#viewHistoryTable", "common/activiti/viewHistory", viewData/* ,callback*/);
	};
	
	//监听click事件，刷新DataTable，显示Modal
	var DataTableId = null;
	var initModal = function() {
		$(DataTableId).off("click", '.toViewHistory').on("click", '.toViewHistory', function(){
			var processInstanceId = $(this).attr('processinstance');
			if(dataTableView != null) {
				dataTableView.addAjaxParam("processInstanceId", processInstanceId);
				dataTableView.getDataTable().fnDraw();
				dataTableView.clearAjaxParams();
			}
			$("#historyViewGraph").attr("src", "common/activiti/graphHistoryProcessInstance/"+ processInstanceId +"?"+ Math.random());
			$("#viewHistory").modal();
		});
	};
	
	return {
		init : function(tableId){
			//isShow = false;
			DataTableId = tableId;
			initDatatableAjaxView();
			initModal();
		}
	};
});