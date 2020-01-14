<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <td>日期</td>
        <td>类型</td>
        <td>积分</td>
        <td>剩余</td>
        <td>状态</td>
    </tr>
    </thead>
    <tbody>
    <tr v-for="record in records">
        <td>{{record.create_time}}</td>
        <td>
            <label v-if="record.detail_type==1" class="control-label">企业注册</label>
            <label v-if="record.detail_type==2" class="control-label">普通认证</label>
            <label v-if="record.detail_type==3" class="control-label">vip认证</label>
            <label v-if="record.detail_type==4" class="control-label">发布采购需求</label>
            <label v-if="record.detail_type==5" class="control-label">分享app</label>
            <label v-if="record.detail_type==6" class="control-label">推荐完成普通认证</label>
            <label v-if="record.detail_type==7" class="control-label">推荐完成vip认证</label>
            <label v-if="record.detail_type==8" class="control-label">发布找钱需求</label>
            <label v-if="record.detail_type==9" class="control-label">发布赚钱需求</label>
            <label v-if="record.detail_type==10" class="control-label">发布省钱需求</label>
            <label v-if="record.detail_type==11" class="control-label">抢单</label>
            <label v-if="record.detail_type==12" class="control-label">体验会员</label>
            <label v-if="record.detail_type==13" class="control-label">白金会员</label>
            <label v-if="record.detail_type==14" class="control-label">钻石会员</label>
        </td>
        <td>{{record.score}}</td>
        <td>{{record.score_avaid}}</td>
        <td>
            <div v-if="record.state==0">
                <label class="control-label">失败</label>
            </div>
            <div v-if="record.state==1">
                <label class="control-label">成功</label>
            </div>
            <div v-if="record.state==2">
                <label class="control-label">初始状态</label>
            </div>
        </td>
    </tr>
    </tbody>
</table>