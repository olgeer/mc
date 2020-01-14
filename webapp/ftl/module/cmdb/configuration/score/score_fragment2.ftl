<div class="form-horizontal">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-4 control-label">积分记录id</label>
            <div class="col-md-8">
                <label class="control-label">{{record.record_id}}</label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">积分订单id</label>
            <div class="col-md-8">
                <label class="control-label">{{record.order_id}}</label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">用户名称</label>
            <div class="col-md-8">
                <label class="control-label">{{record.user_name}}|{{record.user_id}}</label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">企业名称</label>
            <div class="col-md-8">
                <label class="control-label">{{record.company_name}}|{{record.company_id}}</label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">具体类型</label>
            <div class="col-md-8">
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
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">积分</label>
            <div class="col-md-8">
                <label class="control-label">{{record.score}}</label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">变更后积分</label>
            <div class="col-md-8">
                <label class="control-label">{{record.score_avaid}}</label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">变更状态</label>
            <div class="col-md-8">
                    <label v-if="record.state==0" class="control-label">失败</label>
                    <label v-if="record.state==1" class="control-label">成功</label>
                    <label v-if="record.state==2" class="control-label">初始状态</label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">记录时间</label>
            <div class="col-md-8">
                <label class="control-label">{{record.create_time}}</label>
            </div>
        </div>
    </div>
</div>