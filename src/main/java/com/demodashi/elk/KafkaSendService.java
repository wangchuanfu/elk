package com.demodashi.elk;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@Named("kafkaSendService")
public class KafkaSendService {
    @Autowired
    @Qualifier("inputToKafka")
    MessageChannel channel;

    public void send(String key, Object obj) {
        Message msg = MessageBuilder.withPayload(obj).build();
                //.setHeader("kafkaUser", key)
                //.setHeader(KafkaHeaders.TOPIC, "mylog_topic") //topic已经在spring配置中设置了
        		//.build();
        channel.send(msg);
    }
}
