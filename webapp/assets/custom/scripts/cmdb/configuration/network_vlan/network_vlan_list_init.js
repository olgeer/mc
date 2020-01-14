define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function getCodeToValueFn(params, name) {
		var map = {};
		$('#' + params.uuid + ' [name="' + name + '"] option').each(function() {
			$(this).attr('value') && (map[$(this).attr('value')] =  $(this).text());
		});
		
		return function(data) {
			return map[data] || data;
		}
	}
	
	function getAoData(params) {
		var aoData = [{
			"sTitle" : "vlan",
			"mData" : "vlan"
		},{
			"sTitle" : "关联IP池网络号",
			"mData" : "network_num"
		}, {
			"sTitle" : "状态",
			"mData" : "status",
			"mRender" : getCodeToValueFn(params, 'status')
		}, {
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_network_vlan_edit_' + data + '" href="configuration/cmdb_network_vlan/' + data + '?displayName=vlan"><i class="fa fa-pencil"></i> 编辑</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_view_' + data + '" href="configuration/assignedip/view?id=' + data + '"><i class="fa fa-eye"></i> IP详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=view_cmdb_network_vlan&_action=get", getAoData(params));
	};
	
	return {
		init : function(params) {
			initDataTableAjax(params);
			
			$('#' + params.uuid).closest('.portlet-body').find('.generate').one('click', function() {
				var poolId = params.poolId;
				$.ajax({
					url: 'configuration/vlanpool/generate',
					data: {poolId: poolId},
					success: function() {
						$('#' + params.uuid + ' .filter-submit').trigger('reload');
					}
				});
			});
			
		}
	};
	
	
});