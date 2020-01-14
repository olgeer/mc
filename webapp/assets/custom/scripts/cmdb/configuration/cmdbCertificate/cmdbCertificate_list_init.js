define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "证书名",
			"mData" : "certificate_name"
		}, {
			"sTitle" : "到期时间",
			"mData" : "expire_date"
		}, {
			"sTitle" : "商务负责人",
			"mData" : "person_in_charge"
		},
		{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbCertificate_edit_' + data + '" href="configuration/cmdb_certificate/' + data + '?displayName=Http证书"><i class="fa fa-pencil"></i> 编辑</a>');
				///str += '<a class="btn btn-xs blue ajaxify" module_id="module_device_view_' + data + '" href="configuration/device/view?id=' + data + '"><i class="fa fa-eye"></i> 设备详情</a>';
				return str;
			}
		}];
		return aoData;
	}


	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#cmdbCertificate_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_certificate&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});