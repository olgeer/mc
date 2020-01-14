<!--START MODAL-->
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		 <#if permission??>
			<h4 class="modal-title">编辑权限</h4>
		 <#else>
			<h4 class="modal-title">添加权限</h4>
		 </#if>
	</div>
	<div class="modal-body">
		<div class="portlet-body form">
			<form action="javascript:void(0)" role="form" class="form-horizontal"  id="permissionForm">
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
						<label class="col-md-3 control-label">权限名称
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text" name="permName" class="form-control" placeholder="权限名称" <#if permission??> value="${permission.permName}" </#if>>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">上级权限
						</label>
						<div class="col-md-9">
							<select class="form-control select2me" name="permParentId">
								<option value="" >请选择...</option>
								<#list trees as tree >
									<option value="${tree.id}" <#if permission??&&permission.permParentId??&&tree.id==permission.permParentId>selected</#if>>${tree.text}</option>
									<#if tree.children ??>
										<#list tree.children as tree1 >
											<option value="${tree1.id}" <#if permission??&&permission.permParentId??&&tree1.id==permission.permParentId>selected</#if> >&nbsp;&nbsp;&nbsp;${tree1.text}</option>
											<#if tree1.children ??>
												<#list tree1.children as tree2 >
													<option value="${tree2.id}" <#if permission??&&permission.permParentId??&&tree2.id==permission.permParentId>selected</#if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${tree2.text}</option>
													<#if tree2.children ??>
														<#list tree2.children as tree3 >
															<option value="${tree3.id}" <#if permission??&&permission.permParentId??&&tree3.id==permission.permParentId>selected</#if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${tree3.text}</option>
														</#list>
													</#if>
												</#list>
											</#if>
										</#list>
									</#if>
								</#list>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">权限类型</label>
						<div class="col-md-9">
							<select class="form-control select2me" name="permType">
								<option value="1" <#if permission??&&permission.permType==1> selected </#if> >模块</option>
								<option value="0" <#if permission??&&permission.permType==0> selected </#if>  >功能</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">权限URL
						</label>
						<div class="col-md-9">
							<input type="text"  name="permUrl"   class="form-control" placeholder="权限URL" <#if permission??> value="${permission.permUrl!}" </#if> >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">资源
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text"  name="permResource"   class="form-control" placeholder="资源" <#if permission??> value="${permission.permResource!}" </#if> >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">启用状态</label>
						<div class="col-md-9">
							<select class="form-control select2me" name="permUseStatus">
								<option value="1" <#if permission??&&permission.permUseStatus==1> selected </#if> >启用</option>
								<option value="0" <#if permission??&&permission.permUseStatus==0> selected </#if>  >关闭</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">图标样式
						</label>
						<div class="col-md-9">
							<input type="text"  name="permIcon"   class="form-control" placeholder="图标样式" <#if permission??> value="${permission.permIcon!}" </#if> >
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">权限排序
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text"  name="permSort"   class="form-control" placeholder="权限排序" <#if permission??> value="${permission.permSort!}" </#if>>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">权限描述</label>
						<div class="col-md-9">
							<textarea class="form-control" name="permDescribe" rows="3" ><#if permission??>${permission.permDescribe!}</#if></textarea>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn default">取消</button>
			<#if permission??>
				<a id="submitPermission" href="admin/permission/updatePermission?permId=${permission.permId}" class="btn green">
					<i class="fa fa-check"></i> 提交
				</a>
			<#else>
				<a id="submitPermission" href="admin/permission/savePermission" class="btn green">
					<i class="fa fa-check"></i> 提交
				</a>
			</#if>
			
	</div>
<!--END MODAL-->

<#--BEGIN PAGE JAVASCRIPT -->
<script>
    require(["jquery", "${base}/assets/custom/scripts/admin/permission/permission_add_edit_init.js"], function($, permissionAddEditInit) {
        $(function() {
            Metronic.init();
            permissionAddEditInit.init();
        });
    });
</script>
<#--END PAGE JAVASCRIPT-->


