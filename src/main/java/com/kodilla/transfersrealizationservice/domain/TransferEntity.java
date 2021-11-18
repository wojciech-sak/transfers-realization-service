package com.kodilla.transfersrealizationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "TRANSFERS")
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSFER_ID", unique = true)
    private Long id;

    private String senderAccount;
    private String recipientAccount;
    private String title;
    private BigDecimal amount;

    public TransferEntity(String senderAccount, String recipientAccount, String title, BigDecimal amount) {
        this.senderAccount = senderAccount;
        this.recipientAccount = recipientAccount;
        this.title = title;
        this.amount = amount;
    }
}
