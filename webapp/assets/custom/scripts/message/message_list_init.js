define(['datatable_ajax'],
    function (DataTableAjax) {

        var aoData = [
//            {
//                "sTitle": '编号',
//                "mData": "id",
//                "bSortable": false
//            },
            {
                "sTitle": "标题",
                "mData": "title",
                "mRender": function (data, type, full) {
                	if (!full.glob_detail_url) {
                		return data;
                	}
                    return '<a class="ajaxify" module_id="module_message_url_' + full.id + '" href="message/messct/toMsgUrl?id=' + full.id + '" data-html="true">' + data + '</a><a class="hidden"><span title="' + data + '"><span class="fa fa-pencil"></span>任务处理</span></a>';
                }
            }, {
                "sTitle": "详情",
                "mData": "detail",
                "mRender": function (data, type, full) {
                	data || (data = '');
                	var content = '<span title="' + data + '">' + (data.length > 30 ? data.substring(0, 30) + '...' : data) + '</span>';
                	if (!full.glob_detail_url) {
                		return content;
                	}
                    return '<a class="ajaxify" module_id="module_message_url_' + full.id + '" href="message/messct/toMsgUrl?id=' + full.id + '" data-html="true">' + content + '</a><a class="hidden"><span title="' + data + '"><span class="fa fa-pencil"></span>任务处理</span></a>';
                }
            }, {
                "sTitle": "发送用户",
                "mData": "send_user_name"
            }
            , {
                "sTitle": "发送时间",
                "mData": "send_time"
            }, {
                "sTitle": "状态",
                "mData": "sts",
                "mRender": function (data, type, full) {
                    if (data ==="1") {
                        return '<span class="label label-danger"> 未读 </span>';
                    }
                    else if (data ==="2")  {
                        return '<span class="label label-success"> 已读 </span>';
                    }else {
                        return '<span class="label label-success">无状态</span>';
                    }
                }
            }, {
                "sTitle": "操作",
                "mData": "id",
                "bSortable": false,
                "mRender": function (data, type, full) {
                    return  '<a module_id="module_message_view_' + data + '" href="message/messct/toView?id=' + data + '" class="btn btn-xs blue ajaxify">查看</a>';
                }
            }];
        var dataTable;
        var initDataTableAjax = function (params) {
            dataTable = DataTableAjax.initfnCallback("#" + params.uuid, "message/messct/messageListView"+"?sts=" + params.sts, aoData, function() {
            	if (params.sts == '1') {
	            	$('#' + params.uuid + ' td a').on('click', function() {
	            		setTimeout(function() {
	            			$('#' + params.uuid + ' .filter-submit').click();
	            			window.loadTopMsgData && loadTopMsgData();
	            		}, 1000);
	            	});
            	}
            });
        };

       
        return {
            init: function (params) {
            	initDataTableAjax(params);
            },
            getDataTableObject: function () {
                return dataTable;
            }
        };
    });