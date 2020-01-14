define(['vue', 'lodash'], function (Vue, _) {

    function init(params) {
        var vm = new Vue({
            el: $('#' + params.uuid)[0],
            data: {
                items: [],
                monitorTime: params.monitorTime || 6,
                ip: '',
                debounceIp: ''
            },
            computed: {
                filterItems: function() {
                    var list = this.items;
                    return list.filter(function (host) {
                        if (vm.debounceIp) {
                            return host.ip.indexOf(vm.debounceIp) != -1;
                        }
                        return true;
                    })
                }
            },
            watch: {
                monitorTime: function (newMonitorTime) {
                    this.loadData(newMonitorTime);
                },
                ip: _.debounce(function() {
                    this.debounceIp = vm.ip;
                }, 500)
            },
            methods: {
                loadData: function (monitorTime) {
                    $.ajax({
                        url: 'monitor/platform_host/data',
                        data: {monitorTime: monitorTime, projectid: params.projectid},
                        dataType: 'json',
                        success: function (rs) {
                            var hostList = processData(rs);
                            vm.items = hostList;
                        }
                    });
                },
                ignore: function (ids) {
                    $.get('monitor/ignore?ids=' + ids, function() {
                        vm.loadData(vm.monitorTime);
                    });
                },
                showModal: function(statMonitor) {
                    modal.monitors = statMonitor.monitors;
                    $('#' + params.uuid + '_modal').modal('show');
                }
            },
            mounted: function() {
                this.loadData(this.monitorTime);
            }
        });

        var modal = new Vue({
            el: $('#' + params.uuid + '_modal')[0],
            data: {
                monitors: []
            }
        });
    }


    function processData(rs) {
        var list = rs.list;
        var itypeMap = rs.itypeMap;

;
        var hostMap = {};
        for (var i = 0; i < list.length; i++) {
            var monitor = list[i];
            var serverip = monitor.serverip;
            var computername = monitor.computername;
            var host = hostMap[serverip] || (hostMap[serverip] = {ip: serverip, hostName: computername, monitors: []});
            host.monitors.push(monitor);
        }

        var hostList = [];
        for (var i in hostMap) {
            hostList.push(hostMap[i]);
        }


        var rsHostList = [];
        for (var i = 0; i < hostList.length; i++) {
            var host = hostList[i];

            var monitors = host.monitors || [];
            var typeMap = {};
            for (var j = 0; j < monitors.length; j++) {
                var monitor = monitors[j];
                var itype = monitor.itype;
                var typeList = typeMap[itype] || (typeMap[itype] = []);
                typeList.push(monitor);
            }

            //if (monitors.length == 0) continue;

            var statTypeMonitor = [];
            var typeCount = 0;
            for (var k in typeMap) {
                var monitorList = typeMap[k];
                monitorList.sort(function(a, b) {
                    var t1 = a.alarmtime;
                    var t2 = b.alarmtime;
                    if (t1 > t2) {
                        return 1;
                    } else if (t1 < t2) {
                        return -1;
                    } else {
                        return 0;
                    }
                });


                var titieList = [];
                var contentList = [];
                var ids = [];

                for (var l = 0; l < monitorList.length; l++) {
                    var monitor = monitorList[l];
                    titieList.push(monitor.alarmtitle);
                    contentList.push(monitor.alarmcontent);
                    ids.push(monitor.id);
                }

                var o = {};
                o.type = k;
                o.typeName = itypeMap[k];
                o.typeCount = monitorList.length;
                o.titles = titieList.join(',');
                o.contents = contentList.join(',');
                o.ids = ids.join(',');
                o.maxAlarmtime = monitorList[monitorList.length - 1].alarmtime;
                o.monitors = monitorList;

                statTypeMonitor.push(o);
                typeCount++;
            }

            var newHost = {
                ip: host.ip,
                hostName: host.hostName,
                statMonitors: statTypeMonitor,
                typeCount: typeCount
            };

            rsHostList.push(newHost);
        }

        return rsHostList;
    }


    return {
        init: init
    };
});