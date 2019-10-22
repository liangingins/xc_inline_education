package com.xuecheng.test.freemarker.controller;

import com.sun.xml.internal.messaging.saaj.soap.Envelope;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChatTest {

    private static final String EXCHANGE_FANOUT_CHAT="exchange_fanout_chat";

    public static void main(String[] args) throws Exception {
        String  userName = null;


        //通过连接工厂创建新的连接和mq建立连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("192.168.11.33");//查阅   资料  修改rabbitmq 可以通过远程访问
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);//端口
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //设置虚拟机，一个mq服务可以设置多个虚拟机，每个虚拟机就相当于一个独立的mq
        connectionFactory.setVirtualHost("/");

        //建立新连接
        Connection connection = connectionFactory.newConnection();
        //创建会话通道,生产者和mq服务所有通信都在channel通道中完成
        Channel channel = connection.createChannel();

        System.out.println("请输入你的姓名：");
        Scanner scanner = new Scanner(System.in);

        userName = scanner.nextLine().trim();

        channel.exchangeDeclare(EXCHANGE_FANOUT_CHAT, BuiltinExchangeType.FANOUT);

        String queueName = System.currentTimeMillis()+"q";
        channel.queueDeclare(queueName,false,false,true,null);

        channel.queueBind(queueName, EXCHANGE_FANOUT_CHAT, "");

        //实现消费方法
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){

            /**
             * 当接收到消息后此方法将被调用
             * @param consumerTag  消费者标签，用来标识消费者的，在监听队列时设置channel.basicConsume
             * @param envelope 信封，通过envelope
             * @param properties 消息属性
             * @param body 消息内容
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                Object uname = properties.getHeaders().get("userName");
                //消息内容
                String message= new String(body,"utf-8");
                System.out.println(uname+":"+message);
            }
        };

        //监听队列
        //参数：String queue, boolean autoAck, Consumer callback
        /**
         * 参数明细：
         * 1、queue 队列名称
         * 2、autoAck 自动回复，当消费者接收到消息后要告诉mq消息已接收，如果将此参数设置为tru表示会自动回复mq，如果设置为false要通过编程实现回复
         * 3、callback，消费方法，当消费者接收到消息要执行的方法
         */
        channel.basicConsume(queueName,true,defaultConsumer);

        System.out.println("请输入消息内容：");
        Map<String,Object> map = new HashMap<>();
        String msg = null;
        while ((msg=scanner.nextLine())!=null){
            map.put("userName",userName);
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().headers(map).build();
            channel.basicPublish(EXCHANGE_FANOUT_CHAT,"",properties,msg.getBytes("utf-8"));
        }


    }
}
