package org.lionbo.practice.kafka;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

public class KafkaTest {

    private static final Logger logger = LoggerFactory.getLogger(KafkaTest.class);
    private String topic1 = "UMEC_noah_server_message_in_channel";
    private String group = "group";

    public static void main(String[] args) {
        KafkaTest t = new KafkaTest();
        try {
            t.testSend();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testSend() throws Exception {
        logger.info("Start auto");
        KafkaTemplate<String, String> template = createTemplate();
        //        template.setDefaultTopic(topic1);
        String date = new Date().toString();
        String aString = "{\"payload\":{\"businessType\":17,\"cityId\":\"1\",\"phone\":\"18532976927\",\"roleType\":1,\"userId\":\"284973903134720\"},\"session\":\"395c469c-22d5-4409-a773-9e13ffe06884\",\"skillGroupName\":\"ddappkcck\",\"time\":"
                + date + ",\"type\":1}";
        System.out.println(date);
        try {
            ListenableFuture<SendResult<String, String>> future = template.send(topic1, "test", aString);
            SendResult<String, String> result = future.get();
            System.out.println(result.toString());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

    private KafkaTemplate<String, String> createTemplate() {
        Map<String, Object> senderProps = senderProps();
        ProducerFactory<String, String> pf = new DefaultKafkaProducerFactory<String, String>(senderProps);
        KafkaTemplate<String, String> template = new KafkaTemplate<>(pf);
        return template;
    }

    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.94.96.197:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    private Map<String, Object> senderProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.94.96.197:9092");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
}
