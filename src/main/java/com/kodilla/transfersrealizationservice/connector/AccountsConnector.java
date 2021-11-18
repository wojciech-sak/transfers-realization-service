package com.kodilla.transfersrealizationservice.connector;

import com.kodilla.transfersrealizationservice.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.math.BigDecimal;

@FeignClient(name = "accounts")
public interface AccountsConnector {

    @GetMapping("/v1/accounts/{nrb}")
    AccountDto getAccount(@PathVariable("nrb") String nrb);

    @PutMapping("/v1/accounts/{nrb}/{amount}")
    AccountDto updateAccountAvailableFunds(@PathVariable String nrb, @PathVariable BigDecimal amount);
}
