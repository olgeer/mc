package com.ucsmy.mc.module.message.util;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;

import com.ucsmy.mc.module.message.controller.MessageController.MsgType;
import com.ucsmy.mc.module.message.entity.MessReceive;
import com.ucsmy.mc.module.message.mmapper.MessageMapper;
import com.ucsmy.mc.util.ApplicationContextHolder;
import com.ucsmy.mc.util.MailUtil;
import com.ucsmy.mc.util.UUIDUtil;

public class MsgUtil {

	private static MessageMapper messageMapper;
	private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

	static {
		messageMapper = ApplicationContextHolder.getBean(MessageMapper.class);
	}

	public static class Mstip {

		/**
		 * 未知
		 */
		public static int UNKNOW = 0;

		/**
		 * 通知
		 */
		public static int MESSAGE = 1;

		/**
		 * 提醒
		 */
		public static int WARN = 2;

		/**
		 * 告警
		 */
		public static int ALARM = 3;

		/**
		 * 错误
		 */
		public static int ERROR = 4;

		/**
		 * 其它
		 */
		public static int OTHER = 9;
	}
	
	
	/**
	 * 发送消息
	 * 
	 * @param title 标题
	 * @param detail 内容
	 * @param relationDomain 关联对象
	 * @param relationDomainId 关联对象ID
	 * @param sendUserId 发送人
	 * @param revUserId 接收人
	 * @param mstip 消息类型 MsgUtil.Mstip
	 */
	public static void sendMsg(final String title, final String detail, final String relationDomain, final String relationDomainId,
			final String sendUserId, final String revUserId, final int mstip) {

		singleThreadExecutor.execute(new Runnable() {
			public void run() {
				MessReceive r = new MessReceive();
				r.setId(UUIDUtil.creatUUID());
				r.setTitle(title);
				r.setDetail(detail);
				r.setRelation_domain(relationDomain);
				r.setRelation_domain_id(relationDomainId);
				r.setSend_user_id(sendUserId);
				r.setRev_user_id(revUserId);
				r.setSts(MsgType.UNREAD);
				r.setMstip(mstip);
				r.setSend_time(new Date());
				messageMapper.InsertMessage(r);

				String mail = messageMapper.selectUserMailUserId(revUserId);
				if (StringUtils.isNotBlank(mail)) {
					MailUtil.sendMail(mail, title, detail);
				}
				
			}
		});
	}

}
