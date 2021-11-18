package com.kodilla.transfersrealizationservice.dao;

import com.kodilla.transfersrealizationservice.domain.TransferEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TransfersDao extends CrudRepository<TransferEntity, Long> {
}
