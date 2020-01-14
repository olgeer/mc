<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"]/>
<#--BEGIN PAGE LEVEL STYLES-->
<link rel="stylesheet" href="${base}/assets/global/plugins/data-tables/DT_bootstrap.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${base}/assets/global/plugins/bootstrap-datepicker/css/datepicker.css" />
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
<#--END PAGE LEVEL STYLES-->

<style>
	.preview-image-wrapper {
	    background: #f7f7f7;
	    padding: 16px;
	    display: inline-block;
	    text-align: center;
	    width: 100%;
	    position: relative;
    }
    
    .preview-image-box {
	    cursor: pointer;
	    max-width: 100%;
	    -webkit-transition: all .3s;
	    transition: all .3s;
	    background: #fff;
	    padding: 12px;
	    border-radius: 4px;
	    line-height: 80px;
	    text-align: left;
	}
	
	.preview-image-box:hover {
	    box-shadow: 1px 1px 6px rgba(0,0,0,.3);
	}
	
	.preview-image-box .prc-name {
		font-size: 16px;
		color: #999;
		vertical-align: middle;
	}
	
	.preview-image-box .prc-count {
		margin-left: 16px;
		font-size: 32px;
		font-weight: 700;
		color: #999;
		vertical-align: middle;
	}
	
	.preview-image-box > span {
		display: inline-block;
		width: 180px;
	}
</style>

<#-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<#-- Begin: life time stats -->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-archive"></i>流程总览
				</div>
			</div>
			<div class="portlet-body" style="padding: 0;">
				<div id="${uuid}_search" class="form-inline" style="padding: 15px;">
					<div class="form-group">
						<div class="input-group input-large date-picker input-daterange" data-date-format="yyyy-dd-mm">
							<input type="text" class="form-control" name="_start" placeholder="开始时间">
							<span class="input-group-addon"> 到 </span>
							<input type="text" class="form-control" name="_end" placeholder="结束时间">
						</div>
					</div>
					<button class="btn btn-sm yellow filter-submit"><i class="fa fa-search"></i> 查询 </button>
					<span style="color: #999;font-size: 12px;vertical-align: bottom;margin-left: 8px;">默认本周统计</span>
				</div>
				<div id="${uuid}">
					
				</div>
				<template id="${uuid}_tpl">
					<div>
						<div class="preview-image-wrapper">
							<div class="preview-image-box">
								<span>
									<span class="prc-name">发起流程</span>
									<span class="prc-count" style="color: #57c5f7;">{{item.total_start}}</span>
								</span>
								<span>
									<span class="prc-name">运行流程</span>
									<span class="prc-count" style="color: #9fd986;">{{item.total_running}}</span>
								</span>
								<span>
									<span class="prc-name">结束流程</span>
									<span class="prc-count" style="color: #ff7733;">{{item.total_end}}</span>
								</span>
							</div>
						<div>
					</div>
				</template>
			</div>
		</div>
		<#-- End: life time stats -->
	</div>
</div>
<#-- END PAGE CONTENT -->

<script>
	require(["jquery", "../custom/scripts/cmdb/configuration/flow_overview/flow_overview_list_init"], function($, page) {
		$(function() {
			Metronic.init();
			page.init({
				uuid: '${uuid}',
				hasEdit: 'true'
			});
		});
	});
</script>