<style>[v-if] {display: none}</style>
<div class="row">
	<div class="col-md-12">
		<form class="form-horizontal" id="cmdb-${uuid}">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-plus-square-o"></i> 绑定设备
					</div>
				</div>
				<div class="portlet-body form">
					<div class="form-horizontal">
						<div class="form-body">
							<div class="row">
								<input type="hidden" name="ipId" value="${ipId}">
								<input type="hidden" name="id" value="${id!}">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">
											ip
										</label>
										<div class="col-md-8">
											<input type="text" class="form-control" value="${ip}" readonly>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<v-form-component :column="column" v-for="column in columns"></v-form-component>
								<button type="button" class="btn btn-default" onClick="$(this).closest('form').find('[name=belongsDeviceId]').select2('val', null)">清空</button>
							</div>
						</div>
						
						<div class="form-actions fluid">
							<div class="row text-center">
								<div class=" col-md-12">
									<button v-if="true" class="btn btn-sm green" type="button" @click="submit">
										<i class="fa fa-check"></i> 提交
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
	require(['../custom/scripts/cmdb/device_logic_interface/device_logic_interface_init'], function(view) {
		view.init({
			uuid: '${uuid}',
			fromId: '${fromId!}',
			columns: [{
				formcontrol:"search",
				showname:"绑定主机",
				aliasName:"belongsDeviceId",
				dictionaryname:"cmdb_host:id:host_name",
				maynull: true,
				value: '${belongsDeviceId!}'
			}]
		});
	});
</script>

