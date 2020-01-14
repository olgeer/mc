<div class="portlet blue box" v-if="records.length > 0">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-magic"></i>积分记录
        </div>
    </div>
    <div id="score-${companyId}" class="portlet-body">
        <a class="ajaxify"
           :module_id="company.company_id | concat('module_score_view_')"
           :href="company.company_id | concat('configuration/score/list?company_id=')" title="积分记录" data-html="true">
            <#include "score_fragment.ftl" />
        </a>
        <a class="hidden"><i class="fa fa-eye"></i> 使用记录</a>
    </div>
</div>

