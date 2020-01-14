<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/global/plugins/data-tables/DT_bootstrap.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${base}/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" />

<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
		  	日志管理 <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li>
				<i class="fa fa-home"></i>
				<a  href="user/home/page" class="ajaxify">首页</a>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<a href="javascript:;">日志管理</a>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<a href="javascript:;">操作日志
				<#if own=="admin">(管理员)<#else>(普通用户)</#if>
				</a>
			</li>
		</ul>
	
	</div>
</div>

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
					<table class="table table-striped table-bordered table-hover" id="logQueryList">
					
						<thead>						
							<tr role="row" class="heading">
								<th width="15%">操作模块</th>
								<th width="15%">操作类型</th>
								<th width="20%">描述</th>
								<th width="15%">操作用户</th>
								<th width="20%">操作时间</th>
								<th width="15%">操作</th>
							</tr>
							<tr role="row" class="filter">
								<td>
									<input id="own" name="own" value="${own!}" type="hidden"/>
									<input type="text" class="form-control form-filter input-sm" name="syloModuleName">
								</td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="syloMethodType">
								</td>
								<td></td>
								<td>
									<input type="text" class="form-control form-filter input-sm" name="operationUsername">
								</td>
								<td>
									<div class="input-group date datetime-picker margin-bottom-5">
										<input type="text" class="form-control form-filter input-sm" readonly name="dateFrom" placeholder="From">
										<span class="input-group-btn">
										<button class="btn btn-sm default date-set" type="button"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
									<div class="input-group date datetime-picker">
										<input type="text" class="form-control form-filter input-sm" readonly name="dateTo" placeholder="To">
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

<script src="${base}/assets/custom/scripts/common/log_query_list_main.js"></script>
