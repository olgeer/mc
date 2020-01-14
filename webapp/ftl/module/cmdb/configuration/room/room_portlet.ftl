<div class="portlet blue box">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-sitemap"></i>机房信息
		</div>
	</div>
	<div class="portlet-body">
		<a class="ajaxify"
				:module_id="room.id | concat('module_room_view_')" :href="room.id | concat('configuration/room/view?id=')" title="机房详情" data-html="true">
			<#include "room_fragment.ftl" />
		</a>
		<a class="hidden"><i class="fa fa-eye"></i> 机房详情</a>
	</div>
</div>