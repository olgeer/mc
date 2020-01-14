<style>
    .host-view-monitor-item {
        line-height: 30px;
        cursor: pointer;
        position: relative;
    }

    .host-view-monitor-item:hover {
        background: #f5f5f5;
    }

    .zabbix-monitor-items > div {
        float: left;
        padding: 5px 0;
        font-size: 20px;
        border-radius: 5px;
        border: 1px #ccc solid;
        margin: 5px 0 5px 5px;
        min-width: 90px;
    }

    .zabbix-monitor-items .item-name {
        font-size: 12px;
        text-align: center;
    }

    .zabbix-monitor-items .item-value {
        text-align: center;
        line-height: 1.5em;
        font-weight: 600;
        min-height: 30px;
    }

    .triangle-right {
        width: 0;
        height: 0;
        border-top: 8px solid transparent;
        border-left: 12px solid #169ef4;
        border-bottom: 8px solid transparent;
        position: absolute;
        top: 7px;
    }

    .triangle-left {
        width: 0;
        height: 0;
        border-top: 8px solid transparent;
        border-right: 12px solid #169ef4;
        border-bottom: 8px solid transparent;
        position: absolute;
        top: 7px;
    }

    .triangle-up {
        width: 0;
        height: 0;
        border-left: 8px solid transparent;
        border-right: 8px solid transparent;
        border-bottom: 12px solid #169ef4;
        position: absolute;
        top: 7px;
    }

    .zabbix-monitor-items .active {
        position: relative;
    }

    .zabbix-monitor-items .active:before,.zabbix-monitor-items .active:after{
        content:"";display:block;
        border-width:10px;
        position:absolute;
        bottom:-21px;
        left: 34px;
        border-style:solid dashed dashed;
        border-color: #cccccc transparent transparent;
        font-size:0;
        line-height:0;
    }

    .zabbix-monitor-items .active:after{
        bottom:-20px;
        border-color:#FFF transparent transparent;
    }

    .pointer {
        cursor: pointer;
    }

    .ignore {
        right: 0;
        position: absolute;
        display: none;
    }

    .host-view-monitor-item:hover .ignore {
        display: block;
    }

