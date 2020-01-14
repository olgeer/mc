define(['vue', 'lodash', 'numeral'], function (Vue, _, numeral) {

    Vue.filter('fmt', function(val, fmt) {
        if (!val) return '';
        return numeral(val).format(fmt);
    });

    function init(params) {
        var currentIndex = 0;

        var vm = new Vue({
            el: $('#' + params.uuid)[0],
            data: {
                monitors: [],
                itypeMap: {},
                detailMonitor: {},
                zabbixData: {},
                zabbixItems: [],
                logs: [],
                activeMonitor: ''
            },
            computed: {
                filterZabbixItems: function() {
                    var self = this;
                    if (!this.activeMonitor) {
                        return this.zabbixItems;
                    }

                    var pattern = new RegExp(self.activeMonitor, 'i');
                    return this.zabbixItems.filter(function (item) {
                        return pattern.test(item.key_) || pattern.test(item.name)// item.key_.indexOf(self.activeMonitor) != -1 || item.name.indexOf(self.activeMonitor) != -1
                    });
                }
            },
            watch: {

            },
            filter: {
                
            }
            ,
            methods: {
                showDetail: function(monitor) {
                    this.detailMonitor = monitor;

                    currentIndex += 1;

                    loadLog(monitor);
                    loadItem(monitor);
                },

                selectMonitorItem: function(type) {
                    if (this.activeMonitor == type) {
                        this.activeMonitor = '';
                    } else {
                        this.activeMonitor = type;
                    }
                },

                getFullName: function(item) {
                    var name = item.name;
                    if (name.indexOf('$') <= -1) return name;

                    var re = /\[(.*)\]/i;
                    var key = item.key_;
                    var result = re.exec(key);
                    if (result && result[1]) {
                        var str = result[1];
                        var params = str.split(',');
                        for (var i = 0; i < params.length; i++) {
                            name = name.replace('$' + (i + 1), params[i]);
                        }
                    }

                    return name;
                },

                ingore: function(item) {
                    var vm = this;

                    Metronic.blockUI({
                        target: '',
                        boxed: true,
                        message: '请稍候...'
                    });

                    $.get('monitor/ignore?ids=' + item.id, function() {
                        $.ajax({
                            url: 'monitor/host/view_data',
                            data: {platformId: params.platformId, serverIp: params.serverIp, monitorTime: params.monitorTime, start: params.start, end: params.end},
                            dataType: 'json',
                            success: function (rs) {
                                vm.monitors = rs.monitors;
                                vm.itypeMap = rs.itypeMap;
                                if (rs.monitors.length > 0) {
                                    vm.showDetail(rs.monitors[0]);
                                } else {
                                    currentIndex += 1;
                                    vm.detailMonitor = {};
                                    vm.zabbixData = {};
                                    vm.zabbixItems = [];
                                    vm.logs = [];
                                }

                                Metronic.unblockUI();
                            }
                        });
                    });
                },

                loadData: function () {
                    $.ajax({
                        url: 'monitor/host/view_data',
                        data: {platformId: params.platformId, serverIp: params.serverIp, monitorTime: params.monitorTime, start: params.start, end: params.end},
                        dataType: 'json',
                        success: function (rs) {
                            vm.monitors = rs.monitors;
                            vm.itypeMap = rs.itypeMap;
                            rs.monitors.length > 0 && vm.showDetail(rs.monitors[0]);
                        }
                    });
                }

            },
            mounted: function() {
                this.loadData();
            }
        });


        function loadLog(monitor) {
            vm.logs = [];
            var index  = currentIndex;
            if (!monitor.systemId || !monitor.traceId) return;

            $.ajax({
                url: 'log/api',
                type: 'post',
                data: {systemId: monitor.systemId, traceId: monitor.traceId, maxId: 0, pageSize: 100, alarmtime: monitor.alarmtime},
                dataType: 'json',
                success: function (rs) {
                    if (index != currentIndex) {return;}
                    vm.logs = rs.ReturnData || [];
                }
            });
        }



        function loadItem(monitor) {
            vm.zabbixData = {};
            vm.zabbixItems = [];

            var index  = currentIndex;
            if (!params.hostName) return;

            $.ajax({
                url: 'monitor/host/history_items',
                data: {hostName: params.hostName || 'SA0LW00555', alarmtime: monitor.alarmtime || '2017-07-3 10:15:59', id: monitor.id},
                dataType: 'json',
                success: function (rs) {
                    if (index != currentIndex) {return;}

                    var zabbixData = {};
                    var temp = {};
                    for (var i = 0; i < rs.length; i++) {
                        var item = rs[i];
                        temp[item.key_] = item;
                    }

                    zabbixData.cpu = temp['CPU_total_usage'] || temp['system.cpu.util'] || {};
                    zabbixData.menUsed = temp['vm.memory.size[available]'] || {};
                    zabbixData.menTotal = temp['vm.memory.size[total]'] || {};
                    zabbixData.winMem = temp['FreeMemoryPercent_Windows'];
                    zabbixData.proc = temp['proc.num[,,run]'] || temp['proc.num[]'] || {};
                    zabbixData.disk = temp['vfs.fs.size[/,pfree]'] || temp['vfs.fs.size[C:,pfree]'] || {};
                    zabbixData.network = temp['if_dropped_per[eth0]'] || {};


                    vm.zabbixData = zabbixData;
                    vm.zabbixItems = rs;
                }
            });
        }
    }


    return {
        init: init
    };
});