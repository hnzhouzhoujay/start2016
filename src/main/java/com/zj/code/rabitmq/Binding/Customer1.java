/**
* 文件名：Customer.java
* 创建日期： 2016-3-25
* 作者：     周杰
* Copyright (c) 2009-2016 架构平台组
* All rights reserved.
 
* 修改记录：
* 	1.修改时间：2016-3-25
*   修改人：Administrator
*   修改内容：
*/
package com.zj.code.rabitmq.Binding;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 功能描述：
 *
 */
public class Customer1 {
	static final String EXCHANGE_NAME="logs";
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory=new ConnectionFactory();
		Connection connection=factory.newConnection();
		final Channel channel=connection.createChannel();
		String queueName=channel.queueDeclare().getQueue();//声明一个临时队列，名字自动生成，当consumer断开连接时会自动删除
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//声明一个exchange ,producer和consumer都要，不然可能存在一方先启动而不存在的错
		/*  1.queueName 2.exchangeName        */
		channel.queueBind(queueName, "logs", "");
		Consumer consumer=new  DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					BasicProperties properties, byte[] body) throws IOException {
				String msg=new String(body,"utf-8");
				System.out.println("[x]  receive msg:"+msg);
				System.out.println("打印日志");
			}
		
		};
		/* 1队列名2是否自动确认false需程序handleDelivery中调用channel.basicAck      */
		channel.basicConsume(queueName,true, consumer);
		
	}

}
