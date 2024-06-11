package org.api.producer.CouponCreateProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CouponCreateProducer {
    private final KafkaTemplate<String, Long> kafkaTemplate;


    public CouponCreateProducer(KafkaTemplate<String, Long> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void create(Long userId)  {
        kafkaTemplate.send("couponCreate", userId);
    }
}