<#-- BEGIN PAGE CONTENT-->
<style>
    .platform-content > div {
        width: 333px;
        float: left;
        margin-right: 16px;
    }

    iframe[src="about:blank"] {
         display: block !important;
    }
</style>
<div class="row" v-if="true" id="${uuid}">
    <div class="col-md-12">
        <div class="table-header-search-content">
            <div class="form-inline clearfix">
                <div class="form-group pull-left">
                    <div class="search-header-title">
                        <i class="fa fa-tachometer"></i> 平台监控
                    </div>
                </div>
                <div v-show="!monitorTime" style="float: right;" class="input-group input-group-sm input-large date-picker input-daterange">
                    <input type="text" class="form-control" placeholder="开始时间" id="${uuid}_start" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', onpicked:function(){ $(this).change(); }})">
                    <span class="input-group-addon"> 到 </span>
                    <input type="text" class="form-control" placeholder="结束时间" id="${uuid}_end" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', onpicked:function(){ $(this).change(); }})">
                </div>
                <div class="form-group pull-right" style="width: 190px;margin-right: 8px;">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">监控时间段</span>
                        <select class="form-control" v-model="monitorTime">
                            <option value="">自定义</option>
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
                        <span class="input-group-addon">平台</span>
                        <input class="form-control" v-model="platformName" placeholder="按平台名称搜索">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12 platform-content">
        <div class="bs-callout"
             :class="getClass(item)"
             v-for="item in filterItems">
            <div class="pull-left" style="padding: 20px;width: 220px;">
                <a class="ajaxify" :href="'monitor/topology/char?platformId=' + item.id + '&monitorTime=' + monitorTime + '&start=' + start + '&end=' + end" :module_id="'monitor_topology_char_' + item.id " data-html="true"><h4 class="text-overflow" :title="item.platform_name">{{item.platform_name}}</h4></a>
                <a class="hidden">{{item.platform_name}}</a>
                <div class="text-overflow" :title="item.manager" style="font-size: 12px;color: #666;">
                    项目经理：{{item.manager}}
                </div>
                <div class="text-overflow" :title="item.developer" style="font-size: 12px;color: #666;">
                    研发人：{{item.developer}}
                </div>
            </div>
            <div class="pull-left" style="padding: 20px 0;">
                <div class="text-danger" style="font-size: 12px">异常： <span style="font-weight: 600;">{{item.errorCount || 0}}</span></div>
                <div class="text-warning" style="font-size: 12px">警告： <span style="font-weight: 600;">{{item.warnCount || 0}}</span></div>
                <div style="margin-top: 5px;" title="最近发生时间">{{item.lastHappenDate | data('MM-DD HH:mm')}}</div>
            </div>
        </div>
    </div>
</div>
<#-- END PAGE CONTENT-->
<script>
	require(["jquery", "../custom/scripts/monitor/platform_char_info_init", "bootstrap_datepicker"], function($, page) {
        //Metronic.init();
        page.init({
            uuid: '${uuid}'
        });
    });
</script>