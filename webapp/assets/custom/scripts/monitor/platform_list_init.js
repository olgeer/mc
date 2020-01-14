define(['vue', 'lodash'], function (Vue, _) {

    function init(params) {
        var vm = new Vue({
            el: $('#' + params.uuid)[0],
            data: {
                items: [],
                monitorTime: 6,
                platformName: '',
                debouncePlatformName: ''
            },
            computed: {
                filterItems: function() {
                    var list = this.items;
                    return list.filter(function (platform) {
                        if (vm.debouncePlatformName) {
                            return platform.platformName.indexOf(vm.debouncePlatformName) != -1;
                        }
                        return true;
                    })
                }
            },
            watch: {
                monitorTime: function (newMonitorTime) {
                    this.loadData(newMonitorTime);
                },
                platformName: _.debounce(function() {
                    this.debouncePlatformName = vm.platformName;
                }, 500)

            },
            methods: {
                loadData: function (monitorTime) {
                    $.ajax({
                        url: 'monitor/platform/data',
                        data: {monitorTime: monitorTime},
                        dataType: 'json',
                        success: function (rs) {
                            var platformList = processData(rs);
                            vm.items = platformList;
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

                // var id = setInterval(function() {
                //     if ($('#' + params.uuid).length <= 0) {
                //         clearInterval(id);
                //         return;
                //     }
                //     vm.loadData(vm.monitorTime);
                // }, 5 * 1000);

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

        var platformList = [];
        for (var i = 0; i < list.length; i++) {
            var platform = list[i];

            var monitors = platform.monitors || [];
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

            var newPlatform = {
                platformId: platform.platformId,
                platformName: platform.platformName,
                statMonitors: statTypeMonitor,
                typeCount: typeCount,
                monitorSize: monitors.length
            };

            platformList.push(newPlatform);
        }


        platformList.sort(function(a, b) {
            return b.monitorSize - a.monitorSize;
        });

        return platformList;
    }


    return {
        init: init
    };
});