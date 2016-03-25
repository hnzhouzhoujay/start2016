package com.zj.code.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;

public class Rev {
	final static String QUEUE_NAME="hello";
	ConnectionFactory connectionFactory=null;
	Connection connection=null;
	Channel channel=null;
	public Rev(String host){
		connectionFactory=new ConnectionFactory();
		connectionFactory.setHost(host);
	}
	public static void main(String[] args) {
		Rev rev=new Rev("127.0.0.1");
		rev.receive();
	}
	public  void receive() {
		try {
			connection=connectionFactory.newConnection();
			channel=connection.createChannel();
//			channel.queueDeclare(QUEUE_NAME, false, false, false, null);//声明一个消息队列，没有会创建
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);//第二个参数表示持久化队列
//			channel.basicQos(1);//一次只发一条，确认后再发
			Consumer consumer=new DefaultConsumer(channel){
				@Override
				public void handleDelivery(String consumerTag,
						Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					String msg=new String(body,"UTF-8");
					System.out.println("get Message :"+msg);
					try{
						System.out.println("do something");
					}finally{
						channel.basicAck(envelope.getDeliveryTag(), false);//第二个参数表示自动ack,如果要消息确认必须设为false
					}
				}
			};
			channel.basicConsume(QUEUE_NAME,false,consumer);//第二个参数表示自动ack,如果设为false,handleDelivery中必须调用basicAck;
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