</style>
<div class="row host-detail" v-if="true" id="${uuid}" style="font-size: 12px;">
    <div class="col-md-8" style="padding-right: 8px;">
        <div class="portlet blue box" style="margin-bottom: 16px;">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-server"></i> 告警详情
                </div>
            </div>
            <div class="portlet-body" style="height: 360px;overflow: auto;">
                <div class="clearfix">
                    <div class="pull-left" style="line-height: 36px;color: #333333;font-size: 18px;">{{detailMonitor.alarmtitle}}</div>
                    <div class="pull-right" style="color: #8e8e8e;margin-top: 15px;">{{detailMonitor.alarmtime}}</div>
                </div>
                <hr style="margin-top: 10px;">
                <pre style="font-size: 12px;line-height: 1.5em;padding: 0;margin: 0;background-color: #fff;border: 0;">{{detailMonitor.alarmcontent}}</pre>
            </div>
        </div>
    </div>

    <div class="col-md-4" style="padding-left: 8px;">
        <div class="portlet blue box" style="margin-bottom: 16px;">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-dashboard"></i> 告警信息
                </div>
            </div>
            <div class="portlet-body" style="height: 360px;overflow: auto;">
                <ul class="feeds">
                    <li class="host-view-monitor-item" v-for="monitor in monitors" @click="showDetail(monitor)">
                        <div class="triangle-right" v-if="detailMonitor && detailMonitor.id == monitor.id"></div>
                        <div class="pull-left" style="margin-left: 16px;">
                            {{monitor.alarmtitle}}
                            <span class="label label-sm" style="background-color: #108ee9">{{itypeMap[monitor.itype]}} </span>
                        </div>
                        <div class="pull-right">{{monitor.alarmtime | data('MM-DD HH:mm:ss')}}</div>
                        <div class="btn btn-sm btn-info ignore" @click.stop.prevent="ingore(monitor)">处理</div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="col-md-6" style="padding-right: 8px;">
        <div class="portlet blue box">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-magic"></i> 监控指标
                </div>
            </div>
            <div class="portlet-body" style="padding: 10px 0;">
                <div class="zabbix-monitor-items clearfix"  v-if="zabbixItems.length > 0">
                    <div class="pointer" :class="{active: activeMonitor == 'cpu'}" @click="selectMonitorItem('cpu')">
                        <div class="item-value">{{zabbixData.cpu && zabbixData.cpu.lastvalue | fmt('0.0')}}{{zabbixData.cpu && zabbixData.cpu.units}}</div>
                        <div class="item-name">cpu</div>
                    </div>
                    <div class="pointer" :class="{active: activeMonitor == 'memory'}" @click="selectMonitorItem('memory')">
                        <div class="item-value" v-if="!zabbixData.winMem">{{zabbixData.menUsed.lastvalue / zabbixData.menTotal.lastvalue * 100 | fmt('0.0')}}%</div>
                        <div class="item-value" v-if="zabbixData.winMem">{{zabbixData.winMem.lastvalue | fmt('0.0')}}{{zabbixData.winMem && zabbixData.winMem.units}}</div>
                        <div class="item-name">内存</div>
                    </div>
                    <div class="pointer" :class="{active: activeMonitor == 'disk'}" @click="selectMonitorItem('disk')">
                        <div class="item-value">{{zabbixData.disk && zabbixData.disk.lastvalue | fmt('0.0')}}{{zabbixData.disk && zabbixData.disk.units}}</div>
                        <div class="item-name">剩余磁盘</div>
                    </div>
                    <div class="pointer" :class="{active: activeMonitor == 'network'}" @click="selectMonitorItem('network')">
                        <div class="item-value">{{zabbixData.network && zabbixData.network.lastvalue | fmt('0.0')}}{{zabbixData.network && zabbixData.network.units}}</div>
                        <div class="item-name">网络丢包率</div>
                    </div>
                    <div class="pointer" :class="{active: activeMonitor == 'processes'}" @click="selectMonitorItem('processes')">
                        <div class="item-value">{{zabbixData.proc.lastvalue}}</div>
                        <div class="item-name"> 进程</div>
                    </div>
                   <#--
                    <div>主机 : SA0WC02065</div>
                    <div>zabbix : 2.0</div>
                    -->
                </div>
                <div style="margin: 5px;"  v-if="zabbixItems.length > 0">
                    <table class="table table-bordered table-striped">
                        <tr>
                            <#--<th>key</th>-->
                            <th>指标名称</th>
                            <th>数据</th>
                            <th></th>
                        </tr>
                        <tr v-for="item in filterZabbixItems">
                            <#--<td>{{item.key_}}</td>-->
                            <td>{{getFullName(item)}}</td>
                            <td>
                                <span v-if="item.units == 'B'">
                                    <span v-if="item.lastvalue < 1024">{{item.lastvalue}}{{item.units}}</span>
                                    <span v-else-if="item.lastvalue < 1024 * 1024">{{item.lastvalue / 1024 | fmt('0.0')}}KB</span>
                                    <span v-else-if="item.lastvalue < 1024 * 1024 * 1024">{{item.lastvalue / 1024 / 1024 | fmt('0.0')}}MB</span>
                                    <span v-else>{{item.lastvalue / 1024 / 1024 / 1024 | fmt('0.0')}}GB</span>
                                </span>
                                <span v-else-if="item.units == '%'">
                                    {{item.lastvalue | fmt('0.0')}}{{item.units}}
                                </span>
                                <span v-else>{{item.lastvalue}}{{item.units}}</span>
                            </td>
                            <td></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6" style="padding-left: 8px;">
        <div class="portlet blue box">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-magic"></i> 日志信息
                </div>
            </div>
            <div class="portlet-body">
                <ul class="list-group" v-if="logs.length > 0">
                    <li class="list-group-item" v-for="log in logs">
                        <div class="clearfix">
                            <div class="pull-left">{{log.Title}}</div>
                            <div class="pull-right" style="color: rgb(142, 142, 142);">{{+log.CreateTime.substring(6, log.CreateTime.length - 2) | data('YYYY-MM-DD HH:mm:ss.SSS')}}</div>
                        </div>
                        <hr style="margin: 8px 0;"/>
                        <div style="word-break:break-all;line-height: 1.5em;">{{log.Message}}</div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</div>

<script>
    require(["jquery", "../custom/scripts/monitor/host_view_init"], function ($, page) {
        //Metronic.init();
        page.init({
            uuid: '${uuid}',
            platformId: '${platformId}',
            serverIp: '${serverIp}',
            monitorTime: '${monitorTime}',
            hostName: '${hostName!}',
            start: '${start!}',
            end: '${end!}'
        });
    });
</script>