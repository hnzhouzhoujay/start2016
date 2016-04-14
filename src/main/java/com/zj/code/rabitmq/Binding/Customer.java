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
 * 声明一个临时队列
 */
public class Customer {
	static final String EXCHANGE_NAME="logs";
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory=new ConnectionFactory();
		Connection connection=factory.newConnection();
		final Channel channel=connection.createChannel();
		String queueName=channel.queueDeclare().getQueue();//声明一个临时队列，名字自动生成，当consumer断开连接时会自动删除
		/*
		 * 1.当exchange类型为direct时，根据routing_key完全匹配，没有匹配的消息会被丢掉
		 * 2.当exchange类型为topic是，根据routing_key匹配，可用通配符，*代表任意1个字符，#代表0个或多个字符
		 */
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//声明一个exchange ,producer和consumer都要，不然可能存在一方先启动而不存在的错

		/*  1.queueName 2.exchangeName3.routingkey 在fanout(广播)类型exchange会忽略掉        */
		channel.queueBind(queueName, "logs", "");
		channel.queueBind(queueName, "logs", "error");
		Consumer consumer=new  DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					BasicProperties properties, byte[] body) throws IOException {
				String msg=new String(body,"utf-8");
				System.out.println("[x]  receive msg:"+msg);
				System.out.println("保存日志");
			}
		
		};
		/* 1队列名2是否自动确认false需程序handleDelivery中调用channel.basicAck      */
		channel.basicConsume(queueName,true, consumer);
		
	}

}
