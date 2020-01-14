<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <td>分配id</td>
        <td>司务号</td>
        <td>企业名称</td>
        <td>手机号</td>
        <td>匹配状态</td>
        <td>匹配时间</td>
    </tr>
    </thead>
    <tbody>
    <tr v-for="detail in details">
        <td>{{detail.req_assign_id}}</td>
        <td>{{detail.ser_cm_id}}</td>
        <td>
            <a class="ajaxify" :module_id="detail.ser_company_id | concat('module_company_view_')" :href="detail.ser_company_id | concat('configuration/company/view?company_id=')" title="企业详情" data-html="true">
                {{detail.ser_company_name}}
            </a>
            <a class="hidden"><i class="fa fa-eye"></i> 企业详情</a>
        </td>
        <td>{{detail.ser_user_mobile}}</td>
        <td>
            <div v-if="detail.state==-1">
                <label class="control-label">未读</label>
            </div>
            <div v-if="detail.state==0">
                <label class="control-label">已读</label>
            </div>
            <div v-if="detail.state==1">
                <label class="control-label">已抢单</label>
            </div>
            <div v-if="detail.state==2">
                <label class="control-label">满单未抢单</label>
            </div>
            <div v-if="detail.state==21">
                <label class="control-label">满单已抢单</label>
            </div>
            <div v-if="detail.state==3">
                <label class="control-label">抢单成交</label>
            </div>
            <div v-if="detail.state==4">
                <label class="control-label">已失效未抢单</label>
            </div>
            <div v-if="detail.state==41">
                <label class="control-label">已失效已抢单</label>
            </div>
        </td>
        <td>{{detail.assign_time}}</td>
    </tr>
    </tbody>
</table>