<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <td></td>
        <td>需求类别</td>
        <td>业务分类</td>
        <td>状态</td>
        <td>发布时间</td>
    </tr>
    </thead>
    <tbody>
    <tr v-for="require in requires">
        <td>
            <a class="ajaxify" :module_id="require.req_id | concat('module_requirement_view_')" :href="require.req_id | concat('configuration/requirement/view?req_id=')" title="点击查看" data-html="true">
                <i class="fa fa-share"></i>
            </a>
            <a class="hidden"><i class="fa fa-eye"></i> 需求详情</a>
        </td>
        <td>
            <div v-if="require.req_type==0">
                <label class="control-label">未知</label>
            </div>
            <div v-if="require.req_type==1">
                <label class="control-label">找钱</label>
            </div>
            <div v-if="require.req_type==2">
                <label class="control-label">赚钱</label>
            </div>
            <div v-if="require.req_type==3">
                <label class="control-label">省钱</label>
            </div>
        </td>
        <td>{{require.req_name}}</td>
        <td>
            <div v-if="require.state==0">
                <label class="control-label">发布中</label>
            </div>
            <div v-if="require.state==1">
                <label class="control-label">已抢单未满员</label>
            </div>
            <div v-if="require.state==2">
                <label class="control-label">满员</label>
            </div>
            <div v-if="require.state==3">
                <label class="control-label">已成交</label>
            </div>
            <div v-if="require.state==3">
                <label class="control-label">已失效</label>
            </div>
        </td>
        <td>
            <div v-if="require.state==1">
                <label class="control-label">{{require.ser_time}}</label>
            </div>
            <div v-else>
                <label class="control-label">{{require.create_time}}</label>
            </div>
        </td>
    </tr>
    </tbody>
</table>