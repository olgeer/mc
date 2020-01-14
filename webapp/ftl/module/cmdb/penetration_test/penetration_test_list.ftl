
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
					<i class="fa fa-bullhorn"></i>安全测试申请列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="btn-group">
						<button id="new_dispatch" class="btn blue ajaxify" href="module/cmdb/penetrationTest/toAddOrEidtCmdbPenetrationTest">
							新建安全测试申请 <i class="fa fa-plus"></i>
						</button>
					</div>
				</div>
				<div class="table-container">
					<div class="table-actions-wrapper">
						<span> </span>
						<select class="table-group-action-input form-control input-inline input-sm">
							<option value="">选择事项...</option>
							<option value="submit">提交</option>
							<option value="delete">删除</option>
							<option value="rollback">回收</option>
						</select> 
						<a class="btn btn-sm yellow table-group-action-submit" data-target="#staticModal"> 
							<i class="fa fa-check"></i> 确定
						</a>
						<#-- static modal-->
						<div id="staticModal" class="modal fade confirm-submit" tabindex="-1" data-backdrop="static" data-keyboard="false">
							<div class="modal-body">
								<p>你确定执行此操作？</p>
							</div>
							<div class="modal-footer">
								<button type="button" data-dismiss="modal" class="btn blue sure-submit">确定</button>
								<button type="button" data-dismiss="modal" class="btn btn-default">返回</button>
							</div>
						</div>
					</div>
					<table class="table table-striped table-bordered table-hover" id="penetration_test_datatable">
						<thead>
							<tr role="row" class="heading">
								<th width="3%"></th>
								<th width="22%">标题</th>
								<th width="8%">状态</th>
								<th width="12%">提交时间</th>
								<th width="22%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td></td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="cpte_title">
								</td>
								<td>
									<select name="cpte_audit_status" class="form-control form-filter input-sm">
										<option value="">请选择...</option>
										<option value="0">待提交</option>
										<option value="1">待审核</option>
										<option value="2">已通过</option>
										<option value="3">被撤回</option>
									</select>
								</td>
								<td>
									<div class="input-group date date-picker margin-bottom-5" data-date-format="yyyy-mm-dd">
										<input type="text" class="form-control form-filter input-sm" readonly name="cpte_submit_time_from" placeholder="起始时间">
										<span class="input-group-btn">
										<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
									<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
										<input type="text" class="form-control form-filter input-sm" readonly name="cpte_submit_time_to" placeholder="终止时间">
										<span class="input-group-btn">
										<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
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
<div id="auditViewModel" class="modal fade" tabindex="-1" data-keyboard="false" tabindex="-1" data-width="760"   style="margin-top:-1000px">
</div>
<div id="revocationModal" class="revocationModal modal fade" tabindex="-1" data-keyboard="false">
    <div class="modal-body">
        <p>
           	 您是否确定要撤回此条记录<br>（下一位执行人未操作通过和整体流程未结束，方可撤销）
        </p>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn blue sure-submit-revocation">确定</button>
        <button type="button" data-dismiss="modal" class="btn btn-default">返回</button>
    </div>
</div>
<!--END MODAL-->

<#--BEGIN PAGE JAVASCRIPT -->
<script src="${base}/assets/custom/scripts/cmdb/penetration_test/penetration_test_list_main.js"></script>
<#--END PAGE JAVASCRIPT-->