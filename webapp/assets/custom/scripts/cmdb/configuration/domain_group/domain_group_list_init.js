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
			"mData" : "h.hostname"
		},{
			"sTitle" : "主机ip",
			"mData" : "group_concat(ai.ip)",
			"mRender" : function(data) {
				if (!data) return '';
				var datas = data.split(',');
				return toSet(data.split(',')).join('<br>');
			}
		}, {
			"sTitle" : "所属域组",
			"mData" : "group_concat(dg.group_name)",
			"mRender" : function(data) {
				if (!data) return '';
				return toSet(data.split(',')).join('<br>');
			}
		},{
			"sTitle" : "操作",
			"mData" : "dh.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				//params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_domain_group_with_host_edit_' + data + '" href="configuration/domain_group_with_host/' + data + '?displayName=域组主机关联"><i class="fa fa-pencil"></i> 编辑</a>');
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_domain_group_with_host_add" href="configuration/domain_group_with_host/add?displayName=域组主机关联&host_id='+ full.h.id +'"><i class="fa fa-pencil"></i> 添加域组</a>');
				//str += '<a class="btn btn-xs blue ajaxify" module_id="module_assignedip_view_' + data + '" href="configuration/assignedip/view?id=' + data + '"><i class="fa fa-eye"></i> IP详情</a>';
				return str;
			}
		}];

		return aoData;
	}
	
	
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=domain_group dg ,domain_group_with_host dh,host h,assigned_ip ai&_joincols=dg.id dh.domaingroup_id,h.id dh.host_id(-),h.id ai.belongs_to_host_id&_selectcol=h.id,h.hostname,group_concat(ai.ip) ,group_concat(dg.group_name),dh.id&_groupcol=h.id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
		}
	};
	
	
});