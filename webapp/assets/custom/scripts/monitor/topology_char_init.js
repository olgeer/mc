define(['vue', 'lodash', 'd3'], function (Vue, _, d3) {


    function Topology(ele, uuid, platformId, monitorTime, start, end){
        typeof(ele)=='string' && (ele=document.getElementById(uuid + '-' + ele));

        var w=ele.clientWidth,
            h=ele.clientHeight,
            self=this;

        this.w = w;
        this.h = h;
        this.uuid = uuid;
        this.platformId = platformId;
        this.monitorTime = monitorTime;
        this.start = start;
        this.end = end;

        this.nodes = [];
        this.links = [];
        this.clickFn=function(){};
        var svg = this.vis = d3.select(ele)
            .append("svg:svg")
            .attr("width", w)
            .attr("height", h)
            .call(d3.zoom().scaleExtent([1 / 2, 8]).on("zoom", function() {
                svg.attr("transform", d3.event.transform);
                //self.transform = d3.event.transform;
            })).append("g");


        var simulation = d3.forceSimulation()
            .force("link", d3.forceLink().id(function(d) {
                return d.id;
            }))
            .force("charge", d3.forceManyBody().strength(-2000))
            //.force("center", d3.forceCenter(width_2 / 2, height_2 / 2))
            .force("xPos", d3.forceX(w / 2))
            .force("yPos", d3.forceY(h / 2))
            .on("tick", function(x) {
                self.vis.selectAll("g.node")
                    .attr("transform", function(d) {
                        var dix = 64;
                        //d.x = Math.max(dix, Math.min(w - dix / 2, d.x));
                        //d.y = Math.max(dix, Math.min(h - dix / 2, d.y));
                        return "translate(" + d.x + "," + d.y + ")";
                    });

                self.vis.selectAll("line.link")
                    .attr("x1", function(d) { return d.source.x - 32; })
                    .attr("y1", function(d) { return d.source.y - 32; })
                    .attr("x2", function(d) { return d.target.x - 32; })
                    .attr("y2", function(d) { return d.target.y - 32; });
            });
        simulation.stop();
        this.simulation = simulation;

    }


    Topology.prototype.doZoom=function(){
        d3.select(this).select('g').attr("transform","translate(" + d3.event.translate + ")"+ " scale(" + d3.event.scale + ")");
    }


    Topology.prototype.addNode=function(node){
        this.nodes.push(node);
    }

    Topology.prototype.addNodes=function(nodes){
        if (Object.prototype.toString.call(nodes)=='[object Array]' ){
            var self=this;
            nodes.forEach(function(node){
                self.addNode(node);
            });

        }
    }


    Topology.prototype.addLink=function(source,target){
        this.links.push({source:this.findNode(source),target:this.findNode(target)});
    }


    Topology.prototype.addLinks=function(links){
        if (Object.prototype.toString.call(links)=='[object Array]' ){
            var self=this;
            links.forEach(function(link){
                self.addLink(link['source'],link['target']);
            });
        }
    }


    Topology.prototype.removeNode=function(id){
        var i=0,
            n=this.findNode(id),
            links=this.links;
        while ( i < links.length){
            links[i]['source']==n || links[i]['target'] ==n ? links.splice(i,1) : ++i;
        }
        this.nodes.splice(this.findNodeIndex(id),1);
    }


    Topology.prototype.removeChildNodes=function(id){
        var node=this.findNode(id),
            nodes=this.nodes;
        links=this.links,
            self=this;

        var linksToDelete=[],
            childNodes=[];

        links.forEach(function(link,index){
            link['source']==node
            && linksToDelete.push(index)
            && childNodes.push(link['target']);
        });

        linksToDelete.reverse().forEach(function(index){
            links.splice(index,1);
        });

        var remove=function(node){
            var length=links.length;
            for(var i=length-1;i>=0;i--){
                if (links[i]['source'] == node ){
                    var target=links[i]['target'];
                    links.splice(i,1);
                    nodes.splice(self.findNodeIndex(node.id),1);
                    remove(target);

                }
            }
        }

        childNodes.forEach(function(node){
            remove(node);
        });

        for(var i=nodes.length-1;i>=0;i--){
            var haveFoundNode=false;
            for(var j=0,l=links.length;j<l;j++){
                ( links[j]['source']==nodes[i] || links[j]['target']==nodes[i] ) && (haveFoundNode=true)
            }
            !haveFoundNode && nodes.splice(i,1);
        }
    }


    Topology.prototype.findNode=function(id){
        var nodes=this.nodes;
        for (var i in nodes){
            if (nodes[i]['id']==id ) return nodes[i];
        }
        return null;
    }


//���ҽڵ�����������
    Topology.prototype.findNodeIndex=function(id){
        var nodes=this.nodes;
        for (var i in nodes){
            if (nodes[i]['id']==id ) return i;
        }
        return -1;
    }


    Topology.prototype.setNodeClickFn=function(callback){
        this.clickFn=callback;
    }


    Topology.prototype.update=function(){
        var simulation = this.simulation;

        var link = this.vis.selectAll("line.link")
            .data(this.links, function(d) { return d.source.id + "-" + d.target.id; })
            .attr("class", function(d){
                return d['source']['status'] && d['target']['status'] ? 'link' :'link link_error';
            });

        link.enter().insert("svg:line", "g.node")
            .attr("class", function(d){
                return d['source']['status'] && d['target']['status'] ? 'link' :'link link_error';
            });

        link.exit().remove();

        var node = this.vis.selectAll("g.node")
            .data(this.nodes, function(d) { return d.id;});

        var nodeEnter = node.enter().append("svg:g")
            .attr("class", "node")
            .call(d3.drag()
                .on("start", function(d) {
                    if (!d3.event.active)
                        simulation.alphaTarget(0.3).restart();
                    d.fx = d.x;
                    d.fy = d.y;
                })
                .on("drag", function(d) {
                    d.fx = d3.event.x;
                    d.fy = d3.event.y;
                })
                .on("end", function(d) {
                    if (!d3.event.active)
                        simulation.alphaTarget(0);
                    d.fx = null;
                    d.fy = null;
                }));


        var self=this;
        nodeEnter.filter(function(d) {
            return d.error;
        }).append("circle").style("fill","none")
            .attr("r", 48)
            .attr('cx', -32)
            .attr('cy', -32)
            .style("stroke-width","1")
            .style("stroke", 'red')
            .style("opacity", 0)
            .transition()
            .duration(1000)
            .delay(function(d, i) {
                if (i) {
                    return i * 10;
                }
                return 10;
            })
            .on("start", function repeat(d) {
                d3.active(this)
                    .attr("r", 32)
                    .style("opacity", 1)
                    .transition()
                    .attr("r", 100)
                    .style("opacity", 0)
                    .transition()
                    .on("start", repeat);
            });


        var $tip = $('#' + self.uuid + '-tip');
        nodeEnter.append("svg:image")
            .attr("class", "circle")
            .attr("xlink:href", function(d){
                //return d.web ? "./images/cloudserver.png" : "./images/certserver.png";
                var imgsrc="assets/custom/img/server.png";
                //if(d.type=="WEB")imgsrc="assets/custom/img/cloudserver.png";
                //if(d.type=="DBCluster")imgsrc="assets/custom/img/safeserver.png";
                //if(d.type=="DB")imgsrc="assets/custom/img/manageserver.png";
                if (d.node_type == 0) imgsrc="assets/custom/img/app-server.png";
                else if (d.node_type == 1) imgsrc="assets/custom/img/cache-server.png";
                else if (d.node_type == 2) imgsrc="assets/custom/img/db-server.png";
                else if (d.node_type == 3) imgsrc="assets/custom/img/jump-server.png";
                else if (d.node_type == 4) imgsrc="assets/custom/img/mag.png";
                else if (d.node_type == 5) imgsrc="assets/custom/img/msg-server.png";
                else if (d.node_type == 6) imgsrc="assets/custom/img/msg-server.png";
                else if (d.node_type == 7) imgsrc="assets/custom/img/vmhost.png";
                else if (d.node_type == 8) imgsrc="assets/custom/img/web-server.png";
                else if (d.node_type == 20) imgsrc="assets/custom/img/public.png";
                else if (d.node_type == 21) imgsrc="assets/custom/img/route.png";
                else if (d.node_type == 22) imgsrc="assets/custom/img/www.png";
                else if (d.node_type == 23) imgsrc="assets/custom/img/limbo.png";
                else if (d.node_type == 24) imgsrc="assets/custom/img/limbo.png";
                else if (d.node_type == 25) imgsrc="assets/custom/img/partnar.png";
                return imgsrc;
            })
            .attr("x", "-64px")
            .attr("y", "-64px")
            .attr("width", "64px")
            .attr("height", "64px")
            .on('click',function(d) {
                var params = {
                    platformId: self.platformId,
                    serverIp: d.node_name,
                    monitorTime: self.monitorTime,
                    start: self.start,
                    end: self.end
                };
                if (d.node_type >= 0 && d.node_type <= 8) {
                    params.hostName = d.remarks;
                }
                d.error && Layout.openTab('monitor/host/view?' + $.param(params), d.node_name ,'monitor_host_' + d.id);
            })
            .on('mouseover', function(d) {

                //console.log('w:' + self.w + ' h:' + self.h  + ' px: ' + d.x + '  py: ' + d.y);
                if (d.error) {
                    var error = d.error;
                    var msg = [];
                    var j = 0;
                    for (var i in error) {
                        msg.push('<div><span class="t-r-d t-r-d-' + ((j++ % 5) + 1) + '"></span>' + i + ' <div class="pull-right m-l-3"> : ' + error[i] + '</div></div>');
                    }
                    $tip.html(msg.join(''));

                    var w = $tip.width();
                    var h = $tip.height();

                    var x = d3.event.pageX;
                    var y = d3.event.pageY;

                    var ww = $(window).width()
                    var wh = $(window).height()

                    if (x + w > ww) {
                        x = x - w - 50;
                    } else {
                        x = x + 20;
                    }

                    if (y + h > wh) {
                        y = self.h - h + 10;
                    } else {
                        y = y - 50;
                    }

                    $tip.css({left: x + 'px', top: y + 'px'})
                        .show();
                }
            })
            // .on('mousemove', function(a, b, c) {
            //
            // })
            .on('mouseout', function() {
                $tip.hide();
            });

        nodeEnter.append("svg:text")
            .attr("class", "nodetext")
            .attr("dx", -60)
            .attr("dy", 15)
            .selectAll("tspan")
            .data(function(d) {
                var type = typeMap[d.node_type];
                var name = d.node_name;
                if (name.indexOf(',') == -1) {
                    return [type + ': ' + name];
                }
                var names = name.split(',');
                var rs = [];
                for (var i = 0; i < names.length; i++) {
                    if (i == 0) {
                        rs.push(type + ': ' + names[i]);
                    } else {
                        rs.push(names[i]);
                    }
                }
                return rs;
            })
            .enter()
            .append("tspan")
            .attr("x", function(d, i) {
                if (i < 1)
                    return null;
                else
                    return -30;
            })
            .attr("dy", function(d, i) {
                if (i < 1) {
                    return null;
                } else {
                    return '1em';
                }
            })
            .text(function(d) {
                return d;//typeMap[d.node_type] + ': ' + d.node_name;
            });


        node.exit().remove();

        //this.force.start();

        simulation.stop();

        simulation.nodes(this.nodes);
        simulation.force("link").links(this.links).distance(300);
        simulation.alpha(1);
        simulation.restart();

    }

    var typeMap = {
        '0': 'APP',
        '1': 'CACHE',
        '2': 'DB',
        '3': 'JUMP',
        '4': 'MAG',
        '5': 'MSG',
        '6': 'SER',
        '7': 'VMHOST',
        '8': 'WEB',
        '20': '公网',
        '21': '负载',
        '22': '域名',
        '23': '本端NAT',
        '24': '对端NAT',
        '25': '合作单位',
    };


    Topology.prototype.updateState=function(){
        var simulation = this.simulation;

        var link = this.vis.selectAll("line.link")
            .data(this.links, function(d) { return d.source.id + "-" + d.target.id; })
            .attr("class", function(d){
                return d['source']['status'] && d['target']['status'] ? 'link' :'link link_error';
            });

        var node = this.vis.selectAll("g.node");

        this.vis.selectAll("circle").remove();

        node.filter(function(d) {
            return d.error;
        }).append("circle")
            .style("fill","none")
            .attr("r", 48)
            .attr('cx', -32)
            .attr('cy', -32)
            .style("stroke-width","1")
            .style("stroke", 'red')
            .style("opacity", 0)
            .transition()
            .duration(1000)
            .delay(function(d, i) {
                if (i) {
                    return i * 10;
                }
                return 10;
            })
            .on("start", function repeat(d) {
                d3.active(this)
                    .attr("r", 32)
                    .style("opacity", 1)
                    .transition()
                    .attr("r", 100)
                    .style("opacity", 0)
                    .transition()
                    .on("start", repeat);
            });

    }



    function expandNode(id){
        topology.addNodes(childNodes);
        topology.addLinks(childLinks);
        topology.update();
    }

    function collapseNode(id){
        topology.removeChildNodes(id);
        topology.update();
    }



    function init(params) {
        //params.platformId = 2097;
        $.get('monitor/topology/char_data?platformId=' + params.platformId + '&monitorTime=' + params.monitorTime
            + '&start=' + params.start + '&end=' + params.end, function(rs) {
            var edges = rs.edges;
            var nodes =  rs.nodes;
            var monitors = rs.monitors;
            var typeMap = rs.itypeMap;

            var hostMap = {};
            for (var i = 0; i < monitors.length; i++) {
                var monitor = monitors[i];
                var ip = monitor.serverip;
                var type = typeMap[monitor.itype];

                var host = hostMap[ip] || (hostMap[ip] = {});
                var count = host[type] || 0;
                host[type] = count + 1;
            }


            for (var i = 0; i < nodes.length; i++) {
                var node = nodes[i];
                var nodeName = node.node_name;
                var hostError = hostMap[nodeName];
                if (hostError) {
                    node.error = hostError;
                    node.status = false;
                } else {
                    node.status = true;
                }
            }

            var topology = new Topology('container', params.uuid, params.platformId, params.monitorTime, params.start, params.end);
            topology.addNodes(nodes);
            topology.addLinks(edges);
            topology.update();

            var id = setInterval(function() {
                if ($('#' + params.uuid + '-container').length <= 0) {
                    clearInterval(id);
                    return;
                }

                $.get('monitor/topology/char_monitor_data?platformId=' + params.platformId + '&monitorTime=' + params.monitorTime
                    + '&start=' + params.start + '&end=' + params.end, function(rs) {
                    var monitors = rs.monitors;

                    var hostMap = {};
                    for (var i = 0; i < monitors.length; i++) {
                        var monitor = monitors[i];
                        var ip = monitor.serverip;
                        var type = typeMap[monitor.itype];

                        var host = hostMap[ip] || (hostMap[ip] = {});
                        var count = host[type] || 0;
                        host[type] = count + 1;
                    }

                    for (var i = 0; i < nodes.length; i++) {
                        var node = nodes[i];
                        var nodeName = node.node_name;
                        var hostError = hostMap[nodeName];
                        if (hostError) {
                            node.error = hostError;
                            node.status = false;
                        } else {
                            node.error = null;
                            node.status = true;
                        }
                    }

                    topology.updateState();

                });
            }, 30 * 1000);


        });
    }


    return {
        init: init
    };
});