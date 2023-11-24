package com.chslcompany.picpaysimplificado.repositories;

import com.chslcompany.picpaysimplificado.domain.transaction.Transaction;
import com.chslcompany.picpaysimplificado.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//    Optional<Users> findTransactionBySender(Users sender);
//    Optional<Users> findTransactionByReceiver(Users receiver);

}
