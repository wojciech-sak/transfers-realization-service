package com.kodilla.transfersrealizationservice.service;

import com.kodilla.commons.AccountUpdate;
import com.kodilla.commons.Transfer;
import com.kodilla.commons.TransferMessage;
import com.kodilla.transfersrealizationservice.dao.TransfersDao;
import com.kodilla.transfersrealizationservice.domain.TransferEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class TransferListener {

    private final TransfersDao transfersDao;
    private final AccountUpdateProducer accountUpdateProducer;

    @Autowired
    public TransferListener(TransfersDao transfersDao, AccountUpdateProducer accountUpdateProducer) {
        this.transfersDao = transfersDao;
        this.accountUpdateProducer = accountUpdateProducer;
    }

    @KafkaListener(topics = "transfers")
    public void consume(@Payload TransferMessage transferMessage) throws IOException {
        log.info("Consumed transferMessage: {}", transferMessage);

        log.info("Saving transfer to db.");
        Transfer transfer = transferMessage.getTransfer();
        transfersDao.save(new TransferEntity(
                transfer.getSenderAccount(),
                transfer.getRecipientAccount(),
                transfer.getTitle(),
                transfer.getAmount()
        ));

        AccountUpdate accountUpdate = new AccountUpdate();
        accountUpdate.setSenderAccount(transfer.getSenderAccount());
        accountUpdate.setRecipientAccount(transfer.getRecipientAccount());
        accountUpdate.setAmount(transfer.getAmount());

        accountUpdateProducer.sendAccountUpdate(accountUpdate);
    }

}