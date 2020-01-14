define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {

	function getAoData(params) {
		var aoData = [ 
                  {
	            "sTitle" : "方法名描述",
	            "mData" : "syslogEx.method_description"
                 }, {
         			"sTitle" : "用户名",
        			"mData" : "syslog.sylo_usro_name"
        		},{
	            "sTitle" : "角色名",
	            "mData" : "syslog.sylo_role_name"
                 },
		    {
			"sTitle" : "创建时间",
			"mData" : "syslog.sylo_create_date"
		  },{
			"sTitle" : "操作",
			"mData" : "syslog.sylo_id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '<a class="btn btn-xs blue ajaxify" module_id="module_systemLog_view_' + data + '" href="admin/commonUse/systemLog/view?sylo_id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
				return str;
			}
		}];
		return aoData;
	}
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#systemlog_datatable_" + params.uuid, cmdbHost + "/itil?_tablename=system_log syslog,system_log_extend syslogEx&_joincols=syslog.sylo_method_name syslogEx.method_name &_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});