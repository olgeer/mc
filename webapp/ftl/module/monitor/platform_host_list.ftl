<#-- BEGIN PAGE CONTENT-->
<div class="row" v-if="true" id="${uuid}">
    <div class="col-md-12">
        <div class="table-header-search-content">
            <div class="form-inline clearfix">
                <div class="form-group pull-left">
                    <div class="search-header-title">
                        <i class="fa fa-tachometer"></i> 主机监控
                    </div>
                </div>
                <div class="form-group pull-right" style="width: 190px;">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">监控时间段</span>
                        <select class="form-control" v-model="monitorTime">
                            <option value="1">最近30分钟</option>
                            <option value="2">最近1小时</option>
                            <option value="3">最近3小时</option>
                            <option value="4">最近6小时</option>
                            <option value="5">最近1天</option>
                            <option value="6">最近3天</option>
                        </select>
                    </div>
                </div>
                <div class="form-group pull-right" style="width: 180px;margin-right: 8px;">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">IP</span>
                        <input class="form-control" v-model="ip" placeholder="按IP搜索">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <table  class="table table-striped table-bordered table-hover">
            <thead>
                <tr role="row" class="heading">
                    <th width="120px">主机</th>
                    <th width="120px">监控指标</th>
                    <th>异常次数</th>
                    <th>异常信息</th>
                    <th width="120px">最近发生时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <template v-for="host in filterItems" :key="host.ip">
                    <template v-if="host.statMonitors && host.statMonitors.length > 0">
                        <tr  v-for="(statMonitor, index) in host.statMonitors">
                            <td :rowspan="host.typeCount" v-if="index == 0">
                                {{host.ip}}<br>
                                {{host.hostName}}
                            </td>
                            <td>{{statMonitor.typeName}}</td>
                            <td>{{statMonitor.typeCount}}</td>
                            <td :title="statMonitor.contents" @click="showModal(statMonitor)" style="cursor: pointer;">{{statMonitor.titles}}</td>
                            <td>{{statMonitor.maxAlarmtime}}</td>
                            <td>
                                <a href="javascript:void(0)" class="btn btn-xs blue" @click="ignore(statMonitor.ids)">忽略</a>
                            </td>
                        </tr>
                    </template>
                    <template v-else>
                        <tr>
                            <td>{{platform.platformName}}</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </template>
                </template>
            </tbody>
        </table>
    </div>
</div>
<#-- END PAGE CONTENT-->
<div class="modal fade bs-modal-lg" id="${uuid}_modal" tabindex="-1" role="dialog" data-width="720">
    <div>
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <h4 class="modal-title">异常信息</h4>
        </div>
        <div>
            <div style="max-height: 500px; overflow: auto;padding: 20px;">
                <div v-for="monitor in monitors">
                    <div>
                        <span style="font-size: 15px;">{{monitor.alarmtitle}}</span>
                        <span style="color: #999;font-size: 12px;">{{monitor.alarmtime}}</span>
                    </div>
                    <div style="padding: 5px 0 0;">
                        <span style="color: rgba(0,0,0,.65);">{{monitor.alarmcontent}}</span>
                    </div>
                    <hr>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
	require(["jquery", "../custom/scripts/monitor/platform_host_list_init", "bootstrap_datepicker"], function($, page) {
        //Metronic.init();
        page.init({
            uuid: '${uuid}',
            projectid: '${projectid}',
            monitorTime: '${monitorTime!}'
        });
    });
</script>