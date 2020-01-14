define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "名称",
			"mData" : "supplier_name"
		}, {
			"sTitle" : "地址",
			"mData" : "supplier_adress"
		},
		 {
				"sTitle" : "联系人",
				"mData" : "supplier_marketing_representative"
			}, {
			"sTitle" : "联系方式",
			"mData" : "supplier_contact_number"
		},
		 {
					"sTitle" : "邮箱",
					"mData" : "email"
				},{
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbSupplier_edit_' + data + '" href="configuration/cmdb_supplier/' + data + '?displayName=供应商"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdbSupplier_view_' + data + '" href="configuration/cmdbSupplier/view?id=' + data + '"><i class="fa fa-eye"></i> 详情</a>';
				return str;

				
			}
		}];
		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#cmdbSupplier_datatable_" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_supplier&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});