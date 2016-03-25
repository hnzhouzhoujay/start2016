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
package com.zj.code.rabitmq.Basic;

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
public class Customer {
	static final String QUEUE_NAME="TESTQUEUE";
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory=new ConnectionFactory();
		Connection connection=factory.newConnection();
		final Channel channel=connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);//声明队列在producer和custonmer都要写，防止一方启动队列不存在
		channel.basicQos(1);//设置一次只处理一条,这里能起到负载均衡的功能，如果一个consumer没处理完成，会交给另一个consumer
		Consumer consumer=new  DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					BasicProperties properties, byte[] body) throws IOException {
				String msg=new String(body,"utf-8");
				System.out.println("[x] receive msg:"+msg);
				try{
					doSomeThing(msg);
					System.out.println("[x] done");
				}finally{
					/* 1.deliveryTag2.是否自动确认与channel一致               */
					channel.basicAck(envelope.getDeliveryTag(), false);//消息确认
				}
			}
			private void doSomeThing(String msg) {
				for (char ch : msg.toCharArray()) {
					if(ch=='.'){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		/* 1队列名2是否自动确认false需程序handleDelivery中调用channel.basicAck      */
		channel.basicConsume(QUEUE_NAME,false, consumer);
		
	}

}
