<!--START MODAL-->
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<#if grade ?? >
			<h4 class="modal-title">编辑级别</h4>
		<#else>
			<h4 class="modal-title">添加级别</h4>
		</#if>
	</div>
	<div class="modal-body">
		<div class="portlet-body form">
			<form action="javascript:void(0)" role="form" class="form-horizontal"  id="gradeForm">
					<div class="form-body">
					<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					填写信息不完整或有误，请重新检查！
					</div>
					<div class="alert alert-success display-hide">
						<button class="close" data-close="alert"></button>
						操作成功!
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">級別编号
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text" name="gradGradeNo"  id="gradGradeNo" class="form-control" placeholder="级别编号">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">级别名称
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text"  name="gradGradeName"  id="gradGradeName" class="form-control" placeholder="级别名称">
						</div>
					</div>
						<div class="col-md-9">
							<div class="form-group">
							<label class="control-label col-md-4">
										级别类型
							<span class="required">*</span>
							</label>
										<div class="col-md-8">
											<div class="input-icon right">
												<i class="fa"></i>
										<@dictSelect name="gradType" value=(grade.gradType)!'-1' type="gradeFieldType" showSelect=true />
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn default">取消</button>
		<#if grade ?? >
			<a id="submitGradeInfo" href="admin/grade/saveGrade?gradId=${grade.gradId}" class="btn green">
				<i class="fa fa-check"></i> 提交
			</a>
		<#else>
			<a id="submitGradeInfo" href="admin/grade/saveGrade" class="btn green">
				<i class="fa fa-check"></i> 提交
			</a>
		</#if>
	</div>
<!--END MODAL-->

<script src="${base}/assets/custom/scripts/admin/grade/grade_add_edit_main.js" type="text/javascript"></script>

<#if grade ?? >
	
	<script type="text/javascript">
	
		$('#gradGradeNo').val("${grade.gradGradeNo}");
		
		$('#gradGradeName').val("${grade.gradGradeName}");
		 
		
         
	</script>

</#if>

