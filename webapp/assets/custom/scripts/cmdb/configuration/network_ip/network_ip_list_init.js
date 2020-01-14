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
			"sTitle" : "ip",
			"mData" : "ip",
			"bSortable" : false
		}, {
			"sTitle" : "平台",
			"mData" : "platform_name",
			"bSortable" : false
		}, {
			"sTitle" : "主机",
			"mData" : "host_name",
			"bSortable" : false,
			"mRender" : function(data,type,full) {
				if (data) {
					return '<a class="ajaxify" data-html="true" module_id="module_host_comprehensive_view_' + full.host_id + '" href="configuration/host_comprehensive/view?id=' + full.host_id + '">' + data + '</a><a class="hidden"><i class="fa fa-eye"></i> 详情</a>';
				}
				return data;
			}
		}, {
			"sTitle" : "备注",
			"mData" : "remarks",
			"bSortable" : false
		}, {
			"sTitle" : "状态",
			"mData" : "status",
			"mRender" : getCodeToValueFn(params, 'status'),
			"bSortable" : false
		}, {
			"sTitle" : "操作",
			"mData" : "id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_cmdb_network_ip_edit_' + data + '" href="configuration/cmdb_network_ip/' + data + '?displayName=ip"><i class="fa fa-pencil"></i> 编辑</a>');
				(params.hasEdit && isHostBtn(full)) && (str += '<a refresh class="btn btn-xs blue ajaxify" module_id="module_cmdb_bind_device_' + data + '" href="cmdb/toCmdbDeviceLogicInterfaceAddOREdit?ipId=' + data + '&ip=' + full.ip + '"><i class="fa fa-pencil"></i> 绑定设备</a>');
				(params.hasEdit && isLoadBtn(full)) && (str += '<a refresh class="btn btn-xs blue ajaxify" module_id="module_network_load_add" href="configuration/cmdb_network_load/add?displayName=负载均衡&load_ip=' + full.ip + '"><i class="fa fa-pencil"></i> 负载均衡</a>');
				(params.hasEdit && isNatBtn(full)) && (str += '<a refresh class="btn btn-xs blue ajaxify" module_id="module_network_nat_add" href="configuration/cmdb_network_nat/add?displayName=NAT&nat_before_ip=' + full.ip + '"><i class="fa fa-pencil"></i> nat</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_view_' + data + '" href="configuration/assignedip/view?id=' + data + '"><i class="fa fa-eye"></i> IP详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	function isHostBtn(data) {
		if (data.pool_area != 6 && data.pool_type != 24) {
			return true;
		}
		
		return false;
	}
	
	function isNatBtn(data) {
		if (data.pool_area == 6) {
			return true;
		}
		
		return false;
	}

	function isLoadBtn(data) {
		if (data.pool_type == 24) {
			return true
		}
		
		return false;
	}
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=view_cmdb_network_ip&_action=get", getAoData(params));
	};
	
	return {
		init : function(params) {
			initDataTableAjax(params);
			
			$('#' + params.uuid).closest('.portlet-body').find('.generate').one('click', function() {
				var poolId = params.poolId;
				$.ajax({
					url: 'configuration/ippool/generate',
					data: {poolId: poolId},
					success: function() {
						$('#' + params.uuid + ' .filter-submit').trigger('reload');
					}
				});
			});
			
		}
	};
	
	
});