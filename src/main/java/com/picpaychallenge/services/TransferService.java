package com.picpaychallenge.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaychallenge.dto.TransferDto;
import com.picpaychallenge.model.Transfer;
import com.picpaychallenge.model.User;
import com.picpaychallenge.repository.TransferRepository;

@Service
public class TransferService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransfer(TransferDto transfer) throws Exception {
        User sender = this.userService.findUser(transfer.senderId());
        User receiver = this.userService.findUser(transfer.receiverId());

        userService.validateTransfer(sender, transfer.value());

        if (!this.authorizeTransfer(sender, transfer.value())) {
            throw new Exception("Transação não autorizada");
        }

        Transfer newTransfer = new Transfer();
        newTransfer.setAmout(transfer.value());
        newTransfer.setSender(sender);
        newTransfer.setReceiver(receiver);
        newTransfer.setTimestamp(LocalDateTime.now());
        
        sender.setBalance(sender.getBalance().subtract(transfer.value()));
        receiver.setBalance(receiver.getBalance().add(transfer.value()));

        transferRepository.save(newTransfer);
        userService.saveUser(sender);
        userService.saveUser(receiver);
    }

    public boolean authorizeTransfer(User sender, BigDecimal value) {
        ResponseEntity<Map> authorization = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize",
                Map.class);

        if (authorization.getStatusCode() == HttpStatus.OK) {
            return true;
        }

        return false;
    }
}
