package com.chslcompany.picpaysimplificado.service;

import com.chslcompany.picpaysimplificado.domain.transaction.Transaction;
import com.chslcompany.picpaysimplificado.domain.user.Users;
import com.chslcompany.picpaysimplificado.dtos.TransactionDTO;
import com.chslcompany.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    private static final String URL_AUTHORIZATION = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;


    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        Users sender = this.userService.findUserById(transactionDTO.senderId());
        Users receiver = this.userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(sender, transactionDTO.value() );

        if (!this.authorizeTransaction(sender, transactionDTO.value())){
            throw new Exception("Transação não autorizada");
        }
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        this.transactionRepository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação enviada com sucesso");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return transaction;
    }

    public boolean authorizeTransaction(Users sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(URL_AUTHORIZATION, Map.class);
        return authorizationResponse.getStatusCode() == HttpStatus.OK && authorizationResponse.getBody().get("message").equals("Autorizado");
    }
}
