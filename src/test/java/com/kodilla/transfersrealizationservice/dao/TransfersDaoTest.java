package com.kodilla.transfersrealizationservice.dao;

import com.kodilla.commons.Transfer;
import com.kodilla.transfersrealizationservice.domain.TransferEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TransfersDaoTest {
    @Autowired
    private TransfersDao transfersDao;

    @Test
    void testRetrieveSavedTransfer() {
        //Given
        TransferEntity transfer = new TransferEntity("senderAccount", "recipientAccount", "title", new BigDecimal("10000.15"));
        TransferEntity savedTransfer = transfersDao.save(transfer);

        //When
        Optional<TransferEntity> retrievedTransfer = transfersDao.findById(savedTransfer.getId());

        //Then
        assertEquals(retrievedTransfer.get().getAmount(), savedTransfer.getAmount());
    }
}