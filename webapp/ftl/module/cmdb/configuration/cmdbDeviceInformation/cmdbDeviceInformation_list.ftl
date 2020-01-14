<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"]/>
<#--BEGIN PAGE LEVEL STYLES-->
<link rel="stylesheet" href="${base}/assets/global/plugins/data-tables/DT_bootstrap.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${base}/assets/global/plugins/bootstrap-datepicker/css/datepicker.css" />
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
<#--END PAGE LEVEL STYLES-->

<#-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<#-- Begin: life time stats -->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-sitemap"></i>资产列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_MANAGEMENT_CMDBDEVICEINFORMATION_EDIT">
						<div class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_cmdbDeviceInformation_add" href="configuration/cmdb_room/add?displayName=机房">
							<i class="fa fa-plus-square-o"></i> 添加资产
							</a>
						</div>
					</@security.authorize>
				</div>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="cmdbDeviceInformation_datatable_${uuid}">
						<thead>
							<tr role="row" class="heading">
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="10%"></th>
								<th width="8%"></th>
								<th width="9%"></th>
								<th width="10%"></th>
								<th ></th>
								<th width="15%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
                                    <select class="form-control form-filter input-sm init-condition" name="cdi.device_type">
                                        <option value="" selected="selected"> 请选择 </option>
                                        <option value="0"> 服务器 </option>
                                        <option value="1" > 存储设备 </option>
                                        <option value="2"> 防火墙 </option>
                                        <option value="3"> 交换机 </option>
                                        <option value="4"> 路由器 </option>
                                        <option value="5"> 其他设备 </option>
                                        <option value="6"> 视频会议设备 </option>
                                        <option value="7"> 无线设备 </option>
                                        <option value="8"> 负载均衡 </option>
                                        <option value="9"> IP </option>
                                        <option value="10"> 安全设备 </option>
                                        <option value="11"> 板卡 </option>
                                        <option value="12"> 堡垒机 </option>
                                        <option value="13"> 第三方服务 </option>
                                        <option value="14"> 电源 </option>
                                        <option value="15"> 机箱 </option>
                                        <option value="16"> 软件 </option>
                                        <option value="17"> 许可证 </option>
                                        <option value="18"> 专线 </option>
                                        <option value="19"> 硬件 </option>
                                    </select>
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="device_name">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="device_model">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="device_sequence_number">
								</td>
                                <td>
                                  <input type="text" class="form-control form-filter input-sm" name="physics_area" >

                                </td>
                                <td>
                                  <input type="text" class="form-control form-filter input-sm" name="supplier" >

                                </td>
                                <td>
                                    <input type="text" class="form-control form-filter input-sm" name="manufacturer">
                                </td>
                                <td>
                                  <input type="text" class="form-control form-filter input-sm" name="outof_guarantee_deadline" >

                                </td>
								<td class="text-center">
									<div class="margin-bottom-5">							
										<button class="btn btn-sm yellow filter-submit">
											<i class="fa fa-search"></i> 查询
										</button>
										<button class="btn btn-sm red filter-cancel">
											<i class="fa fa-times"></i> 重置
										</button>
									</div>
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
<#-- END PAGE CONTENT -->

<#--BEGIN PAGE JAVASCRIPT -->
<script src="${base}/assets/custom/scripts/cmdb/configuration/cmdbDeviceInformation/cmdbDeviceInformation_list_main.js"></script>
<#--END PAGE JAVASCRIPT-->
<script type="text/javascript">
    require(["jquery", "cmdbDeviceInformation_list_init","jquery_validator_messages", "bootstrap_datepicker", "bootstrap_modal"],
            function($, cmdbDeviceInformationListInit) {
                $(document).ready(function() {
                    Metronic.init();
                    cmdbDeviceInformationListInit.init({
                        uuid: '${uuid}',
                        hasEdit: '<@security.authorize ifAnyGranted="ROLE_CONFIGURATION_MANAGEMENT_CMDBDEVICEINFORMATION_EDIT">true</@security.authorize>'
                    });
                });
            });
</script>
