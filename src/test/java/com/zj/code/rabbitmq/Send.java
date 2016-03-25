package com.zj.code.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Send {
	public static final String QUEUE_NAME="task_queue";
	public static void main(String[] args) {
		ConnectionFactory connectionFactory=new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");
		Connection connection=null;
		Channel channel=null;
		try {
			connection=connectionFactory.newConnection();
			channel=connection.createChannel();
//			channel.queueDeclare(QUEUE_NAME, false, false, false, null);//声明一个消息队列，没有会创建
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);//第二个参数表示持久化队列
			String msg="get marry....";
			channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());//表示
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
				try {
					if(channel!=null){
						channel.close();
					}
					if(connection!=null){
						connection.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			
		}
		
	}

}
