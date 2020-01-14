<style type="text/css">
    .link { stroke: green; stroke-linejoin:bevel;}

    .link_error{
        stroke:red;
        stroke-linejoin:bevel;
    }

    .nodetext {
        font: 12px sans-serif;
        -webkit-user-select:none;
        -moze-user-select:none;
        stroke-linejoin:bevel;
    }

    .topology-container{
        border:1px solid #CCCCCC;
        border-radius:5px;
        position: absolute;
        top: 50px;
        left: 20px;
        bottom: 10px;
        right: 20px;
    }
</style>
<div id='${uuid}-container' class="topology-container"></div>


<style>
    .tip {
        position: fixed;
        display: none;
        border-style: solid;
        white-space: nowrap;
        z-index: 9999999;
        transition: left 0.4s cubic-bezier(0.23, 1, 0.32, 1), top 0.4s cubic-bezier(0.23, 1, 0.32, 1);
        background-color: rgba(50, 50, 50, 0.7);
        border-width: 0px;
        border-color: rgb(51, 51, 51);
        border-radius: 4px;
        color: rgb(255, 255, 255);
        font-style: normal;
        font-variant: normal;
        font-weight: normal;
        font-stretch: normal;
        font-size: 14px;
        font-family: & quot;
        Microsoft YaHei & quot;;
        line-height: 21px;
        padding: 5px;
    }

    .t-r-d {
        display: inline-block;
        margin-right: 5px;
        border-radius: 10px;
        width: 9px;
        height: 9px;
        background-color: #c23531;
    }
    .t-r-d-1 {background-color:#c23531;}
    .t-r-d-2 {background-color:#2f4554;}
    .t-r-d-3 {background-color:#61a0a8;}
    .t-r-d-4 {background-color:#d48265;}
    .t-r-d-5 {background-color:#91c7ae;}

    .m-l-3 {margin-left: 3px;}
</style>
<div id="${uuid}-tip" class="tip">

</div>

<script>
    require(["jquery", "../custom/scripts/monitor/topology_char_init"], function($, page) {
        page.init({
            uuid: '${uuid}',
            platformId: '${platformId}',
            monitorTime: '${monitorTime}',
            start: '${start}',
            end: '${end}'
        });
    });
</script>