package com.kodilla.transfersrealizationservice.service;

import com.kodilla.commons.AccountUpdate;
import com.kodilla.commons.AccountUpdateMessage;
import com.kodilla.transfersrealizationservice.connector.AccountsConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class AccountUpdateListener {


    private final AccountsConnector connector;

    @Autowired
    public AccountUpdateListener(AccountsConnector connector) {
        this.connector = connector;
    }

    @KafkaListener(topics = "account_update")
    public void consume(@Payload AccountUpdateMessage accountUpdateMessage) throws IOException {
        log.info("Consumed accountUpdateMessage: {}", accountUpdateMessage);
        AccountUpdate update = accountUpdateMessage.getUpdate();

        log.info("Updating sender account: {}, by subtracting amount of: {}", update.getSenderAccount(), update.getAmount());
        connector.updateAccountAvailableFunds(update.getSenderAccount(), update.getAmount().negate());

        log.info("Updating recipent account: {}, by adding amount of: {}", update.getRecipientAccount(), update.getAmount());
        connector.updateAccountAvailableFunds(update.getRecipientAccount(), update.getAmount());
    }
}