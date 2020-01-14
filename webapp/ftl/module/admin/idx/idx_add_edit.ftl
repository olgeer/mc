<!--START MODAL-->
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<#if mctype ?? >
			<h4 class="modal-title">编辑指标</h4>
		<#else>
			<h4 class="modal-title">添加指标</h4>
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
						<label class="col-md-3 control-label">指标名称</label>
                        <div class="col-md-9">
								<input type="text" name="iname"  id="iname" class="form-control" placeholder="指标名称">
                        </div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">指标标识
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text" name="imark"  id="imark" class="form-control" placeholder="指标标识">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">指标状态
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text"  name="status"  id="status" class="form-control" placeholder="状态、0:不可显示、1:可显示；">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">告警级别</label>
						<div class="col-md-9">
							<textarea class="form-control" name="ilevel" id="ilevel" rows="3"  placeholder="警告级别 123"></textarea>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn default">取消</button>
		<#if mctype ?? >
			<a id="submitDepartmentInfo" href="idx/saveIdx?id=${mctype.id}" class="btn green">
				<i class="fa fa-check"></i> 提交
			</a>
		<#else>
			<a id="submitDepartmentInfo" href="idx/saveIdx" class="btn green">
				<i class="fa fa-check"></i> 提交
			</a>
		</#if>
	</div>
<!--END MODAL-->

<script src="${base}/assets/custom/scripts/idx/idx_add_edit_main.js" type="text/javascript"></script>
<#if mctype ?? >
	<script type="text/javascript">
		<#--$('#gradeSelect option').each(function () {-->
	             <#--var gradId = $(this).val().split("%")[0]; //获取单个value-->
	             <#--if(gradId==${mctype.id}){-->
	             	<#--$(this).attr("selected", true); -->
	             <#--}-->
	         <#--});-->
         
         function setSelect(value){
//         	 $('#departmentForm .form-group select option').each(function () {
//	             var depa_id = $(this).val(); //获取单个value
//	             if(depa_id==value){
//	             	$(this).attr("selected", true);
//	             	if($(this).attr('class')==''){
//	             		return;
//	             	}else{
//	             		setSelect($(this).attr('class'));
//	             	}
//	             }
//        	 });
         }
         // 编辑时,设置选中对应的部门 
         <#--<#if mctype.id ?? >-->
	         <#--setSelect(${mctype.id});-->
         <#--</#if>-->
         <#--var arr = new Array();-->
		<#--$('#gradeSelect option').each(function () {-->
             <#--var val = $(this).val().split("%")[1]; //获取单个value-->
             <#--arr.push(val);-->
         <#--});-->
         <#--var val=$('#gradeSelect').children('option:selected').val();-->
			<#--var	gradeGradeNo=val.split("%")[1];-->
			<#--//校级才开始显示部门select-->
			<#--for (var i=1;i<arr.length;i++)-->
			<#--{-->
				<#--if(i<gradeGradeNo){-->
					<#--$('#gradeGradeNoDiv'+i).show();-->
				<#--}else{-->
					<#--$('#gradeGradeNoDiv'+i).hide();-->
				<#--}-->
			<#--}-->
		<#--$('#depaNo').val("${mctype.id}");-->
		<#--$('#depaName').val("${mctype.iname}");-->
			 <#if mctype.iname ?? >
				$('#iname').val("${mctype.iname}");
			 </#if>
			<#if mctype.imark ?? >
            $('#imark').val("${mctype.imark}");
			</#if>
			<#if mctype.status ?? >
            $('#status').val("${mctype.status}");
			</#if>
			<#if mctype.ilevel ?? >
            $('#ilevel').val("${mctype.ilevel}");
			</#if>
	</script>

</#if>

