<#-- BEGIN PAGE LEVEL STYLES -->
<link href="${base}/assets/global/plugins/bootstrap-datepicker/css/datepicker.css" type="text/css" rel="stylesheet"/>
<link href="${base}/assets/global/plugins/data-tables/DT_bootstrap.css" type="text/css" rel="stylesheet" />
<#-- END PAGE LEVEL STYLES -->


<#-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
	<#-- Begin: life time stats -->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-shopping-cart"></i>消息列表
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover" id="${uuid}">
                        <thead>
                        <tr role="row" class="heading">
                            <!--<th width="3%"></th>-->
                            <th width="22%"></th>
                            <th width="40%"></th>
                            <th width="10%"></th>
                            <th width="15%"></th>
                            <th width="8%"></th>
                            <th width="5%"></th>
                        </tr>
                        <tr role="row" class="filter">
                            <!--<td></td>-->
                            <td>
                                <input type="text" class="form-control form-filter input-sm" name="title">
                            </td>
                            <td>
                                <input type="text" class="form-control form-filter input-sm" name="detail">
                            </td>
                            <td>
                                <input type="text" class="form-control form-filter input-sm" name="send_user_name">
                            </td>
                            <td>
                                <div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
									<input type="text" class="form-control form-filter input-sm" name="send_time" placeholder="发送时间">
									<span class="input-group-btn">
										<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
									</span>
								</div>
                            </td>
                            <td>
                                <select name="sts" class="form-control form-filter input-sm">
                                    <option value="">请选择...</option>
                                    <option value="1" <#if sts =="1">selected="selected"</#if>>未读</option>
                                    <option value="2" <#if sts =="2">selected="selected"</#if>>已读</option>
                                </select>
                            </td>
                            <td class="text-center">
                                <div class="margin-bottom-5">
                                    <button class="btn btn-sm yellow filter-submit <#if sts =="1">msg-submit-btn</#if>">
                                        <i class="fa fa-search"></i> 查询
                                    </button>
                                </div>
                                <button class="btn btn-sm red filter-cancel">
                                    <i class="fa fa-times"></i> 重置
                                </button>
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
	<#-- End: life time stats -->
    </div>
</div>
<#-- END PAGE CONTENT-->
<script>
	require(["jquery", "../custom/scripts/message/message_list_init", "bootstrap_datepicker"],
		function($, page) {
			$(document).ready(function() {
				Metronic.init();
				page.init({
					sts: '${sts}',
					uuid: '${uuid}'
				});
			});
		});
</script>