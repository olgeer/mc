<div class="row" id="room-content-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-sitemap"></i>机房信息
				</div>
			</div>
			<div class="portlet-body">
				<#include "room_fragment.ftl" />
			</div>
		</div>
	</div>
	
	<div class="col-md-4">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-book"></i>设备信息
				</div>
				<a v-if="devices.length > 0" class="ajaxify pull-right" data-html="true" href="configuration/device/list?belongs_to_room_id=${id}" module_id="module_device_${id}" style="color: #fff">
					<i class="fa fa-list-ul"></i>
					查看更多
				</a>
				<a class="hidden">
					<i class="fa fa-book"></i>
					{{room.roomname}} 设备
				</a>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td>设备编码</td>
							<td>购买时间</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="device in devices">
							<td>
								<a class="btn btn-xs ajaxify" :module_id="device.id | concat('module_device_view_')" :href="device.id | concat('configuration/device/view?id=')" data-html="true">{{device.maintenance_code}}</a>
								<a class="hidden"><i class="fa fa-eye"></i> 设备详情</a>
							</td>
							<td>{{device.purchase_date}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/room/room_view_init'], function(view) {
		view.init({
			id: '${id}'
		});
	});
</script>