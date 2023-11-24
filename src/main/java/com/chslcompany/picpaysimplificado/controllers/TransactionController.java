package com.chslcompany.picpaysimplificado.controllers;

import com.chslcompany.picpaysimplificado.domain.transaction.Transaction;
import com.chslcompany.picpaysimplificado.domain.user.Users;
import com.chslcompany.picpaysimplificado.dtos.TransactionDTO;
import com.chslcompany.picpaysimplificado.dtos.UserDTO;
import com.chslcompany.picpaysimplificado.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
