define(['vue', 'lodash', 'moment'], function (Vue, _, moment) {

    Vue.filter('data', function(val, fmt) {
        if (!val) return '';
        return moment(val).format(fmt);
    });

    function init(params) {
        var vm = new Vue({
            el: $('#' + params.uuid)[0],
            data: {
                items: [],
                monitorTime: '1',
                platformName: '',
                debouncePlatformName: '',
                start: '',
                end: ''
            },
            computed: {
                filterItems: function() {
                    var list = this.items;
                    list = list.filter(function (platform) {
                        if (vm.debouncePlatformName) {
                            return platform.platform_name.indexOf(vm.debouncePlatformName) != -1;
                        }
                        return true;
                    });

                    list.sort(function(a, b) {
                        if (!a.errorCount) a.errorCount = 0;
                        if (!b.errorCount) b.errorCount = 0;
                        if (!a.warnCount) a.warnCount = 0;

                        if (!b.warnCount) b.warnCount = 0;

                        if (a.errorCount > b.errorCount) return -1;
                        else if (a.errorCount < b.errorCount) return 1;
                        else {
                            if (a.warnCount > b.warnCount) return -1;
                            else if (a.warnCount < b.warnCount) return 1;
                            return 0;
                        }
                    });

                    return list;
                }
            },
            watch: {
                monitorTime: function (newMonitorTime) {
                    this.loadData();
                },
                platformName: _.debounce(function() {
                    this.debouncePlatformName = vm.platformName;
                }, 500)
            },
            methods: {
                loadData: function () {
                    var monitorTime = this.monitorTime;
                    var data = null;
                    if (monitorTime) {
                        data = {monitorTime: monitorTime};
                    } else {
                        var start = $('#' + params.uuid + '_start').val();
                        var end = $('#' + params.uuid + '_end').val();
                        if (start && end) {
                            data = {start: start, end: end};
                        } else {
                            return ;
                        }
                    }

                    $.ajax({
                        url: 'monitor/platform/char_info_data',
                        data: data,
                        dataType: 'json',
                        success: function (rs) {
                            //var platformList = processData(rs);
                            vm.items = rs;
                            vm.start = data.start || '';
                            vm.end = data.end || '';
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
                },
                getClass: function(item) {
                    if (item.errorCount && item.errorCount > 0) {
                        return 'bs-callout-danger';
                    } else if (item.warnCount && item.warnCount > 0) {
                        return 'bs-callout-warning';
                    }
                    return 'bs-callout-info';
                }
            },
            mounted: function() {
                this.loadData();

                var id = setInterval(function() {
                    if ($('#' + params.uuid).length <= 0) {
                        clearInterval(id);
                        return;
                    }
                    vm.loadData();
                }, 30 * 1000);

            }
        });

        $('#' + params.uuid + '_start').on('change', function() {
            vm.loadData();
            localStorage.start = $(this).val();
        });

        $('#' + params.uuid + '_end').on('change', function() {
            vm.loadData();
            localStorage.end = $(this).val();
        });

        var start = localStorage.start;
        var end = localStorage.end;

        start && $('#' + params.uuid + '_start').val(start);
        end && $('#' + params.uuid + '_end').val(end);
    }


    return {
        init: init
    };
});