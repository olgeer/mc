<style>[v-if] {display: none}</style>
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
									<button v-if="true" class="btn btn-sm green" type="button" @click="submit(false)">
										<i class="fa fa-check"></i> 提交
									</button>
									<button v-if="addMore" class="btn btn-sm green" type="button" @click="submitAndAdd">
										<i class="fa fa-check"></i> 提交再添加
									</button>
									<a v-if="true" class="btn btn-sm default" @click="closeTab">
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
	require(['../custom/scripts/cmdb/configuration/edit_init'], function(view) {
		view.init({
			tableName: '${tableName}',
			uuid: '${uuid}',
			id: '${id}',
			_addMore: '${_addMore!}',
			extParams: '${extParams}'
		});
	});
</script>

