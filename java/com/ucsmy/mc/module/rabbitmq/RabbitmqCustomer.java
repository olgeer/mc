package com.ucsmy.mc.module.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.ucsmy.mc.module.monitor.entity.ZbHisData;
import com.ucsmy.mc.module.monitor.entity.ZbHisMessage;
import com.ucsmy.mc.module.monitor.mapper.ZbHisDataMapper;
import com.ucsmy.mc.module.monitor.util.ZabbixUtil;
import com.ucsmy.mc.module.monitor.util.ZipUtil;
import com.zabbix4j.item.ItemGetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ucs_mawenzhong on 2017/7/26.
 */
@Component
public class RabbitmqCustomer implements MessageListener {
    private static Logger logger = LoggerFactory.getLogger(RabbitmqCustomer.class);

    @Autowired
    ZbHisDataMapper zbHisDataMapper;

    public void onMessage(Message message) {
        byte[] body = message.getBody();
        String msg = new String(body, Charset.defaultCharset());
        //logger.info("消息消费者 = " + msg);

        ZbHisMessage zbHisMessage = JSON.parseObject(msg, ZbHisMessage.class);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = sdf.parse(zbHisMessage.getAlarmtime());

            List<ItemGetResponse.Result> historyItems
                    = ZabbixUtil.getHistoryItems(zbHisMessage.getHostName(), parse);

            String data = JSON.toJSONString(historyItems);
            String compress = ZipUtil.compress(data);
            ZbHisData zbHisData = new ZbHisData();
            zbHisData.setId(zbHisMessage.getId());
            zbHisData.setZbData(compress);
            zbHisDataMapper.insert(zbHisData);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
