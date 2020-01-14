<!--START MODAL-->
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<#if department ?? >
			<h4 class="modal-title">编辑部门</h4>
		<#else>
			<h4 class="modal-title">添加部门</h4>
		</#if>
	</div>
	<div class="modal-body">
		<div class="portlet-body form">
			<form action="javascript:void(0)" role="form" class="form-horizontal"  id="departmentForm">
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
						<label class="col-md-3 control-label">级别</label>
						<div class="col-md-9">
							<select class="form-control" id="gradeSelect" name="grade">
								<#list gradeList as grade  >
									<option value="${grade.gradId}%${grade.gradGradeNo}">${grade.gradGradeName}</option>
								</#list>
							</select>
						</div>
					</div>
					<#list gradeList as grade  >
						<!-- 过滤最顶级和最低级-->
						<#if grade_has_next >
							<div class="form-group"   id="gradeId${grade.gradId}" style= "display:none">
								<label class="col-md-3 control-label">${grade.gradGradeName}</label>
								<div class="col-md-9">
									<select class="form-control" id="gradeGradeNo${grade.gradGradeNo}" name="${grade.gradGradeNo}">
										<#list departmentMenuList as departmentMenu>
											<#if departmentMenu.grad_grade_no==grade.gradGradeNo>
												<#if grade_index!=0>
													<option value="${departmentMenu.depa_id}" class="${departmentMenu.parent_depa_id}">${departmentMenu.depa_name}</option>
												<#else>
													<option value="${departmentMenu.depa_id}" >${departmentMenu.depa_name}</option>
												</#if>
											</#if>
										</#list>
									</select>
								</div>
							</div>
						</#if>
					</#list>
					<div class="form-group">
						<label class="col-md-3 control-label">部门编号
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text" name="depaNo"  id="depaNo" class="form-control" placeholder="部门编号">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">部门名称
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text"  name="depaName"  id="depaName" class="form-control" placeholder="部门名称">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">部门介绍</label>
						<div class="col-md-9">
							<textarea class="form-control" name="depaDescription" id="depaDescription" rows="3" ></textarea>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn default">取消</button>
		<#if department ?? >
			<a id="submitDepartmentInfo" href="admin/department/saveDepartment?depa_id=${department.depaId}" class="btn green">
				<i class="fa fa-check"></i> 提交
			</a>
		<#else>
			<a id="submitDepartmentInfo" href="admin/department/saveDepartment" class="btn green">
				<i class="fa fa-check"></i> 提交
			</a>
		</#if>
	</div>
<!--END MODAL-->

<script src="${base}/assets/custom/scripts/admin/department/department_add_edit_main.js" type="text/javascript"></script>
<#if department ?? >
	<script type="text/javascript">
		$('#gradeSelect option').each(function () {
	             var gradId = $(this).val().split("%")[0]; //获取单个value
	             if(gradId==${department.gradId}){
	             	$(this).attr("selected", true); 
	             }
	         });
         
         function setSelect(value){
         	 $('#departmentForm .form-group select option').each(function () {
	             var depa_id = $(this).val(); //获取单个value
	             if(depa_id==value){
	             	$(this).attr("selected", true); 
	             	if($(this).attr('class')==''){
	             		return;
	             	}else{
	             		setSelect($(this).attr('class'));
	             	}
	             }
        	 });
         }
         // 编辑时,设置选中对应的部门 
         <#if department.depDepaId ?? >
	         setSelect(${department.depDepaId});
         </#if>
         var arr = new Array();
		$('#gradeSelect option').each(function () {
             var val = $(this).val().split("%")[1]; //获取单个value
             arr.push(val);
         });
         var val=$('#gradeSelect').children('option:selected').val();
			gradeId=val.split("%")[0];
			//校级才开始显示部门select
			for (var i=1;i<arr.length;i++)
			{
				if(i<gradeId){
					$('#gradeId'+i).show();
				}else{
					$('#gradeId'+i).hide();
				}
			}
		$('#depaNo').val("${department.depaNo}");
		$('#depaName').val("${department.depaName}");
		 <#if department.depaDescription ?? >
			$('#depaDescription').val("${department.depaDescription}");
         </#if>
	</script>

</#if>

