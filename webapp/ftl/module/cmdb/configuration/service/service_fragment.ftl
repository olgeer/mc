<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <td></td>
        <td>需求类别</td>
        <td>业务分类</td>
        <td>抢单状态</td>
        <td>发布时间</td>
    </tr>
    </thead>
    <tbody>
    <tr v-for="service in services">
        <td>
            <a class="ajaxify" :module_id="service.req_id | concat('module_requirement_view_')" :href="service.req_id | concat('configuration/requirement/view?req_id=')" title="点击查看" data-html="true">
                <i class="fa fa-share"></i>
            </a>
            <a class="hidden"><i class="fa fa-eye"></i> 需求详情</a>
        </td>
        <td>
            <div v-if="service.req_type==0">
                <label class="control-label">未知</label>
            </div>
            <div v-if="service.req_type==1">
                <label class="control-label">找钱</label>
            </div>
            <div v-if="service.req_type==2">
                <label class="control-label">赚钱</label>
            </div>
            <div v-if="service.req_type==3">
                <label class="control-label">省钱</label>
            </div>
        </td>
        <td>{{service.req_name}}</td>
        <td>
            <div v-if="service.ass_state==-1">
                <label class="control-label">未读</label>
            </div>
            <div v-if="service.ass_state==0">
                <label class="control-label">已读</label>
            </div>
            <div v-if="service.ass_state==1">
                <label class="control-label">已抢单</label>
            </div>
            <div v-if="service.ass_state==2">
                <label class="control-label">满单未抢单</label>
            </div>
            <div v-if="service.ass_state==21">
                <label class="control-label">满单已抢单</label>
            </div>
            <div v-if="service.ass_state==3">
                <label class="control-label">抢单成交</label>
            </div>
            <div v-if="service.ass_state==4">
                <label class="control-label">已失效未抢单</label>
            </div>
            <div v-if="service.ass_state==41">
                <label class="control-label">已失效已抢单</label>
            </div>
        </td>
        <td>
            <div v-if="service.ass_state==1 || service.ass_state==21 || service.ass_state==3 || service.ass_state==41">
                <label class="control-label">{{service.ser_time}}</label>
            </div>
            <div v-else>
                <label class="control-label">{{service.ass_time}}</label>
            </div>
        </td>
    </tr>
    </tbody>
</table>