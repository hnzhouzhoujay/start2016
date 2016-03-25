/**
* 文件名：Produce.java
* 创建日期： 2016-3-25
* 作者：     周杰
* Copyright (c) 2009-2016 架构平台组
* All rights reserved.
 
* 修改记录：
* 	1.修改时间：2016-3-25
*   修改人：Administrator
*   修改内容：
*/
package com.zj.code.rabitmq.Basic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * 功能描述：
 *
 */
public class Producer {
	static final String QUEUE_NAME="TESTQUEUE";
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory=new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection=factory.newConnection();
		Channel channel=connection.createChannel();
		/* 1队列名,2队列是否持久化  */
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		String msg="hello ................";
		/* 1exchange名,2队列名，3消息持久化，4,消息字节数组 */
		channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes("utf-8"));//持久化
		channel.close();
		connection.close();
	}

}
