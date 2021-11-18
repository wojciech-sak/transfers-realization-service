package com.kodilla.transfersrealizationservice.service;

import com.kodilla.commons.AccountUpdate;
import com.kodilla.commons.AccountUpdateMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountUpdateProducer {

    private static final String ACCOUNT_UPDATE_TOPIC = "account_update";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendAccountUpdate(final AccountUpdate accountUpdate) {
        AccountUpdateMessage accountUpdateMessage = new AccountUpdateMessage(accountUpdate);
        log.info("Sending message to Kafka, accountUpdateMessage: {}", accountUpdateMessage);
        kafkaTemplate.send(ACCOUNT_UPDATE_TOPIC, accountUpdateMessage);
        log.info("Message was sent");
    }

}