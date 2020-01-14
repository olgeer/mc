<style type="text/css">
	.device-type-img {
		width: 24px;
		height: 24px;
	}
	.device-item {
		width: 220px;
		height: 90px;
		padding: 8px;
		border-radius: 4px;
		transition: all 0.3s ease;
	}
	.device-item-hover:hover {
	    box-shadow: 1px 1px 6px rgba(0, 0, 0, 0.3);
	}
	.device-img {
		display: inline-block;
		width: 48px;
		height: 48px;
	}
	.device-name {
		display: inline-block;
		vertical-align: middle;
		margin-left: 8px;
		color: #666;
		font-size: 14px;
	}
	.device-desc {
		padding: 8px 0;
		font-size: 12px;
	    color: #999;
	    line-height: 1.5;
	}
</style>

<div id="cmdb-content" class="row">
	<div class="col-md-12"  v-for="deviceType in deviceTypes">
		<!-- BEGIN ALERTS PORTLET-->
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<!--<i class="fa fa-cogs"></i>-->
					<img class="device-type-img" :src="deviceType.icon">
					{{deviceType.name}}
				</div>
			</div>
			<div class="portlet-body">
				<div class="clearfix">
					<div class="pull-left device-item" :class="{'device-item-hover': device.url}" v-for="device in deviceType.devices">
						<a class="ajaxify" v-if="device.url" :href="device|custUrl" :module_id="device.url | moduleId" data-html="true">
							<div>
								<img class="device-img" :src="device.icon">
								<div class="device-name">{{device.name}} : {{device.count}}</div>
							</div>
							<div class="device-desc">
								{{device.desc}}
							</div>
						</a>
						<a v-if="device.url" class="hidden"><i :class="device.iconClass"></i>{{device.name}}</a>
						<div v-else>
							<div>
								<img class="device-img" :src="device.icon">
								<div class="device-name">{{device.name}}: 0</div>
							</div>
							<div class="device-desc">
								{{device.desc}}
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END ALERTS PORTLET-->
	</div>
</div>

<script type="text/javascript">
	requirejs.config({
		baseUrl : "assets/global",
	});
	require(['../custom/scripts/cmdb/configuration/index_init'], function(view) {
		view.init();
	});
</script>