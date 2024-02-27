package com.cherryTest.backendpaycontrol.repositories;

import com.cherryTest.backendpaycontrol.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
