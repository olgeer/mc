<div class="row">
	<div class="col-md-12">
		<form class="form-horizontal" id="configuration-form-${uuid}">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<#if id == "add">
							<i class="fa fa-plus-square-o"></i>添加${displayName}
						<#else> 
						  	<i class="fa fa-pencil"></i>编辑${displayName}
						</#if> 
					</div>
				</div>
				<div class="portlet-body form">
					<div class="form-horizontal">
						<div class="form-body">
							<div class="row">								
								<v-form-component :column="column" v-for="column in columns"></v-form-component>
							</div>
						</div>
						
						<div class="form-actions fluid">
							<div class="row text-center">
								<div class=" col-md-12">
									<button class="btn green" type="button" @click="submit" >
										<i class="fa fa-check"></i> 提交
									</button>
									<a class="btn default" @click="closeTab">
										<i class="fa fa-mail-reply"></i> 返回
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/globCapacity/globCapacity_edit_init'], function(view) {
		view.init({
			tableName: '${tableName}',
			uuid: '${uuid}',
			id: '${id}'
		});
	});
</script>
