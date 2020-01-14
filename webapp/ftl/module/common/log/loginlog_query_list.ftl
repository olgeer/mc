<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/global/plugins/data-tables/DT_bootstrap.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${base}/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" />

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-bullhorn"></i>日志列表
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-container">
					<table class="table table-striped table-bordered table-hover" id="loginlogQueryList">
					
						<thead>						
							<tr role="row" class="heading">
								<th width="15%">登录账号</th>
								<th width="8%">登录正常/异常</th>
								<th width="17%">异常描述</th>
								<th width="10%">登录角色</th>
								<th width="10%">登录IP</th>
								<th width="15%">登录时间</th>
								<th width="15%">退出时间</th>
								<th width="10%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input id="loloOwn" name="loloOwn" value="<#if own??><#if own=="admin">0<#else>1</#if></#if>" type="hidden"/>
									<input type="text" class="form-control form-filter input-sm" name="loloUsroName">
								</td>
								<td>
									<select name="loloType" class="form-control form-filter input-sm">
										<option value="">全部</option>
										<option value="0">正常</option>
										<option value="1">异常</option>
									</select>
								</td>
								<td></td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="loloRoleName">
								</td>
								<td></td>
								<td>
									<div class="input-group date datetime-picker" data-date-format="yyyy-MM-dd hh:mm" style="width:170px;">
										<input type="text" readonly class="form-control form-filter input-sm" name="loloLoginDateFrom" placeholder="From">
										<span class="input-group-btn">
											<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
									<div class="input-group date datetime-picker" style="width:170px;">
										<input type="text" class="form-control form-filter input-sm" readonly name="loloLoginDateTo" placeholder="To">
										<span class="input-group-btn">
											<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
								</td>
								<td>
									<div class="input-group date datetime-picker" data-date-format="yyyy-mm-dd hh:ii" style="width:170px;">
										<input type="text" class="form-control form-filter input-sm" readonly name="loloLogoutDateFrom" placeholder="From">
										<span class="input-group-btn">
										<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
									<div class="input-group date datetime-picker" data-date-format="yyyy-mm-dd hh:ii" style="width:170px;">
										<input type="text" class="form-control form-filter input-sm" readonly name="loloLogoutDateTo" placeholder="To">
										<span class="input-group-btn">
										<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
								</td>
								<td class="text-center">			
									<button class="btn btn-sm yellow filter-submit">
										<i class="fa fa-search"></i> 查询
									</button>
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
		
	</div>
</div>

<script src="${base}/assets/custom/scripts/common/loginlog_query_list_main.js"></script>
