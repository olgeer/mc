define(['datatable_ajax_jsonp'], 
		function(DataTableAjaxJsonp) {
	function getAoData(params) {
		var aoData = [ {
			"sTitle" : "平台名称",
			"mData" : "platform.platform_name"
		}, {
			"sTitle" : "行方域名",
			"mData" : "dnsr.bank_domain"
		}
			, {
				"sTitle" : "主机记录",
				"mData" : "dnsr.sub_domain"
			}
			, {
				"sTitle" : "所属域名",
				"mData" : "dnsd.domain_name"
			}
			, {
				"sTitle" : "记录类型",
				"mData" : "dnsr.record_type"
			}
			, {
				"sTitle" : "线路类型",
				"mData" : "dnsr.record_line"
			}
			, {
				"sTitle" : "IP",
				"mData" : "dnsr.value"
			}, {
				"sTitle" : "TTL",
				"mData" : "dnsr.ttl"
			}, {
				"sTitle" : "解释状态",
				"mData" : "dnsr.record_status"
			}, {
				"sTitle" : "监控状态",
				"mData" : "monitor_status"
			},{
			"sTitle" : "操作",
			"mData" : "dnsr.id",
			"bSortable" : false,
			"mRender" : function(data,type,full){
				var str = '';
				params.hasEdit && (str += '<a class="btn btn-xs blue ajaxify" module_id="module_dnspodrecord_edit_' + data + '" href="configuration/dnspod_record/' + data + '?displayName=域名使用记录"><i class="fa fa-pencil"></i> 编辑</a>');
				str += '<a class="btn btn-xs blue" data-json=\'' + JSON.stringify(full) + '\'  id="module_dnspodrecord_synchronize_' + data + '" href="dnspod/adddnspod" ><i class="fa fa-check"></i> 同步 </a>';
				return str;
			}
		}];
		return aoData;


	}
	var dnspodrecordSynchronize = function(params){

				$('#module_dnspodrecord_synchronize_'+params.uuid).on('click', function(e) {

					$.ajax({
						type: "POST",
						url: "https://www.baidu.com",
						//data: $('#toUpdateMailAndPhone').serialize(),
						success: function (res) {
							console.log("测试++++++++");
						}
					})


				});


		}
	
	var tableObj;
	var initDataTableAjax = function(params){
		tableObj = DataTableAjaxJsonp.init("#" + params.uuid, cmdbHost + "/mc?_tablename=dnspod_record dnsr,platform platform,dnspod_domain dnsd&_joincols=dnsr.platform_id platform.id,dnsr.belongs_to_domain_id dnsd.id&_action=get", getAoData(params));
	};
	
	
	return {
		init : function(params) {
			initDataTableAjax(params);
			dnspodrecordSynchronize(params);
		}
	};
	
	
});