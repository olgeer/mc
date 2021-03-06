define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	
	function toSet(list) {
		var map = {};
		var rs = [];
		for (var i = 0; i < list.length; i++) {
			var o = list[i];
			if (!map[o]) {
				rs.push(o);
				map[o] = true;
			}
		}
		return rs;
	}
	
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "主机名",
			"mData" : "ch.host_name"
		},{
			"sTitle" : "IP",
			"mData" : "group_concat(cni.ip)",
			"mRender" : function(data) {
				if (!data) return '';
				var datas = data.split(',');
				return toSet(data.split(',')).join('<br>');
			}
		}, {
			"sTitle" : "所属域组",
			"mData" : "group_concat(cdg.group_name)",
			"mRender" : function(data) {
				if (!data) return '';
				return toSet(data.split(',')).join('<br>');
			}
		},{
			"sTitle" : "操作",
			"mData" : "cdgh.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				console.log(full.cdgh);
				var str = '';
				//params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_domain_group_with_host_edit_' + data + '" href="configuration/domain_group_with_host/' + data + '?displayName=域组主机关联"><i class="fa fa-pencil"></i> 编辑</a>');
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify"module_id="module_cmdb_domain_group_with_host_edit_' + data + '" href="configuration/cmdb_domain_group_with_host/' + data + '?displayName=域组主机关联">><i class="fa fa-pencil"></i> 编辑</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_view_' + data + '" href="configuration/assignedip/view?id=' + data + '"><i class="fa fa-eye"></i> IP详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=cmdb_domain_group_with_host cdgh,cmdb_domain_group cdg ,cmdb_host ch,cmdb_device_logic_interface cdli,cmdb_network_ip cni&_joincols=cdg.id(-) cdgh.domaingroup_id,ch.id(-) cdgh.host_id,ch.id cdli.belongs_device_id,cdli.ip_id cni.id &_selectcol=ch.id,ch.host_name,group_concat(cni.ip) ,group_concat(cdg.group_name),cdgh.id&_groupcol=ch.id &cdli.interface_device_type=0&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});