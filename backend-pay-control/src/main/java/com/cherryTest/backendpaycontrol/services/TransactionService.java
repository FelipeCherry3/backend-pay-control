package com.cherryTest.backendpaycontrol.services;

import com.cherryTest.backendpaycontrol.domain.transaction.Transaction;
import com.cherryTest.backendpaycontrol.domain.user.User;
import com.cherryTest.backendpaycontrol.dto.TransactionDTO;
import com.cherryTest.backendpaycontrol.repositories.TransactionRepository;
import com.cherryTest.backendpaycontrol.repositories.UserRepository;
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
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderID());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validarTransaction(sender,transaction.value());

        boolean isAuthorized = authorizeTransaction(sender,transaction.value());
        if(!isAuthorized){
            throw new Exception("Não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        transactionRepository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
    }
    public Boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado" .equalsIgnoreCase(message);
        } else return false;
    }
}
