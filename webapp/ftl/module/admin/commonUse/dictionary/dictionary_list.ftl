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
					<i class="fa fa-server"></i>数据字典列表
				</div>
			</div>
			<div class="portlet-body">
				<span class="table-toolbar">
					<@security.authorize ifAnyGranted="Role_COMMON_MANAGER_DICTIONARY_EDIT">
						<span class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_dictionary_add" href="admin/commonUse/dictionary/add?displayName=数据字典">
							<i class="fa fa-plus-square-o"></i> 添加数据字典
							</a>
						</span>
					</@security.authorize>
				</span>
					<span class="table-toolbar">
					<@security.authorize ifAnyGranted="Role_COMMON_MANAGER_DICTIONARY_EDIT">
						<span class="btn-group">
							<a class="btn yellow ajaxify" module_id="module_dictionary_reload" href="${base}/reloadDictionary">
							<i class="fa fa-superpowers"></i> 重新加载数据字典
							</a>
						</span>
					</@security.authorize>
				</span>
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="dictionary_datatable_${uuid}">
						<thead>
							<tr role="row" class="heading">
							    <th width="10%"></th>
								<th width="15%"></th>
								<th width="16%"></th>
								<th width="23%"></th>
								<th ></th>
								<th width="16%">操作</th>
							</tr>
							<tr role="row" class="filter">
							<td>
									<input type="text" class="form-control form-filter input-sm" name="id">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="kind">
								</td>
							    <td>
									<input type="text" class="form-control form-filter input-sm" name="detial">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="value">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="code">
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
<!--START MODAL-->
<div id="historyViewModel" class="modal fade" tabindex="-1" data-keyboard="false" tabindex="-1" data-width="960" style="margin-top:-1000px">
</div>
<!--END MODAL-->


<#--BEGIN PAGE JAVASCRIPT -->
<script src="${base}/assets/custom/scripts/admin/commonUse/dictionary/dictionary_list_main.js"></script>
<#--END PAGE JAVASCRIPT-->

<script>
require(["jquery", "dictionary_list_init","jquery_validator_messages", "bootstrap_datepicker", "bootstrap_modal"],
		function($, dictionaryListInit) {
			$(document).ready(function() {
				Metronic.init();
				dictionaryListInit.init({
					uuid: '${uuid}',
					hasEdit: '<@security.authorize ifAnyGranted="Role_COMMON_MANAGER_DICTIONARY_EDIT">true</@security.authorize>'
				});
			});
		});
</script>